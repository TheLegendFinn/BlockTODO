package com.example.blockTODO

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.blockTODO.adapters.BlockAdapter
import com.example.blockTODO.database.DatabaseHandler
import com.example.blockTODO.listview.ListViewActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {

    //ViewModel
    private lateinit var viewModel: MainViewModel

    //Boolean to track FAB-State
    private var isExpanded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initialise the ViewModel
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        //Define the RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_main)
        recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        //Create and connect the adapter
        val blockAdapter = BlockAdapter(this, object : BlockAdapter.OnItemClickListener {
            override fun onItemClick(view: View, blockId: Int) {
                val intent = Intent(this@MainActivity, ListViewActivity::class.java)
                intent.putExtra(
                    "blockName",
                    view.findViewById<TextView>(R.id.recycler_block_name).text
                )
                    .putExtra("blockId", blockId)
                startActivity(intent)
            }
        }, object : BlockAdapter.OnItemLongClickListener {
            override fun onItemLongClick(view: View, blockId: Int) {
                lifecycleScope.launch {
                    DatabaseHandler.deleteBlock(this@MainActivity, blockId)
                }
            }
        })
        recyclerView.adapter = blockAdapter

        //Observe the Block-LiveData
        viewModel.blockLiveData.observe(this) { blocks -> blockAdapter.setBlocks(blocks) }

        //Define OnClick-Listener for the FAB
        findViewById<FloatingActionButton>(R.id.edit_blocks_fab).setOnClickListener {
            //Start EditBlocksActivity
            val intent = Intent(this@MainActivity, EditBlocksActivity::class.java).apply {}
            startActivity(intent)
        }
    }
}