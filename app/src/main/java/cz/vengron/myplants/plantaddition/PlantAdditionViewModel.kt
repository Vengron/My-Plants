package cz.vengron.myplants.plantaddition

import androidx.lifecycle.ViewModel
import cz.vengron.myplants.database.PlantsDatabaseDao

class PlantAdditionViewModel(val dataSource: PlantsDatabaseDao) : ViewModel() {

    enum class NameOfPlants(latinName: String, imageUrl: String) {
        RYMOVNIK(
            "Plectranthus amboinicus",
            "https://upload.wikimedia.org/wikipedia/commons/1/1b/Leaf_-pani_koorkka.JPG"
        ),
        KOPRIVA(
            "Plectranthus scutellarioides",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0d/Starr_070830-8251_Solenostemon_scutellarioides.jpg/675px-Starr_070830-8251_Solenostemon_scutellarioides.jpg"
        ),
        ALOE(
            "Aloe vera",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e8/Alo%C3%AB-vera-habitus.jpg/600px-Alo%C3%AB-vera-habitus.jpg"
        ),
        PEPROVNIK(
            "Piper",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6f/Piper_longum_plant.jpg/800px-Piper_longum_plant.jpg"
        ),
        LOPATKOVEC(
            "Spathiphyllum",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bd/Spathiphyllum_cochlearispathum_RTBG.jpg/800px-Spathiphyllum_cochlearispathum_RTBG.jpg"
        )
    }
}
