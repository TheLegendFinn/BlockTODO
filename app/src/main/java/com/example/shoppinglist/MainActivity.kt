package com.example.shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.shoppinglist.adapters.BlockAdapter
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Define the RecyclerView and connect it to the adapter
        val recyclerView = findViewById<RecyclerView>(R.id.recylcer_view_main)
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        val blockAdapter = BlockAdapter(this, getList())
        recyclerView.adapter = blockAdapter

    }

    //Generates a List of Strings for Testing Purposes
    private fun getList(): ArrayList<Block> {
        val list = ArrayList<Block>()

        for (i in 1..5) {
            val items = ArrayList<String>()
            var j = Random.nextInt(1, 5)
            while (j > 0) {
                items.add("Item $j")
                j--
            }
            val block = Block("Block $i", items)
            list.add(block)
        }

        return list
    }
}