package com.example.shoppinglist

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shoppinglist.listview.ListViewViewModel

/**
 * ViewModelFactory currently used to create instances of [ListViewViewModel]
 * TODO Generalize
 */
class ViewModelFactory(val application: Application, val intParam: Int ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ListViewViewModel(application, intParam) as T
    }
}