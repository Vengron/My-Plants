package cz.vengron.myplants.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PlantsDatabaseDao {

    @Insert
    fun insert(plant: Plant)

    @Update
    fun update(plant: Plant)

    @Query("SELECT * FROM plants_table WHERE plantId = :key")
    fun get(key: Long): Plant?

    @Query("SELECT * FROM plants_table ORDER BY plantId DESC")
    fun getAllPlants(): LiveData<List<Plant>>
}