package cz.vengron.myplants.encyclopedia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import cz.vengron.myplants.R
import cz.vengron.myplants.databinding.EncyclopediaFragmentBinding

class EncyclopediaFragment : Fragment( ){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: EncyclopediaFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.encyclopedia_fragment, container, false)
        val manager = GridLayoutManager(activity, 3)
        binding.plantsList.layoutManager = manager
        return binding.root
    }
}