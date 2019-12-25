package cz.vengron.myplants.collection

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cz.vengron.myplants.database.PlantsDatabaseDao

class CollectionViewModel(
    val database: PlantsDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    private val _navigateToPlantDetail = MutableLiveData<Long>()

    private val _navigateToPlantAddition = MutableLiveData<Boolean>()
    val navigateToPlantAddition: LiveData<Boolean>
        get() = _navigateToPlantAddition

    fun onAddButtonClick() {
        _navigateToPlantAddition.value = true
    }

    fun onNavigatedToAddition() {
        _navigateToPlantAddition.value = false
    }
}