package com.example.shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

/**
 * This Activity is used to manage the users Blocks (Create/Delete)
 * TODO ViewModel?
 */
class EditBlocksActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_blocks)

        //Set OnClick-Listener for Add-Button
        findViewById<Button>(R.id.add_block_btn).setOnClickListener {
            val editBlockName = findViewById<EditText>(R.id.edit_block_name)

            //Save and Clear Input
            val blockName = editBlockName.text.toString()
            editBlockName.setText("")

            //Filter Empty Block-Names
            if(blockName == "") return@setOnClickListener

            //Create Block and add to Database
            val block = Block(blockName)
            lifecycleScope.launch {
                DatabaseHandler.insertBlock(this@EditBlocksActivity, block)
            }
        }
    }

}