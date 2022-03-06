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

class BlockAdapter(val context: Context, val blocks: ArrayList<Block>) :
    RecyclerView.Adapter<BlockAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.recycler_block, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val block = blocks.get(position)
        holder.textView.text = block.title

        val inflater =  context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        for (item in block.items) {
            val tv = inflater.inflate(R.layout.block_item, null)

            tv.findViewById<TextView>(R.id.block_item_name).setText(item)
            holder.linearLayout.addView(tv)
        }
    }

    override fun getItemCount(): Int {
        return blocks.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView = view.findViewById<TextView>(R.id.recycler_block_name)
        val linearLayout = view.findViewById<LinearLayout>(R.id.recycler_block_lin)
    }
}