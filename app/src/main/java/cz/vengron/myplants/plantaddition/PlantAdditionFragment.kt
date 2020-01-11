package cz.vengron.myplants.plantaddition

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
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
            val plant = Plant()
            plant.plantName = binding.plantNameEdit.text.toString()
            plant.timeForWatering = System.currentTimeMillis() +
                    TimeUnit.DAYS.toMillis(binding.timeForWateringEdit.text.toString().toLong())
            plant.nameOfThePlant = binding.nameOfThePlantEdit.selectedItem.toString()
            additionViewModel.addNewPlant(plant)
        }

        return binding.root
    }
}
