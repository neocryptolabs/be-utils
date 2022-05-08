package com.neocryptolabs.core.util

import kotlin.reflect.KClass
import kotlin.reflect.KProperty0
import kotlin.reflect.KType
import kotlin.reflect.full.createType
import kotlin.reflect.full.withNullability

val intType: KType = Int::class.createType()
val longType: KType = Long::class.createType()
val stringType: KType = String::class.createType()
val doubleType: KType = Double::class.createType()
val floatType: KType = Float::class.createType()

val nintType: KType = Int::class.createType().withNullability(true)
val nlongType: KType = Long::class.createType().withNullability(true)
val nstringType: KType = String::class.createType().withNullability(true)
val ndoubleType: KType = Double::class.createType().withNullability(true)
val nfloatType: KType = Float::class.createType().withNullability(true)

fun <T : Any> T.getClass(): KClass<T> {
    return javaClass.kotlin
}

@Throws(IllegalArgumentException::class)
fun validateOnceFields(field0: KProperty0<Any?>, field1: KProperty0<Any?>) {
    field0.get() ?: field1.get()
    ?: throw IllegalArgumentException("'${field0.name}' and '${field1.name}' fields are required at least!")
}

@Throws(Exception::class)
fun validateFields(vararg fields: KProperty0<Any?>) {
    validateAllFields(listOf(*fields))
}

@Throws(IllegalArgumentException::class)
private fun validateRequiredField(field: KProperty0<Any?>) {
    field.get() ?: throw IllegalArgumentException("'${field.name}' field is required!")
}

private fun validateAllFields(fields: List<KProperty0<Any?>>) {
    for (field in fields) {
        validateRequiredField(field)
    }
}

@Throws(IllegalArgumentException::class)
fun validateOnceFields(vararg fields: KProperty0<Any?>) {
    var count = 0
    for (field in fields) {
        field.get() ?: count++
    }
    if (fields.size == count) {
        throw IllegalArgumentException("'${fields}' fields are required at least!")
    }
}
