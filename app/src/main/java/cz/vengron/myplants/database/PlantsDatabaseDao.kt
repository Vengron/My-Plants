package cz.vengron.myplants.database

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * Defines methods for using the [Plant] class with Room
 */

@Dao
interface PlantsDatabaseDao {

    @Insert
    fun insert(plant: Plant)

    @Update
    fun update(plant: Plant)

    @Query("SELECT * FROM plants_table ORDER BY plantId DESC")
    fun getAllPlants(): LiveData<List<Plant>>

    @Query("SELECT * FROM plants_table WHERE plantId = :key")
    fun getPlantWithId(key: Long): LiveData<Plant>

    @Query("SELECT * FROM plants_table ORDER BY plantId DESC limit 1")
    fun getLastPlant(): Plant
}