package cz.vengron.myplants.plantaddition

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cz.vengron.myplants.database.Plant
import cz.vengron.myplants.database.PlantsDatabaseDao
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit

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
        plant.timeForWatering = System.currentTimeMillis() + TimeUnit.DAYS.toMillis(timeForWatering)
        plant.wateringInterval = timeForWatering
        plant.nameOfThePlant = nameOfThePlant
        val urls = listOfUrls.first { it.nameOfThePlant == nameOfThePlant }
        plant.imageUrl = urls.urlOfImage
        plant.wikiUrl = urls.urlOfWiki
        uiScope.launch {
            insert(plant)
            _onNavigateToDetail.value = getLastPlant()
        }
    }

    private suspend fun getLastPlant(): Plant {
        var lastPlant: Plant
        withContext(Dispatchers.IO) {
            lastPlant = database.getLastPlant()
        }
        return lastPlant
    }

    private suspend fun insert(plant: Plant) {
        withContext(Dispatchers.IO) {
            database.insert(plant)
        }
    }

    private val _onNavigateToDetail = MutableLiveData<Plant?>()
    val onNavigateToDetail: MutableLiveData<Plant?>
        get() = _onNavigateToDetail

    fun onNavigateToDetail() {
        _onNavigateToDetail.value = null
    }

    data class Urls(val nameOfThePlant: String, val urlOfImage: String, val urlOfWiki: String)

    private val listOfUrls = listOf(
        Urls(
            "Plectranthus amboinicus",
            "https://upload.wikimedia.org/wikipedia/commons/1/1b/Leaf_-pani_koorkka.JPG",
            "https://en.wikipedia.org/wiki/Plectranthus_amboinicus"
        ),
        Urls(
            "Plectranthus scutellarioides",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0d/Starr_070830-8251_Solenostemon_scutellarioides.jpg/675px-Starr_070830-8251_Solenostemon_scutellarioides.jpg",
            "https://en.wikipedia.org/wiki/Plectranthus_scutellarioides"
        ),
        Urls(
            "Aloe vera",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e8/Alo%C3%AB-vera-habitus.jpg/600px-Alo%C3%AB-vera-habitus.jpg",
            "https://en.wikipedia.org/wiki/Aloe_vera"
        ),
        Urls(
            "Piper nigrum",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6f/Piper_longum_plant.jpg/800px-Piper_longum_plant.jpg",
            "https://en.wikipedia.org/wiki/Black_pepper"
        ),
        Urls(
            "Spathiphyllum cochlearispathum",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bd/Spathiphyllum_cochlearispathum_RTBG.jpg/800px-Spathiphyllum_cochlearispathum_RTBG.jpg",
            "https://en.wikipedia.org/wiki/Spathiphyllum_cochlearispathum"
        )
    )
}
