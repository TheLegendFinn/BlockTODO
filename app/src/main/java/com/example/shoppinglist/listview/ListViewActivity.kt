package com.example.shoppinglist.listview

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.shoppinglist.Item
import com.example.shoppinglist.R
import com.example.shoppinglist.ViewModelFactory
import com.example.shoppinglist.database.DatabaseHandler
import kotlinx.coroutines.launch

class ListViewActivity : AppCompatActivity() {
    private var blockId = 0;
    private lateinit var viewModel: ListViewViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        blockId = intent.getIntExtra("blockId", 0)
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(application, blockId)
        ).get(ListViewViewModel::class.java)

        //Handle New Items
        findViewById<Button>(R.id.list_view_add_button).setOnClickListener {
            //Get Input
            val editText = findViewById<EditText>(R.id.list_view_item_input)

            //Get Item from Input
            val itemText = editText.text.toString()

            //Filter Empty Items
            if (itemText == "") {
                return@setOnClickListener
            }

            //Clear Input
            editText.setText("")

            //Create Item and add to Database
            val item = Item(itemText, blockId)
            lifecycleScope.launch {
                DatabaseHandler.insertItem(this@ListViewActivity, item)
            }

        }

        //Delete Button
        findViewById<ImageButton>(R.id.list_view_delete_all_button).setOnClickListener {
            lifecycleScope.launch {
                DatabaseHandler.deleteByBlock(this@ListViewActivity, blockId)
            }
        }

        //Observe the LiveData for the Items
        viewModel.itemLiveData.observe(this) { items -> setItems(items) }

        //Set title
        findViewById<TextView>(R.id.list_view_block_name).text = intent.getStringExtra("blockName")
    }

    /**
     * Adds a List of Items to the LinearLayout of the Activity
     * @param items List of items to add
     */
    fun setItems(items: List<Item>) {
        val inflater = this.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val linearLayout = findViewById<LinearLayout>(R.id.list_view_linear_layout)

        linearLayout.removeAllViews()

        for (item in items) {
            val view = inflater.inflate(R.layout.block_item_check, null)
            val cb = view.findViewById<CheckBox>(R.id.block_item_check_name)
            cb.setText(item.text)
            cb.setTag(R.id.tag_id, item.id)
            linearLayout.addView(view)
        }
    }

    fun onCheckboxClicked(view: View) {
        //Toast.makeText(this, "Item clicked: ${view.id}", Toast.LENGTH_LONG).show()
        lifecycleScope.launch {
            DatabaseHandler.deleteItem(this@ListViewActivity, view.getTag(R.id.tag_id) as Int)
        }

    }
}