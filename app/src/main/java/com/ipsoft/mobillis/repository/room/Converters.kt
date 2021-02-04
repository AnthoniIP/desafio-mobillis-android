package com.ipsoft.mobillis.repository.room

import androidx.room.TypeConverter
import com.ipsoft.mobillis.data.model.Type
import java.math.BigDecimal
import java.util.*

/**
 *
 *  Author:     Anthoni Ipiranga
 *  Project:    Mobillis
 *  Date:       04/02/2021
 */

class Converters {
    @TypeConverter
    fun stringToDate(string: String?): Date? {
        return string?.let { Date(it) }
    }

    @TypeConverter
    fun dateToString(date: Date?): String? {
        return date?.let { it.toString() }
    }

    @TypeConverter
    fun doubleToBigDecimal(value: Double?): BigDecimal? {
        return value?.let { it.toBigDecimal() }
    }

    @TypeConverter
    fun bigDecimalToDoule(value: BigDecimal?): Double? {
        return value?.let { it.toDouble() }
    }

    @TypeConverter
    fun typeToInt(type: Type?): Int? {
        return type?.let {
            when (it) {
                Type.INCOME -> 1
                Type.COST -> 2
            }
        }
    }

    @TypeConverter
    fun intToType(int: Int?): Type? {
        return int?.let {
            when (it) {
                1 -> Type.INCOME
                2 -> Type.COST
                else -> Type.INCOME
            }
        }
    }
}