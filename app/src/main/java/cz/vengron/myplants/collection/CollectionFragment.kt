package cz.vengron.myplants.collection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import cz.vengron.myplants.R
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
        return binding.root
    }
}