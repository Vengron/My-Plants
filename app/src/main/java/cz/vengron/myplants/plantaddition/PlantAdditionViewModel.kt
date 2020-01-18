package cz.vengron.myplants.plantaddition

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cz.vengron.myplants.database.Plant
import cz.vengron.myplants.database.PlantsDatabaseDao
import kotlinx.coroutines.*

class PlantAdditionViewModel(val database: PlantsDatabaseDao) : ViewModel() {

    private var viewModelJob = Job()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun addNewPlant(plant: Plant) {
        uiScope.launch {
            insert(plant)
        }
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
