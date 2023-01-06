package com.example.roomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.lifecycle.Observer
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var contactdatabaseclass:ContactDatabase

    lateinit var name:EditText
    lateinit var phone:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        name = findViewById(R.id.name)
        phone = findViewById(R.id.phone)

    }

    fun insert_btn(view: View) {

        var name_data = name.text.toString()
        var phone_data = phone.text.toString()

        contactdatabaseclass = ContactDatabase.getDatabase(applicationContext)

        GlobalScope.launch {
            contactdatabaseclass.contactDao().insert(Contact(0,name_data,phone_data.toLong()));
        }

    }
    fun update_with_query_btn(view: View) {

        var name_data = name.text.toString()
        var phone_data = phone.text.toString()

        contactdatabaseclass = ContactDatabase.getDatabase(applicationContext)

        GlobalScope.launch {
            contactdatabaseclass.contactDao().update_with_query(name_data,phone_data.toLong());
        }

    }
    fun read_btn(view: View) {
        this.contactdatabaseclass = ContactDatabase.getDatabase(applicationContext)

            contactdatabaseclass.contactDao().getAllContact()?.observe(this, Observer {
                Log.d("data",it.toString())
            })

    }
    fun delete_btn(view: View) {

        var name_data = name.text.toString()
        var phone_data = phone.text.toString()

        contactdatabaseclass = ContactDatabase.getDatabase(applicationContext)

        GlobalScope.launch {
            contactdatabaseclass.contactDao().delete(Contact(11,"hi",3456765436));

        }
    }

    fun delete_all_data_btn(view: View) {

        contactdatabaseclass = ContactDatabase.getDatabase(applicationContext)

        GlobalScope.launch {
            contactdatabaseclass.contactDao().deleteAllContact();
        }
    }

    fun update_btn(view: View) {

        contactdatabaseclass = ContactDatabase.getDatabase(applicationContext)

        GlobalScope.launch {
            contactdatabaseclass.contactDao().update(Contact(11,"hi",3456765436))
        }
    }
}