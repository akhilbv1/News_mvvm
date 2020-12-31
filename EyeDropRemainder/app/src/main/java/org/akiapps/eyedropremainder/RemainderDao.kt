package org.akiapps.eyedropremainder

import androidx.room.*

@Dao
interface RemainderDao {

    @Query("SELECT * FROM ${MConstants.TABLE_REMAINDERS}")
    suspend fun getAllDropDetailsList():List<RemainderTable>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDropRemainder(dropRemainderTable: RemainderTable)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateDropRemainder(dropRemainderTable: RemainderTable)
}