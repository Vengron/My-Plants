package cz.vengron.myplants.database

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * Defines methods for using the[Plant] class with Room
 */

@Dao
interface PlantsDatabaseDao {

    @Insert
    fun insert(plant: Plant)

    @Update
    fun update(plant: Plant)

    /**
     * Method used in case of clicking on item in RecyclerView
     * on EncyclopediaFragment
     * @param [key] is [Plant.plantId]
     */
    @Query("SELECT * FROM plants_table WHERE plantId = :key")
    fun get(key: Long): Plant?

    /**
     * Method used for getting all plants for RecyclerView
     */
    @Query("SELECT * FROM plants_table ORDER BY plantId DESC")
    fun getAllPlants(): LiveData<List<Plant>>
}