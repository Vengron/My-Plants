package cz.vengron.myplants.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import cz.vengron.myplants.R
import cz.vengron.myplants.databinding.ScheduleFragmentBinding

/**
 * Fragment used for displaying times of watering the added plants
 */

class ScheduleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: ScheduleFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.schedule_fragment, container, false
        )

        return binding.root
    }
}