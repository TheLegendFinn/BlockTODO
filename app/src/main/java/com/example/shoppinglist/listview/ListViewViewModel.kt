package com.example.shoppinglist.listview

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.shoppinglist.Item
import com.example.shoppinglist.database.DatabaseHandler

/**
 * ViewModel for the [ListViewActivity]
 * @property itemLiveData Live-Data List of Items
 */
class ListViewViewModel(application: Application, val blockId: Int) : AndroidViewModel(application) {
    val itemLiveData: LiveData<List<Item>>
        get() = DatabaseHandler.getItemsByBlock(getApplication(), blockId)
}