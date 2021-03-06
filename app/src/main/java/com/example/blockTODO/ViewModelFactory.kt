package com.example.blockTODO

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.blockTODO.listview.ListViewViewModel

/**
 * ViewModelFactory currently used to create instances of [ListViewViewModel]
 */
class ViewModelFactory(val application: Application, val intParam: Int ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ListViewViewModel(application, intParam) as T
    }
}