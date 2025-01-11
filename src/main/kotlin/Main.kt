import ast.AvChunk
import ast.AvMap
import ast.AvString.Companion.av
import environment.AvFormatter
import environment.AvInterpreter
import environment.AvPrinter
import environment.AvPrinter.Companion.stringify
import environment.CompressionType
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.fancryer.gen.AvLexer
import org.fancryer.gen.AvParser
import kotlin.time.Duration
import kotlin.time.measureTime

fun main()
{
	val helloWorldMap=AvMap().apply {
		props+="hello".av to AvMap().apply {
			props+="world".av to "Hello, world!".av
		}
	}
	println((helloWorldMap["hello"] as? AvMap)?.get("world"))

	val shouldPrintSexp=false

	val lexer:AvLexer
	val parser:AvParser
	val chunk:AvParser.ChunkContext

	val parsingTime:Duration
	val mappingTime:Duration
	val evaluationTime:Duration

	measureTime {
		lexer=AvLexer(CharStreams.fromFileName("src/main/resources/composer-lock.json")) //step4.av"))
		parser=AvParser(CommonTokenStream(lexer))
		chunk=parser.chunk()
	}.also {
		println("[[Av original string tree]]: \n")

		println("${chunk.toStringTree(parser)}\n\n")
		parsingTime=it
	}

	val mappedChunk:AvChunk
	measureTime {
		mappedChunk=AvAstMapper().visitChunk(chunk)
	}.also {
		println("[[Av mapped]]: \n")
		println(stringify(mappedChunk))

		if(shouldPrintSexp)
		{
			println("[[Sexp mapped]]: \n")
			println("${mappedChunk}\n\n")
		}
		mappingTime=it
	}

	val evaluatedChunk:AvChunk
	measureTime {
		evaluatedChunk=AvInterpreter().run {
			mappedChunk.eval
		}
	}.also {
		println("[[Av evaluated]]: \n")
		println(stringify(evaluatedChunk,CompressionType.Minified)
			.also {println("Evaluated with length: ${it.length}")})
		println()

		if(shouldPrintSexp)
		{
			println("[[Sexp evaluated]]: \n")
			println("${evaluatedChunk}\n\n")
		}
		evaluationTime=it
	}

	val formattedChunk:String
	val formatTime:Duration


	measureTime {
		AvFormatter().use {
			formattedChunk=it.format(evaluatedChunk)
		}
	}.also {
		println("[[Av formatted]]: \n")
		//		println(formattedChunk)

		formatTime=it
	}

	val totalTime=parsingTime+mappingTime+evaluationTime+formatTime

	println("Parsing time: $parsingTime | Parsing percent: ${parsingTime/totalTime*100}%")
	println("Mapping time: $mappingTime | Mapping percent: ${mappingTime/totalTime*100}%")
	println("Evaluation time: $evaluationTime | Evaluation percent: ${evaluationTime/totalTime*100}%\n")
	println("Format time: $formatTime | Formatted percent: ${formatTime/totalTime*100}%")
	println("Total time: $totalTime")
}

fun <Match,Result> matchNotNull(default:Result,vararg cases:Pair<Match?,Result>):Result
{
	for((case,result) in cases)
	{
		if(case!=null) return result
	}
	return default
}

fun <Reciever,Match,Result> Reciever.matchNonExhaustive(
	vararg cases:Reciever.()->Pair<Match?,(Match)->Result>
):Result?
{
	for(case in cases)
	{
		val (match,result)=case(this)
		if(match!=null) return result(match)
	}
	return null
}

fun <Reciever,Match,Result> Reciever.matchNotNull(
	default:Result,
	vararg cases:Reciever.()->Pair<Match?,(Match)->Result>
):Result=matchNotNull(default,cases.toList())

fun <Reciever,Match,Result> Reciever.matchNotNull(
	default:Result,
	cases:List<Reciever.()->Pair<Match?,(Match)->Result>>
):Result
{
	for(case in cases)
	{
		val (match,result)=case(this)
		if(match!=null) return result(match)
	}
	return default
}