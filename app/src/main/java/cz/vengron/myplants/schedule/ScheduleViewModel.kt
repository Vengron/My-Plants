package cz.vengron.myplants.schedule

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cz.vengron.myplants.database.PlantsDatabaseDao

class ScheduleViewModel(
    val database: PlantsDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    var plants = database.getAllPlants()

    private val _navigateToPlantDetail = MutableLiveData<Long>()

    val navigateToPlantDetail: LiveData<Long>
        get() = _navigateToPlantDetail

    fun onPlantClicked(plantId: Long) {
        _navigateToPlantDetail.value = plantId
    }

    fun onNavigatedToDetail() {
        _navigateToPlantDetail.value = null
    }
}