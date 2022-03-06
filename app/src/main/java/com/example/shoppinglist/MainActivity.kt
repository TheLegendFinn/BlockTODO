package com.example.shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.adapters.BlockAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Define the RecyclerView and connect it to the adapter
        val recyclerView = findViewById<RecyclerView>(R.id.recylcer_view_main)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        val blockAdapter = BlockAdapter(this, getList())
        recyclerView.adapter = blockAdapter

    }

    //Generates a List of Strings for Testing Purposes
    private fun getList(): ArrayList<String> {
        val list = ArrayList<String>()
        for (i in 1..5) {
            list.add("Block $i")
        }
        return list
    }
}