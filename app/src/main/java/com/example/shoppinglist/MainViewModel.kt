package com.example.shoppinglist

import android.app.Application
import androidx.lifecycle.*
import com.example.shoppinglist.database.DatabaseHandler

/**
 * ViewModel for the MainActivity
 * @property blockLiveData LiveData-List of Blocks
 */
class MainViewModel(application: Application) : AndroidViewModel(application) {
    val blockLiveData: LiveData<List<Block>>
        get() = DatabaseHandler.getBlocks(getApplication()) //TODO Better Solution?

}