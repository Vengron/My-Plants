package cz.vengron.myplants.plantaddition

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cz.vengron.myplants.database.Plant
import cz.vengron.myplants.database.PlantsDatabaseDao
import kotlinx.coroutines.*
import java.sql.Time
import java.util.concurrent.TimeUnit
import kotlin.math.round

class PlantAdditionViewModel(val database: PlantsDatabaseDao) : ViewModel() {

    private var viewModelJob = Job()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun addNewPlant(
        plantName: String,
        timeForWatering: Long,
        nameOfThePlant: String,
        wikiUrl: String
    ) {
        val plant = Plant()
        plant.plantName = plantName
        plant.timeForWatering = System.currentTimeMillis() +
                round(
                    TimeUnit.DAYS.toMillis(7).toDouble() /
                            TimeUnit.DAYS.toMillis(timeForWatering)
                )
                    .toLong()

        plant.nameOfThePlant = nameOfThePlant
        plant.wikiUrl = wikiUrl
        uiScope.launch {
            insert(plant)
        }
        _onNavigateToDetail.value = plant
    }

    private suspend fun insert(plant: Plant) {
        withContext(Dispatchers.IO) {
            database.insert(plant)
        }
    }

    private val _onNavigateToDetail = MutableLiveData<Plant>()
    val onNavigateToDetail: MutableLiveData<Plant>
        get() = _onNavigateToDetail

    fun onNavigateToDetail() {
        _onNavigateToDetail.value = null
    }
}
