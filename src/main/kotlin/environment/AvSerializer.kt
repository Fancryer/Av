package environment

import ast.*
import ast.AvBool.Companion.av
import ast.AvFloat.Companion.av
import ast.AvInt.Companion.av
import ast.AvString.Companion.av
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1
import kotlin.reflect.KType
import kotlin.reflect.full.*

class AvSerializer
{
	fun serialize(any:Any?):AvNode
	{
		if(any==null) return AvNil
		if(any is AvNode) return any

		val clazz=any::class
		val props=clazz.declaredMemberProperties.filter {prop->
			val field=runCatching {clazz.java.getDeclaredField(prop.name)}.getOrNull()
			val hasPropertyAnnotation=prop.annotations.any {it is AvSerializable}
			val hasFieldAnnotation=field?.isAnnotationPresent(AvSerializable::class.java) ?: false

			println("Property: ${prop.name}, PROPERTY Annotations: ${prop.annotations}, FIELD Annotations: ${field?.annotations?.toList()}")

			hasPropertyAnnotation||hasFieldAnnotation
		}



		if(props.isEmpty())
		{
			println("No serializable properties found!")
		}

		val serializedProps=props.map {prop->
			AvPlainMapEntry(
				prop.name.av,
				when(val value=prop.get(@Suppress("TYPE_MISMATCH") any))
				{
					is Iterable<*>->value.map {serialize(it)}
						.filterIsInstance<AvListEntry>()
						.let(::AvList)

					is ByteArray->AvBytes(value.map {AvHexInt(it.toInt().toBigInteger())})
					is Array<*>->
					{
						if(value.all {it is UByte})
							AvBytes(value.map {AvHexInt((it as UByte).toInt().toBigInteger())})
						else
							error("Unsupported array type: ${prop.returnType}")
					}

					is Boolean->value.av
					is String->value.av
					is Int->value.av
					is Float->value.av
					else->TODO("Unsupported property type: ${prop.returnType}")
				}
			)
		}

		return AvMap(serializedProps).apply {
			this.props+="class".av to (clazz.qualifiedName?.av ?: AvNil)
		}
	}


	private inline fun <reified T> isIterable(prop:KProperty1<out Any,T>):Boolean=
		prop isSubtypeOf Iterable::class

	private inline fun <reified T> isArray(prop:KProperty1<out Any,T>):Boolean=
		prop isSubtypeOf Array::class||prop isSubtypeOf ByteArray::class


	private inline fun <reified T> isBoolean(prop:KProperty1<out Any,T>):Boolean=
		prop isSubtypeOf Boolean::class

	private inline fun <reified T> isString(prop:KProperty1<out Any,T>):Boolean=
		prop isSubtypeOf String::class

	private inline fun <reified T> isInt(prop:KProperty1<out Any,T>):Boolean=
		prop isSubtypeOf Int::class

	private inline fun <reified T> isFloat(prop:KProperty1<out Any,T>):Boolean=
		prop isSubtypeOf Float::class

	private inline infix fun <reified T,reified C:Any> KProperty1<out Any,T>.isSubtypeOf(clazz:KClass<C>):Boolean=
		returnType.isSubtypeOf(clazz.createType())
}

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS,AnnotationTarget.PROPERTY,AnnotationTarget.VALUE_PARAMETER,AnnotationTarget.FIELD)
annotation class AvSerializable

@AvSerializable
class Point(
	@field:AvSerializable
	val x:Int,
	@field:AvSerializable
	val y:Int
)