package com.ipsoft.mobillis.repository.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ipsoft.mobillis.data.model.FinancialItem
import com.ipsoft.mobillis.repository.sqlite.DATABASE_NAME
import com.ipsoft.mobillis.repository.sqlite.DATABASE_VERSION

/**
 *
 *  Author:     Anthoni Ipiranga
 *  Project:    Mobillis
 *  Date:       03/02/2021
 */
@Database(entities = [FinancialItem::class], version = DATABASE_VERSION)
abstract class ItemDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao

    companion object {
        private var instance: ItemDatabase? = null

        fun getDatabase(context: Context): ItemDatabase {
            if (instance == null) {
                instance == Room.databaseBuilder(
                    context.applicationContext,
                    ItemDatabase::class.java,
                    DATABASE_NAME
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return instance as ItemDatabase
        }

        fun destroyInstance() {
            instance = null
        }
    }
}