package com.koto.sqliteankoplayground

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class MySqlHelper(context: Context) : ManagedSQLiteOpenHelper(context, "mydb") {

    companion object {
        private var instance: MySqlHelper? = null

        @Synchronized
        fun getInstance(context: Context): MySqlHelper {
            if (instance == null) {
                instance = MySqlHelper(context.applicationContext)
            }

            return instance!! // todo
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(
                "Person", true,
                "_id" to INTEGER + PRIMARY_KEY,
                "name" to TEXT,
                "surname" to TEXT,
                "age" to INTEGER)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}

val Context.database: MySqlHelper
    get() = MySqlHelper.getInstance(applicationContext)