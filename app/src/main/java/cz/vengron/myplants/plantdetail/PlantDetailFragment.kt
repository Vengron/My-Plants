package cz.vengron.myplants.plantdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import cz.vengron.myplants.R
import cz.vengron.myplants.database.PlantsDatabase
import cz.vengron.myplants.databinding.PlantDetailFragmentBinding


class PlantDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: PlantDetailFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.plant_detail_fragment, container, false
        )

        val application = requireNotNull(this.activity).application

        val dataSource = PlantsDatabase.getInstance(application).plantsDatabaseDao

        val arguments = PlantDetailFragmentArgs.fromBundle(arguments!!)

        val viewModelProvider = PlantDetailViewModelFactory(arguments.plantKey, dataSource)

        val detailViewModel = ViewModelProviders.of(
            this, viewModelProvider
        ).get(PlantDetailViewModel::class.java)

        binding.viewModel = detailViewModel

        binding.lifecycleOwner = this

        return binding.root
    }
}
