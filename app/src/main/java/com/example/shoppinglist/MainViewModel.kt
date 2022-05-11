package com.example.shoppinglist

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * ViewModel for the MainActivity
 * @property blockLiveData LiveData-List of Blocks
 */
class MainViewModel(application: Application) : AndroidViewModel(application) {

    //List of the Blocks on the main page TODO Neccessary?
    var blockList: List<Block> = ArrayList<Block>()

    //Corresponding LiveData
    //TODO How does this connect to the blockList?
    val blockLiveData: LiveData<List<Block>>
        get() = DatabaseHandler.getBlocks(getApplication()) //TODO Better Solution?

}