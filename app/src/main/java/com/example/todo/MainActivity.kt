package com.example.todo

import android.R
import android.content.Intent
import android.os.Bundle
import android.util.SparseBooleanArray
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.todo.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var intermediate = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // array list for items
        var itemlist = arrayListOf<String>()
        // adapter
        var adapter = ArrayAdapter<String>(this, R.layout.simple_list_item_multiple_choice, itemlist)

        // add item to list when add button is pressed
        binding.add.setOnClickListener {
            // add to itemlist
            itemlist.add(binding.editText.text.toString())
            // associating the adapter to listView
            binding.listView.adapter =  adapter
            // notifyDataSetChanged() called as the itemlist is updated
            adapter.notifyDataSetChanged()
            // clear the input space
            binding.editText.text.clear()
        }

        binding.edit.setOnClickListener {
            // checked item in list
            val position: SparseBooleanArray = binding.listView.checkedItemPositions
            // no.of items in list
            val count = binding.listView.count
            var item = count - 1
            // removing the items based on position booleanArray
            while (item >= 0) {
                if (position.get(item)) {
                    intermediate = item
                    binding.editText.setText(itemlist.get(item)).toString()
                    break
                }
                item--
            }
            position.clear()
            adapter.notifyDataSetChanged()
        }

        binding.update.setOnClickListener {
            adapter.remove(itemlist.get(intermediate))
            adapter.insert(binding.editText.text.toString(),intermediate)
            adapter.notifyDataSetChanged()
            // clear the input space
            binding.editText.text.clear()
        }

        // selecting and deleting items in list when delete button is pressed
        binding.delete.setOnClickListener {
            // checked items in list
            val position: SparseBooleanArray = binding.listView.checkedItemPositions
            // no.of items in list
            val count = binding.listView.count
            var item = count - 1
            // removing the items based on position booleanArray
            while (item >= 0) {
                if (position.get(item))
                {
                    adapter.remove(itemlist.get(item))
                }
                item--
            }
            position.clear()
            adapter.notifyDataSetChanged()
        }
    }

    fun sendMessage(view: View?) {
        val intent = Intent(this@MainActivity, People::class.java)
        startActivity(intent)
    }
}