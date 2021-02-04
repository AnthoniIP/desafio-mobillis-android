package com.ipsoft.mobillis.repository.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ipsoft.mobillis.data.model.FinancialItem
import com.ipsoft.mobillis.repository.sqlite.*
/**
 *
 *  Author:     Anthoni Ipiranga
 *  Project:    Mobillis
 *  Date:       03/02/2021
 */

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: FinancialItem): Long

    @Update
    fun update(item: FinancialItem): Int

    @Delete
    fun delete(vararg items: FinancialItem): Int

    @Query("SELECT * FROM $TABLE_CONTROL WHERE $COLUMN_ID = :id")
    fun itemById(id: Long): LiveData<FinancialItem>

    @Query("""SELECT * FROM $TABLE_CONTROL ORDER BY $COLUMN_ID""")
    fun getAllItem(): LiveData<List<FinancialItem>>

}