package org.akiapps.eyedropremainder

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RemainderTable::class],version = 1)
abstract class RemainderDataBase:RoomDatabase() {
    abstract fun remainderDao():RemainderDao

     object DatabaseCreator{
        private val TAG:String = DatabaseCreator::class.java.simpleName
        private lateinit var db:RemainderDataBase

        fun getRemainderDataBase(applicationContext: Context):RemainderDataBase{
            db = Room.databaseBuilder(
                applicationContext,
                RoomDatabase::class.java, MConstants.DATABASE_NAME
            ).build() as RemainderDataBase
            return db
        }
    }

}