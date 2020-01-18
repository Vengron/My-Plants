package cz.vengron.myplants.plantaddition

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.SpinnerAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import cz.vengron.myplants.R
import cz.vengron.myplants.database.Plant
import cz.vengron.myplants.database.PlantsDatabase
import cz.vengron.myplants.databinding.PlantAdditionFragmentBinding
import java.util.concurrent.TimeUnit

class PlantAdditionFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val urlsOfImages = mapOf(
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

        val binding: PlantAdditionFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.plant_addition_fragment, container, false
        )
        val application = requireNotNull(this.activity).application

        val dataSource = PlantsDatabase.getInstance(application).plantsDatabaseDao

        val viewModelProvider = PlantAdditionViewModelFactory(dataSource)

        val additionViewModel = ViewModelProviders.of(
            this, viewModelProvider
        ).get(PlantAdditionViewModel::class.java)

        binding.viewModel = additionViewModel
        binding.lifecycleOwner = this

        binding.doneButton.setOnClickListener {
            val nameOfThePlant = binding.nameOfThePlantEdit.selectedItem.toString()
            additionViewModel.addNewPlant(
                binding.plantNameEdit.text.toString(),
                binding.timeForWateringEdit.text.toString().toLong(),
                nameOfThePlant,
                urlsOfImages.getOrDefault(nameOfThePlant, "blankUrl")
            )
        }

        additionViewModel.onNavigateToDetail.observe(this, Observer { plant ->
            plant?.let {
                this.findNavController().navigate(
                    PlantAdditionFragmentDirections
                        .actionPlantAdditionFragmentToPlantDetailFragment(plant.plantId)
                )
                additionViewModel.onNavigateToDetail()
            }

        })

        ArrayAdapter.createFromResource(
            this.context!!,
            R.array.names_of_plants,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.nameOfThePlantEdit.adapter = adapter
        }

        return binding.root
    }
}
