package edu.iest.examen_2ndo_20852

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var Gatos: ImageView? = null
    private var Perfil: ImageView? = null
    private var Configuracion: ImageView? = null
    private var Apagar: ImageView? = null

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        IniciarVistas()
        clicks()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_images, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.option_menu_list_images -> {
                val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val activeNetwork = cm.activeNetworkInfo
                if (activeNetwork != null && activeNetwork.isConnected) {
                    Toast.makeText(this,
                        "Está conectado a una red", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "No está conectado a una red", Toast.LENGTH_SHORT).show()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }



    private fun clicks() {
        Gatos?.setOnClickListener(this)
        Perfil?.setOnClickListener(this)
        Configuracion?.setOnClickListener(this)
        Apagar?.setOnClickListener(this)

    }

    private fun IniciarVistas() {
        Gatos = findViewById(R.id.imagen_gato)
        Perfil = findViewById(R.id.imagen_perfil)
        Configuracion = findViewById(R.id.imagen_configurar)
        Apagar = findViewById(R.id.imagen_apagar)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.imagen_apagar -> {
                finish()
            }
            R.id.imagen_perfil -> {}
        }
    }

}