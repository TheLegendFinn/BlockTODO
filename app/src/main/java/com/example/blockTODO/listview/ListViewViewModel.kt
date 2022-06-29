package com.example.blockTODO.listview

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.blockTODO.Item
import com.example.blockTODO.database.DatabaseHandler

/**
 * ViewModel for the [ListViewActivity]
 * @property itemLiveData Live-Data List of Items
 */
class ListViewViewModel(application: Application, val blockId: Int) : AndroidViewModel(application) {
    val itemLiveData: LiveData<List<Item>>
        get() = DatabaseHandler.getItemsByBlock(getApplication(), blockId)
}