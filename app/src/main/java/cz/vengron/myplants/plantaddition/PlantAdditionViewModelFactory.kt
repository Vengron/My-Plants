package cz.vengron.myplants.plantaddition

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cz.vengron.myplants.database.PlantsDatabaseDao

class PlantAdditionViewModelFactory(
    private val dataSource: PlantsDatabaseDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlantAdditionViewModel::class.java)) {
            return PlantAdditionViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
