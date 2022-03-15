package com.example.shoppinglist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.shoppinglist.adapters.BlockAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    //Animations for FABs
    private val rotateMainFabFwd: Animation by lazy {
        AnimationUtils.loadAnimation(
            this,
            R.anim.rotate_main_fab_fwd
        )
    }
    private val rotateMainFabBwd: Animation by lazy {
        AnimationUtils.loadAnimation(
            this,
            R.anim.rotate_main_fab_bwd
        )
    }
    private val expandFabsFwd: Animation by lazy {
        AnimationUtils.loadAnimation(
            this,
            R.anim.expand_fabs_fwd
        )
    }
    private val expandFabsBwd: Animation by lazy {
        AnimationUtils.loadAnimation(
            this,
            R.anim.expand_fabs_bwd
        )
    }

    //Boolean to track FAB-State
    private var isExpanded = false

    var blockList = ArrayList<Block>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Define the RecyclerView and connect it to the adapter
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_main)
        recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        blockList = getBlocks()
        val blockAdapter = BlockAdapter(this, blockList)
        recyclerView.adapter = blockAdapter

        //Define OnClick-Listeners for the FABs
        findViewById<FloatingActionButton>(R.id.main_fab).setOnClickListener { onMainFABClick() }
        findViewById<FloatingActionButton>(R.id.edit_blocks_fab).setOnClickListener {
            //TODO: Collapse FABs?
            //Start EditBlocksActivity
            val intent = Intent(this@MainActivity, EditBlocksActivity::class.java).apply {}
            startActivity(intent)
        }
        findViewById<FloatingActionButton>(R.id.quick_list_fab).setOnClickListener {
            //TODO: Implement QuickList Feature
        }
    }

    /**
     * Get all Blocks from the database
     * @return A List of all Blocks
     */
    private fun getBlocks(): ArrayList<Block> {
        var list = ArrayList<Block>()
        val t = Thread(Runnable { list = DatabaseHandler.getBlocks(this) as ArrayList<Block> })
        t.start()
        t.join() //TODO: Inefficient

        return list
    }

    /**
     * onClick-Handler for the main FAB
     */
    private fun onMainFABClick() {
        val mainFAB = findViewById<FloatingActionButton>(R.id.main_fab)
        val quickListFAB = findViewById<FloatingActionButton>(R.id.quick_list_fab)
        val editBlocksFAB = findViewById<FloatingActionButton>(R.id.edit_blocks_fab)

        if (!isExpanded) {
            quickListFAB.visibility = View.VISIBLE
            editBlocksFAB.visibility = View.VISIBLE

            mainFAB.startAnimation(rotateMainFabFwd)
            quickListFAB.startAnimation(expandFabsFwd)
            editBlocksFAB.startAnimation(expandFabsFwd)
        } else {
            mainFAB.startAnimation(rotateMainFabBwd)
            quickListFAB.startAnimation(expandFabsBwd)
            editBlocksFAB.startAnimation(expandFabsBwd)

            quickListFAB.visibility = View.GONE
            editBlocksFAB.visibility = View.GONE
        }
        isExpanded = !isExpanded
    }
}