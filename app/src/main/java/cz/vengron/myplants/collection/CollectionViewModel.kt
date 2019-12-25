package cz.vengron.myplants.collection

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import cz.vengron.myplants.database.PlantsDatabaseDao

class CollectionViewModel(
    val database: PlantsDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    val navigateToPlantDetailFragment = MutableLiveData<Long>()
    val navigateToAddPlantFragment = MutableLiveData<Boolean>()
}