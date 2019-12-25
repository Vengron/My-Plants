package cz.vengron.myplants.plantaddition

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import cz.vengron.myplants.R
import cz.vengron.myplants.databinding.CollectionFragmentBinding
import cz.vengron.myplants.databinding.PlantAdditionFragmentBinding

class PlantAdditionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: PlantAdditionFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.plant_addition_fragment, container, false
        )
        return binding.root
    }
}
