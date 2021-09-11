package cz.vengron.myplants.schedule

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import cz.vengron.myplants.R
import cz.vengron.myplants.database.PlantsDatabase
import cz.vengron.myplants.databinding.ScheduleFragmentBinding

/**
 * Fragment used for displaying times of watering the added plants
 */

class ScheduleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: ScheduleFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.schedule_fragment, container, false
        )

        val application = requireNotNull(this.activity).application

        val dataSource = PlantsDatabase.getInstance(application).plantsDatabaseDao

        val viewModelFactory = ScheduleViewModelFactory(dataSource, application)

        val scheduleViewModel = ViewModelProvider(this, viewModelFactory)[ScheduleViewModel::class.java]

        binding.viewModel = scheduleViewModel

        binding.lifecycleOwner = this

        scheduleViewModel.navigateToPlantDetail.observe(viewLifecycleOwner, {
            it?.let {
                this.findNavController().navigate(
                    ScheduleFragmentDirections.actionScheduleFragmentToPlantDetailFragment(it)
                )
                scheduleViewModel.onNavigatedToDetail()
            }
        })

        val adapter = ScheduleAdapter(ScheduleListener { plantId ->
            scheduleViewModel.onPlantClicked(plantId)
        })

        binding.wateringList.adapter = adapter

        scheduleViewModel.plants.observe(viewLifecycleOwner, {
            it?.let {
                adapter.submitList(it)
            }
        })

        return binding.root
    }
}