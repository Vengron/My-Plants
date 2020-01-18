package cz.vengron.myplants.plantaddition

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cz.vengron.myplants.database.Plant
import cz.vengron.myplants.database.PlantsDatabaseDao
import kotlinx.coroutines.*
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
        nameOfThePlant: String
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
        plant.imageUrl = urlsOfImages.getOrDefault(nameOfThePlant, "blankUrl")
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

    private val urlsOfImages = mapOf(
        Pair(
            "Plectranthus amboinicus",
            "https://upload.wikimedia.org/wikipedia/commons/1/1b/Leaf_-pani_koorkka.JPG"
        ),
        Pair(
            "Plectranthus scutellarioides",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0d/Starr_070830-8251_Solenostemon_scutellarioides.jpg/675px-Starr_070830-8251_Solenostemon_scutellarioides.jpg"
        ),
        Pair(
            "Aloe vera",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e8/Alo%C3%AB-vera-habitus.jpg/600px-Alo%C3%AB-vera-habitus.jpg"
        ),
        Pair(
            "Piper nigrum",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6f/Piper_longum_plant.jpg/800px-Piper_longum_plant.jpg"
        ),
        Pair(
            "Spathiphyllum cochlearispathum",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bd/Spathiphyllum_cochlearispathum_RTBG.jpg/800px-Spathiphyllum_cochlearispathum_RTBG.jpg"
        )
    )
}
