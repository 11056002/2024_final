package imd.ntub.mybottomnav

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject
import java.io.File

class ContactFileManager(private val context: Context) {
    private val fileName = "contacts.json"

    fun saveContacts(contacts: List<Contact>) {
        val jsonArray = JSONArray()
        for (contact in contacts) {
            val jsonObject = JSONObject()
            jsonObject.put("name", contact.name)
            jsonObject.put("phone", contact.phone)
            jsonArray.put(jsonObject)
        }

        val file = File(context.filesDir, fileName)
        file.writeText(jsonArray.toString())
    }

    fun loadContacts(): MutableList<Contact> {
        val file = File(context.filesDir, fileName)
        if (!file.exists()) {
            return mutableListOf()
        }

        val jsonArray = JSONArray(file.readText())
        val contacts = mutableListOf<Contact>()
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val name = jsonObject.getString("name")
            val phone = jsonObject.getString("phone")
            contacts.add(Contact(name, phone))
        }

        return contacts
    }
}
