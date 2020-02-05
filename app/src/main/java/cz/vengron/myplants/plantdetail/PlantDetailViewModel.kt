package cz.vengron.myplants.plantdetail

import androidx.lifecycle.MediatorLiveData

import androidx.lifecycle.ViewModel
import cz.vengron.myplants.database.Plant
import cz.vengron.myplants.database.PlantsDatabaseDao
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit


class PlantDetailViewModel(plantKey: Long, val database: PlantsDatabaseDao) : ViewModel() {

    private val viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val plant = MediatorLiveData<Plant>()

    fun getPlant() = plant

    init {
        plant.addSource(database.getPlantWithId(plantKey), plant::setValue)
    }

    fun onWateredButtonClick() {
        plant.value?.let {
            it.timeForWatering = System.currentTimeMillis() +
                    TimeUnit.DAYS.toMillis(it.wateringInterval)
        }
        uiScope.launch {
            update(plant.value!!)
        }
    }

    private suspend fun update(plant: Plant) {
        withContext(Dispatchers.IO) {
            database.update(plant)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}

