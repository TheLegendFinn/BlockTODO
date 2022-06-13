package com.example.shoppinglist.listview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shoppinglist.R

// the fragment initialization parameters
private const val ARG_ITEM_NAME = "item_name"

/**
 * Fragment used in ListViewActivity to display Items
 * Use the [ListViewItemFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListViewItemFragment : Fragment() {
    private var itemName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            itemName = it.getString(ARG_ITEM_NAME)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_view_item, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param itemName Name of the Item
         * @return A new instance of fragment ListViewItemFragment.
         */
        @JvmStatic
        fun newInstance(itemName: String) =
            ListViewItemFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_ITEM_NAME, itemName)
                }
            }
    }
}