package edu.iest.examen_2ndo_20852

import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

import edu.iest.examen_2ndo_20852.DatabaseContract.OpcionEntry

class ConfiguracionActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper
    private lateinit var dbHandler: DatabaseHandler

    // Obtener referencias a las vistas
    lateinit var opcion1EditText: EditText
    lateinit var opcion2EditText: EditText
    lateinit var opcion3EditText: EditText
    lateinit var opcion4EditText: EditText
    lateinit var guardarFAB: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.configuracion_activity)

        dbHelper = DatabaseHelper(this)
        dbHandler = DatabaseHandler(this)

        // Obtener los valores actuales de las opciones guardadas en la base de datos
        val opcionesGuardadas = obtenerOpcionesGuardadas()

        // Obtener referencias a las vistas
        opcion1EditText = findViewById(R.id.opcion1EditText)
        opcion2EditText = findViewById(R.id.opcion2EditText)
        opcion3EditText = findViewById(R.id.opcion3EditText)
        opcion4EditText = findViewById(R.id.opcion4EditText)
        guardarFAB = findViewById(R.id.guardarFAB)

        // Mostrar los valores actuales en las vistas correspondientes
        opcion1EditText.setText(opcionesGuardadas[0])
        opcion2EditText.setText(opcionesGuardadas[1])
        opcion3EditText.setText(opcionesGuardadas[2])
        opcion4EditText.setText(opcionesGuardadas[3])

        // Configurar el listener del botón guardar
        guardarFAB.setOnClickListener {
            // Obtener los valores de las vistas
            val opcion1 = opcion1EditText.text.toString().trim()
            val opcion2 = opcion2EditText.text.toString().trim()
            val opcion3 = opcion3EditText.text.toString().trim()
            val opcion4 = opcion4EditText.text.toString().trim()

            // Guardar los valores en la base de datos
            guardarOpciones(opcion1, opcion2, opcion3, opcion4)

            // Mostrar un mensaje de éxito
            Toast.makeText(this, "Opciones guardadas correctamente", Toast.LENGTH_SHORT).show()
        }
    }

    private fun obtenerOpcionesGuardadas(): List<String> {
        val db = dbHelper.readableDatabase

        val projection = arrayOf(
            DatabaseContract.OpcionEntry.COLUMN_NOMBRE
        )

        val cursor = db.query(
            DatabaseContract.OpcionEntry.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null
        )

        val opcionesGuardadas = mutableListOf<String>()

        with(cursor) {
            while (moveToNext()) {
                val nombre =
                    getString(getColumnIndexOrThrow(DatabaseContract.OpcionEntry.COLUMN_NOMBRE))
                opcionesGuardadas.add(nombre)
            }
        }

        cursor.close()

        return opcionesGuardadas
    }

    private fun guardarOpciones() {
        val db = dbHelper.writableDatabase
        val opcion1 = opcion1EditText.text.toString().trim()
        val opcion2 = opcion2EditText.text.toString().trim()
        val opcion3 = opcion3EditText.text.toString().trim()
        val opcion4 = opcion4EditText.text.toString().trim()

        // Verificar que se hayan ingresado todos los campos
        if (opcion1.isNotEmpty() && opcion2.isNotEmpty() && opcion3.isNotEmpty() && opcion4.isNotEmpty()) {
            // Crear un objeto ContentValues para almacenar los valores a insertar en la base de datos
            val values = ContentValues().apply {
                put(DatabaseContract.OpcionEntry.COLUMN_NOMBRE, opcion1)
            }
            // Insertar los valores de la opción 1 en la tabla opciones
            db.insert(DatabaseContract.OpcionEntry.TABLE_NAME, null, values)

            // Repetir el proceso para las opciones 2, 3 y 4
            values.clear()
            values.put(DatabaseContract.OpcionEntry.COLUMN_NOMBRE, opcion2)
            db.insert(DatabaseContract.OpcionEntry.TABLE_NAME, null, values)

            values.clear()
            values.put(DatabaseContract.OpcionEntry.COLUMN_NOMBRE, opcion3)
            db.insert(DatabaseContract.OpcionEntry.TABLE_NAME, null, values)

            values.clear()
            values.put(DatabaseContract.OpcionEntry.COLUMN_NOMBRE, opcion4)
            db.insert(DatabaseContract.OpcionEntry.TABLE_NAME, null, values)

            // Cerrar la conexión a la base de datos
            db.close()

            // Mostrar un mensaje de éxito
            Toast.makeText(this, "Opciones guardadas correctamente", Toast.LENGTH_SHORT).show()

            // Actualizar las opciones en la actividad principal
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            // Mostrar un mensaje de error si no se han ingresado todos los campos
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
        }
    }
}
