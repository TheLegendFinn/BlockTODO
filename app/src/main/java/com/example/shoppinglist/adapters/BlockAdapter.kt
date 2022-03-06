package com.example.shoppinglist.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.Block
import com.example.shoppinglist.R

/**
 * Class connects the [Block]s with the RecyclerView of the MainActivity
 * @param context Activity Context
 * @param blocks List of Blocks to process
 */
class BlockAdapter(val context: Context, val blocks: ArrayList<Block>) :
    RecyclerView.Adapter<BlockAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.recycler_block, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Get the block and set Title
        val block = blocks.get(position)
        holder.blockName.text = block.title

        //LayoutInflater to inflate the Block Items
        val inflater =  context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        for (item in block.items) {
            //Inflate the Item-Layout
            val tv = inflater.inflate(R.layout.block_item, null)

            //Set Item-Text and attach to the LinearLayout
            tv.findViewById<TextView>(R.id.block_item_name).setText(item)
            holder.linearLayout.addView(tv)
        }
    }

    override fun getItemCount(): Int {
        return blocks.size
    }

    /**
     * Subclass that defines the ViewHolder for the Adapter
     * @param view View inside the ViewHolder
     * @property blockName Name of the Block
     * @property linearLayout LinearLayout to add the items into
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val blockName = view.findViewById<TextView>(R.id.recycler_block_name)
        val linearLayout = view.findViewById<LinearLayout>(R.id.recycler_block_lin)
    }
}