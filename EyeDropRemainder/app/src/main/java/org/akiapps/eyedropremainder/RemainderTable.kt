package org.akiapps.eyedropremainder

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.akiapps.eyedropremainder.MConstants.COLUMN_DROP_GAP
import org.akiapps.eyedropremainder.MConstants.COLUMN_DROP_NEXT_ALARM
import org.akiapps.eyedropremainder.MConstants.COLUMN_DROP_NUMBER
import org.akiapps.eyedropremainder.MConstants.TABLE_REMAINDERS

@Entity(tableName = TABLE_REMAINDERS)
data class RemainderTable(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = COLUMN_DROP_NUMBER) val dropNumber: Int,
    @ColumnInfo(name = COLUMN_DROP_GAP) val dropGap: Int,
    @ColumnInfo(name = COLUMN_DROP_NEXT_ALARM) val time: Long
)