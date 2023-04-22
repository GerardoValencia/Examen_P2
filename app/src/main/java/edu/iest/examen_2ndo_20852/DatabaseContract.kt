package edu.iest.examen_2ndo_20852
import android.provider.BaseColumns


object DatabaseContract {

        object OpcionEntry : BaseColumns {
            const val TABLE_NAME = "opciones"
            const val COLUMN_NOMBRE = "nombre"
        }

        const val SQL_CREATE_ENTRIES =
            "CREATE TABLE ${OpcionEntry.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                    "${OpcionEntry.COLUMN_NOMBRE} TEXT)"

        const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${OpcionEntry.TABLE_NAME}"
}

