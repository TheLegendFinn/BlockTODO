package com.example.blockTODO.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.blockTODO.Block
import com.example.blockTODO.R
import com.example.blockTODO.database.DatabaseHandler

/**
 * Class connects the [Block]s with the RecyclerView of the MainActivity
 * @param context Activity Context
 * @param itemClickListener OnClickListener for the Items
 */
class BlockAdapter(val context: Context, private val itemClickListener: OnItemClickListener, private val itemLongClickListener: OnItemLongClickListener) :
    RecyclerView.Adapter<BlockAdapter.ViewHolder>() {

    /**
     * Interface that defines the OnClickListener for the RecyclerView Items
     */
    interface OnItemClickListener {
        fun onItemClick(view: View, blockId: Int)
    }

    /**
     * Interface that defines the OnLongClickListener for the RecyclerView Items
     */
    interface OnItemLongClickListener {
        fun onItemLongClick(view: View, blockId: Int)
    }

    private var blocks: List<Block> = ArrayList<Block>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //Define the viewHolder
        val container = LayoutInflater.from(context).inflate(R.layout.recycler_block, parent, false)
        val viewHolder = ViewHolder(container)

        //Define the OnClickListener
        viewHolder.view.setOnClickListener {
            itemClickListener.onItemClick(it, this.blocks[viewHolder.adapterPosition].id)
        }

        //Define the OnLongClickListener
        viewHolder.view.setOnLongClickListener {
            itemLongClickListener.onItemLongClick(it, this.blocks[viewHolder.adapterPosition].id)
            return@setOnLongClickListener true
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Get the block and set Title
        val block = blocks[position]
        holder.blockName.text = block.name

        //LayoutInflater to inflate the Block Items
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        //Get Items
        DatabaseHandler.getItemsByBlock(context, block.id).observe(context as LifecycleOwner) {
            //Remove every child except blockName
            holder.linearLayout.removeViews(1, holder.linearLayout.childCount - 1)

            //re-add children
            for (item in it) {
                //Inflate the Item-Layout
                val tv = inflater.inflate(R.layout.block_item, null)

                //Set Item-Text and attach to the LinearLayout
                tv.findViewById<TextView>(R.id.block_item_name).setText(item.text)
                holder.linearLayout.addView(tv)
            }
        }
    }

    override fun getItemCount(): Int {
        return blocks.size
    }

    /**
     * Setter Function for the Adapter DataSet
     * @param blockList DataSet to use
     */
    fun setBlocks(blockList: List<Block>) {
        this.blocks = blockList
        notifyDataSetChanged()
    }

    /**
     * Subclass that defines the ViewHolder for the Adapter
     * @param view View inside the ViewHolder
     * @property blockName Name of the Block
     * @property linearLayout LinearLayout to add the items into
     */
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val blockName = view.findViewById<TextView>(R.id.recycler_block_name)
        val linearLayout = view.findViewById<LinearLayout>(R.id.recycler_block_lin)
    }
}