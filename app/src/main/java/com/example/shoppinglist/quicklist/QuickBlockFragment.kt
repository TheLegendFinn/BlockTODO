package com.example.shoppinglist.quicklist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shoppinglist.R

private const val ARG_NAME = "blockName"

/**
 * Fragment to Select a Block for an [Item] in QuickListActivity
 * Use the [QuickBlockFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuickBlockFragment : Fragment() {
    private var blockName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            blockName = it.getString(ARG_NAME)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quick_block, container, false)
    }

    companion object {
        /**
         * Method to create a new instance of this Fragment
         * @param blockName Name of the Block assigned to this Fragment
         * @return A new instance of fragment QuickBlockFragment.
         */
        @JvmStatic
        fun newInstance(blockName: String) =
            QuickBlockFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_NAME, blockName)
                }
            }
    }
}