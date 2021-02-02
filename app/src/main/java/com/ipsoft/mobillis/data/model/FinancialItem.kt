package com.ipsoft.mobillis.data.model

import java.math.BigDecimal
import java.util.*

/**
 *
 *  Author:     Anthoni Ipiranga
 *  Project:    Mobillis
 *  Date:       02/02/2021
 */

data class FinancialItem(
    var id: Long = 0L,
    val value: BigDecimal = 0.0.toBigDecimal(),
    val description: String = "",
    val data: Date = Date(),
    val isDone: Boolean,
    val type: Type
)

enum class Type {
    RECEITA, DESPESA
}
