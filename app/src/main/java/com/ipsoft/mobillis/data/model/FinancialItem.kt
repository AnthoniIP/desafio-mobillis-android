package com.ipsoft.mobillis.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ipsoft.mobillis.repository.sqlite.COLUMN_ID
import com.ipsoft.mobillis.repository.sqlite.TABLE_CONTROL
import java.math.BigDecimal
import java.util.*

/**
 *
 *  Author:     Anthoni Ipiranga
 *  Project:    Mobillis
 *  Date:       02/02/2021
 */

@Entity(tableName = TABLE_CONTROL)
data class FinancialItem(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ID)
    var id: Long = 0,
    var value: BigDecimal = 0.0.toBigDecimal(),
    var description: String = "",
    var data: Date = Date(),
    var isDone: Boolean = false,
    val type: Type = Type.DESPESA
)

enum class Type {
    RECEITA, DESPESA
}
