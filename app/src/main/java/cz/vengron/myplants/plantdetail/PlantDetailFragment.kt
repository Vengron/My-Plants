package cz.vengron.myplants.plantdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import cz.vengron.myplants.R
import cz.vengron.myplants.database.PlantsDatabase
import cz.vengron.myplants.databinding.PlantDetailFragmentBinding


class PlantDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: PlantDetailFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.plant_detail_fragment, container, false
        )

        val application = requireNotNull(this.activity).application

        val dataSource = PlantsDatabase.getInstance(application).plantsDatabaseDao

        val arguments = PlantDetailFragmentArgs.fromBundle(requireArguments())

        val viewModelProvider = PlantDetailViewModelFactory(arguments.plantKey, dataSource)

        val detailViewModel = ViewModelProvider(
            this, viewModelProvider
        )[PlantDetailViewModel::class.java]

        binding.viewModel = detailViewModel

        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.nav_view)
        bottomNavigationView?.visibility = View.GONE
    }

    override fun onPause() {
        super.onPause()
        val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.nav_view)
        bottomNavigationView?.visibility = View.VISIBLE
    }
}
