package com.example.contactapp.data.DataBase

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.contactapp.data.ContactDataBase

object DataBaseInit {

  private  var db: ContactDataBase? = null
    fun getDatabase(context: Context): ContactDataBase{

        if(db == null){
            db = Room.databaseBuilder(
                context,
                ContactDataBase::class.java,
                "contact_database"
            ).build()

        }
        return db!!
    }

    }

