package cz.vengron.myplants.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import cz.vengron.myplants.R
import cz.vengron.myplants.databinding.SettingsFragmentBinding

class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: SettingsFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.settings_fragment, container, false)

        return super.onCreateView(inflater, container, savedInstanceState)
    }
}