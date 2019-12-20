package cz.vengron.myplants.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Table of [PlantsDatabase] which persistently saves info about one plant with help of Room.
 */

@Entity(tableName = "plants_table")
data class Plant(

    @PrimaryKey(autoGenerate = true)
    var plantId: Long = 0L,

    @ColumnInfo(name = "plant_name")
    var plantName: String = "Plant name",

    @ColumnInfo(name = "name_of_the_plant")
    var nameOfThePlant: String = "Name of the plant",

    @ColumnInfo(name = "time_for_watering")
    var timeForWatering: Long = System.currentTimeMillis() + 60000,

    @ColumnInfo(name = "encyclopedia_info")
    var encyclopediaInfo: String = "encyclopedia info"
)
