package com.ipsoft.mobillis.repository.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.ipsoft.mobillis.data.model.FinancialItem
import com.ipsoft.mobillis.data.model.Type
import com.ipsoft.mobillis.repository.ItemRepository
import java.util.*

/**
 *
 *  Author:     Anthoni Ipiranga
 *  Project:    Mobillis
 *  Date:       02/02/2021
 */

class SQLiteRepository(ctx: Context) : ItemRepository {

    private val helper: ControleFinanceiroHelper = ControleFinanceiroHelper(ctx)

    private fun insert(financialItem: FinancialItem) {

        val tipe = if (financialItem.type == Type.RECEITA) 1 else 2
        val isDone = if (financialItem.isDone) 1 else 0

        val db = helper.writableDatabase
        val cv = ContentValues().apply {
            put(COLUMN_VALUE, financialItem.value.toDouble())
            put(COLUMN_DESCRIPTION, financialItem.description)
            put(COLUMN_DATE, financialItem.data.toString())
            put(COLUMN_IS_DONE, isDone)
            put(COLUMN_TIPE, tipe)
        }
        val id = db.insert(TABLE_CONTROL, null, cv)
        if (id != -1L) {
            financialItem.id = id
        }
        db.close()


    }

    private fun update(financialItem: FinancialItem) {

        val tipe = if (financialItem.type == Type.RECEITA) 1 else 2
        val isDone = if (financialItem.isDone) 1 else 0

        val db = helper.writableDatabase
        val cv = ContentValues().apply {
            put(COLUMN_ID, financialItem.id)
            put(COLUMN_VALUE, financialItem.value.toDouble())
            put(COLUMN_DESCRIPTION, financialItem.description)
            put(COLUMN_DATE, financialItem.data.toString())
            put(COLUMN_IS_DONE, isDone)
            put(COLUMN_TIPE, tipe)

        }
        db.insertWithOnConflict(
            TABLE_CONTROL,
            null,
            cv,
            SQLiteDatabase.CONFLICT_REPLACE
        )
        db.close()

    }

    override fun save(item: FinancialItem) {
        if (item.id != 0L) {
            insert(item)
        } else {
            update(item)
        }
    }


    override fun remove(vararg financialItems: FinancialItem) {
        val db = helper.writableDatabase
        for (item in financialItems) {
            db.delete(
                TABLE_CONTROL,
                "$COLUMN_ID = ?",
                arrayOf(item.id.toString())
            )
            db.close()
        }
    }

    override fun itemById(id: Long, callback: (FinancialItem?) -> Unit) {
        val sql = "SELECT * FROM $TABLE_CONTROL WHERE $COLUMN_ID = ?"
        val db = helper.readableDatabase
        val cursor = db.rawQuery(sql, arrayOf(id.toString()))
        val item = if (cursor.moveToNext()) itemFromCursor(cursor) else null
        callback(item)
    }

    private fun itemFromCursor(cursor: Cursor): FinancialItem {
        val id = cursor.getLong(
            cursor.getColumnIndex(COLUMN_ID)
        )
        val value = cursor.getDouble(
            cursor.getColumnIndex(COLUMN_VALUE)
        )

        val description = cursor.getString(
            cursor.getColumnIndex(COLUMN_DESCRIPTION)
        )
        val date = cursor.getString(
            cursor.getColumnIndex(COLUMN_DATE)
        )
        val isDone = cursor.getInt(
            cursor.getColumnIndex(COLUMN_IS_DONE)
        )
        val type = cursor.getInt(
            cursor.getColumnIndex(COLUMN_TIPE)
        )

        val typeConverted = if (type == 1) Type.RECEITA else Type.DESPESA
        val isDoneConverted = isDone == 1





        return FinancialItem(
            id,
            value.toBigDecimal(),
            description,
            Date(date),
            isDoneConverted,
            typeConverted
        )
    }
}