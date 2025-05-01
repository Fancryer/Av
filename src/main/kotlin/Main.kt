import ast.AvChunk
import ast.AvMap
import ast.AvString.Companion.av
import environment.*
import environment.AvPrinter.Companion.stringify
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.Token
import org.antlr.v4.runtime.TokenStream
import org.antlr.v4.runtime.UnbufferedTokenStream
import org.fancryer.gen.AvLexer
import org.fancryer.gen.AvParser
import java.io.File
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

	val shouldPrintSexp=true

	val lexer:AvLexer
	val parser:AvParser
	val chunk:AvParser.ChunkContext

	val parsingTime:Duration
	val mappingTime:Duration
	val evaluationTime:Duration

	val fileName="src/main/resources/gaming.json" //"src/main/resources/composer-lock.json"

	measureTime {
		lexer=AvLexer(CharStreams.fromFileName(fileName)) //step4.av"))
//		parser=AvParser(CommonTokenStream(lexer))
		parser=AvParser(UnbufferedTokenStream<Token>(lexer))
		chunk=parser.chunk()
	}.also {
		println("[[Av original string tree]]: \n")
//		println("$stringTree\n\n")
		parsingTime=it
	}

	val mappedChunk:AvChunk
	measureTime {
		mappedChunk=AvAstMapper().visitChunk(chunk)
	}.also {
		println("[[Av mapped]]: \n")
//		println(stringify(mappedChunk))

		if(shouldPrintSexp)
		{
			println("[[Sexp mapped]]: \n")
//			println("${mappedChunk}\n\n")
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
		File("$fileName.fmt").writeText(
			AvPrinter().run {
				stringify(evaluatedChunk)
			}
		)
		evaluationTime=it
	}

	val totalTime=parsingTime+mappingTime+evaluationTime

	println("Parsing time: $parsingTime | Parsing percent: ${parsingTime/totalTime*100}%")
	println("Mapping time: $mappingTime | Mapping percent: ${mappingTime/totalTime*100}%")
	println("ParseMapping time: ${parsingTime+mappingTime} | ParseMapping percent: ${(parsingTime+mappingTime)/totalTime*100}%")
	println("Evaluation time: $evaluationTime | Evaluation percent: ${evaluationTime/totalTime*100}%\n")
	println("Total time: $totalTime")

//	map {
//		"user" map {
//			"id" entry 12345.av
//			"name" entry "Alice".av
//			"email" entry "alice@example.com".av
//			"age" entry 21.av
//			"preferences" map {
//				"theme" entry "dark".av
//				"language" entry "ru".av
//				"notifications" map {
//					"email" entry true.av
//					"push" entry false.av
//				}
//			}
//		}
//		"history" list {
//			+map {
//				"date" entry "2025-03-02".av
//				"action" entry "login".av
//			}
//			+map {
//				"date" entry "2025-03-01".av
//				"action" entry "update_settings".av
//			}
//		}
//	}.also {
//		AvFormatter().use {formatter->
//			println("MAP: $it")
//			println("FORMATTED: ${formatter.format(it)}")
//		}
//	}
//
//	val point=map {
//		"x" entry 1.0f.av
//		"y" entry 2.0f.av
//	}
//	val serializedPoint:AvNode
//	measureTime {
//		serializedPoint=AvSerializer().serialize(point)
//	}.also {
//		println("Serialization time: $it")
//	}
//	AvFormatter().use {formatter->
//		println("POINT: ${formatter.format(serializedPoint)}")
//	}

	/*
	{
	  "user": {
		"id": 12345,
		"name": "Alice",
		"email": "alice@example.com",
		"age": 21,
		"preferences": {
		  "theme": "dark",
		  "language": "ru",
		  "notifications": {
			"email": true,
			"push": false
		  }
		}
	  },
	  "history": [
		{
		  "date": "2025-03-02",
		  "action": "login"
		},
		{
		  "date": "2025-03-01",
		  "action": "update_settings"
		}
	  ]
	}

	*/
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