package com.example.stammtisch

import android.os.Bundle
import android.text.InputType
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.stammtisch.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    //define needed variables for Controls and an Adapter to connect them
    private lateinit var lvToDoList : ListView
    private lateinit var addButton: FloatingActionButton
    private lateinit var itemList : ArrayList<String>
    private lateinit var itemAdapter: ArrayAdapter<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // implementation of the functionality - first connect the variables with the respective control IDs
        lvToDoList = findViewById(R.id.toDoList)
        addButton = findViewById(R.id.addToListButton)
        itemList = ArrayList()

        // mock data
        //itemList.add("Apfel")
        //itemList.add("Banane")

        // die itemList mit einer graphischen item list verbinden
        itemAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, itemList)

        // toDoList den itemAdapter zuweisen:
        lvToDoList.adapter = itemAdapter

        lvToDoList.setOnItemLongClickListener(AdapterView.OnItemLongClickListener { _, _, pos, _ ->
            itemList.removeAt(pos)
            itemAdapter.notifyDataSetChanged()
            if(!itemList.isEmpty()) {
                Toast.makeText(applicationContext, "Eintrag gelöscht!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "Alles erledigt!", Toast.LENGTH_SHORT).show()
            }

            true
        })

        addButton.setOnClickListener {

            var builder = AlertDialog.Builder(this)
            builder.setTitle("Bestellung")

            var input = EditText(this)
            input.hint = "Bestellung eingeben"
            input.inputType = InputType.TYPE_CLASS_TEXT
            builder.setView(input)

            builder.setPositiveButton("OK") { _, _ ->
                if(!input.text.toString().isEmpty()) {
                    itemList.add(input.text.toString())
                    itemAdapter.notifyDataSetChanged()
                    //var okayText = input.text.toString() + " " + "Text einfügen"
                    var okayText = "Eintrag hinzugefügt!"
                    Toast.makeText(applicationContext, okayText, Toast.LENGTH_SHORT).show()
                }
            }

            builder.setNegativeButton("Abbruch") { _, _ ->
                Toast.makeText(applicationContext, "Abgebrochen", Toast.LENGTH_SHORT).show()
            }

            builder.show()
        }

        // lvToDoList.add

    }
}
