package cz.vengron.myplants.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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

        val application = requireNotNull(this.activity).application

        val viewModelFactory = SettingsViewModelFactory(application)

        val scheduleViewModel = ViewModelProvider(this, viewModelFactory)
            .get(SettingsViewModel::class.java)

        binding.viewModel = scheduleViewModel

        binding.lifecycleOwner = this

        binding.darkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            when (isChecked) {
                true -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                else -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        return binding.root
    }
}