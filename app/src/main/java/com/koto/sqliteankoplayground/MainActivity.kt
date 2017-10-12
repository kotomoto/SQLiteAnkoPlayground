package com.koto.sqliteankoplayground

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.db.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addPersonButton.setOnClickListener {
            database.use {
                insert("Person",
                        "_id" to 1,
                        "name" to "John",
                        "surname" to "Smith",
                        "age" to 20)
            }
        }

        selectPersonButton.setOnClickListener {
            database.use {
                val result = select("Person").whereSimple("(_id = ?) and (name = ?)", 1.toString(), "John")
                val person = result.parseSingle(parser)
                updateView(person)
            }
        }
    }

    private fun updateView(person: Person) {
        with(person) {
            chosenPerson.text = "$id, $name, $surname, $age"
        }
    }
}

data class Person(val id: Int, val name: String, val surname: String, val age: Int)

val parser = rowParser { id: Int, name: String, surname: String, age: Int -> Person(id, name, surname, age) }