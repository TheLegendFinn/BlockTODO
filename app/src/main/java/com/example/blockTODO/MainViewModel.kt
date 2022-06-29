package com.example.blockTODO

import android.app.Application
import androidx.lifecycle.*
import com.example.blockTODO.database.DatabaseHandler

/**
 * ViewModel for the MainActivity
 * @property blockLiveData LiveData-List of Blocks
 */
class MainViewModel(application: Application) : AndroidViewModel(application) {
    val blockLiveData: LiveData<List<Block>>
        get() = DatabaseHandler.getBlocks(getApplication()) //TODO Better Solution?

}