package com.example.contactapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.contactapp.data.DataAccessObj.ContactDao
import com.example.contactapp.data.entities.Contact


@Database(entities = [Contact::class], exportSchema = true , version = 2)

abstract  class ContactDataBase : RoomDatabase() {
    abstract  val  dao : ContactDao


}