package edu.iest.examen_2ndo_20852

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Opciones.db"

        private const val SQL_CREATE_ENTRIES =
            "CREATE TABLE ${DatabaseContract.OpcionEntry.TABLE_NAME} (" +
                    "${DatabaseContract.OpcionEntry.COLUMN_ID} INTEGER PRIMARY KEY," +
                    "${DatabaseContract.OpcionEntry.COLUMN_NOMBRE} TEXT)"

        private const val SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS ${DatabaseContract.OpcionEntry.TABLE_NAME}"
    }
}
