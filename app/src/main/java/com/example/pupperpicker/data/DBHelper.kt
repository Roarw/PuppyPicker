package com.example.pupperpicker.data

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "PUPPY_PICKER_DB"
        private const val DATABASE_VERSION = 1
        const val TABLE_NAME = "dog_table"
        const val ID_COL = "id"
        const val URL_COL = "url"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(("CREATE TABLE $TABLE_NAME ($ID_COL INTEGER PRIMARY KEY, $URL_COL TEXT)"))
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun createURL(url : String) {
        try {
            // Create URL
            val values = ContentValues()
            values.put(URL_COL, url)

            val db = this.writableDatabase

            db.insert(TABLE_NAME, null, values)
            db.close()

        } catch (e: Exception) {
            // Exception
            Log.w("DataBase", e.toString())
        }
    }

    fun doesURLExist(url : String): Boolean {
        try {
            // Check if URL exists
            val db = this.writableDatabase

            val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME WHERE $URL_COL = '$url'", null)
            val exists = cursor != null && cursor.count > 0

            cursor.close()
            db.close()
            return exists

        } catch (e: Exception) {
            // Exception
            Log.w("DataBase", e.toString())
            return false
        }
    }

    fun deleteURL(url : String) {
        try {
            // Delete URL
            val db = this.writableDatabase

            db.delete(TABLE_NAME, "$URL_COL = ?", arrayOf(url))
            db.close()

        } catch (e: Exception) {
            // Exception
            Log.w("DataBase", e.toString())
        }
    }

    @SuppressLint("Range")
    fun getAllURLs(): List<String> {
        try {
            // Get all URLs
            val db = this.readableDatabase
            val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)

            val list = mutableListOf<String>()
            if (cursor.moveToFirst()) {
                do {
                    list += cursor.getString(cursor.getColumnIndex(URL_COL))
                } while (cursor.moveToNext())
            }

            cursor.close()
            db.close()
            return list

        } catch (e: Exception) {
            // Exception
            Log.w("DataBase", e.toString())
            return listOf()
        }
    }
}