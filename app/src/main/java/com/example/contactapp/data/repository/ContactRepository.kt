package com.example.contactapp.data.repository

import com.example.contactapp.data.DataAccessObj.ContactDao
import com.example.contactapp.data.entities.Contact

class ContactRepository(
    private  val  dao: ContactDao
){
    suspend fun  upsertContact(contact: Contact)=
        dao.upsertContact(contact)

    suspend fun  deleteContact(contact: Contact) =
        dao.upsertContact(contact)

    fun  getContacts () = dao.getContacts()






}