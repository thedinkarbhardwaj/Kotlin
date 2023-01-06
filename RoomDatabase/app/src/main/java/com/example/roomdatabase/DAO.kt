package com.example.roomdatabase

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DAO {

    @Insert
    suspend fun insert(model: Contact?)

    // below method is use to update
    // the data in our database.
    @Update()
    suspend fun update(model: Contact?)

    // below line is use to delete a
    // specific contact in our database.
    @Delete
    suspend fun delete(model: Contact?)

    @Query("UPDATE contact SET name=:name,phone_number=:phone_number WHERE name=:name")
    suspend fun update_with_query(name:String,phone_number:Long)

    // on below line we are making query to
    // delete all Contact from our database.
    @Query("DELETE FROM contact")
    suspend fun deleteAllContact()

    // below line is to read all the contact from our database.
    // in this we are ordering our courses in ascending order
    // with our course name.
    @Query("SELECT * FROM contact ORDER BY name ASC")
    fun getAllContact(): LiveData<List<Contact?>?>?
}