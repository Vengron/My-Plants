package cz.vengron.myplants.plantaddition

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import cz.vengron.myplants.R
import cz.vengron.myplants.database.PlantsDatabase
import cz.vengron.myplants.databinding.PlantAdditionFragmentBinding

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
            additionViewModel.addNewPlant(
                binding.plantNameEdit.text.toString(),
                binding.timeForWateringEdit.text.toString().toLong(),
                binding.nameOfThePlantEdit.selectedItem.toString()
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
