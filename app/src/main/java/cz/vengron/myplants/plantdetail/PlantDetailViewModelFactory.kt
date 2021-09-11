package cz.vengron.myplants.plantdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cz.vengron.myplants.database.PlantsDatabaseDao

class PlantDetailViewModelFactory(
    private val plantKey: Long,
    private val dataSource: PlantsDatabaseDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlantDetailViewModel::class.java)) {
            return PlantDetailViewModel(plantKey, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}