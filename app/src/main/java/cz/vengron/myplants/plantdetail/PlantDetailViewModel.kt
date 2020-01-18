package cz.vengron.myplants.plantdetail

import androidx.lifecycle.MediatorLiveData

import androidx.lifecycle.ViewModel
import cz.vengron.myplants.database.Plant
import cz.vengron.myplants.database.PlantsDatabaseDao
import kotlinx.coroutines.Job


class PlantDetailViewModel(plantKey: Long, val database: PlantsDatabaseDao) : ViewModel() {

    private val viewModelJob = Job()

    private val plant = MediatorLiveData<Plant>()

    fun getPlant() = plant

    init {
        plant.addSource(database.getPlantWithId(plantKey), plant::setValue)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}

