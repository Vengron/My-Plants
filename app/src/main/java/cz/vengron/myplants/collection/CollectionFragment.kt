package cz.vengron.myplants.collection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import cz.vengron.myplants.R
import cz.vengron.myplants.database.PlantsDatabase
import cz.vengron.myplants.databinding.CollectionFragmentBinding


/**
 * Collection of added plants displayed by RecyclerView with
 * onClickListener on every item to handle navigation to PlantDetailFragment
 */

class CollectionFragment : Fragment( ){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: CollectionFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.collection_fragment, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = PlantsDatabase.getInstance(application).plantsDatabaseDao

        val viewModelFactory = CollectionViewModelFactory(dataSource, application)

        val collectionViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(CollectionViewModel::class.java)

        binding.viewModel = collectionViewModel

        binding.lifecycleOwner = this

        collectionViewModel.navigateToPlantAddition.observe(this, Observer {
            if (it == true) {
                this.findNavController().navigate(R.id.plantAdditionFragment)
                collectionViewModel.onNavigatedToAddition()
            }
        })

        return binding.root
    }
}