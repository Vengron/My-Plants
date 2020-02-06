package cz.vengron.myplants.settings

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cz.vengron.myplants.database.PlantsDatabaseDao

class SettingsViewModel(
    application: Application
) : AndroidViewModel(application)