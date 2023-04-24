package com.example.sharedpreferences.models

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sharedpreferences.MainActivity
import com.example.sharedpreferences.R
import com.example.sharedpreferences.adapters.JuegoAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar


class JuegosActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: JuegoAdapter
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_juegos)

        val prefs = getSharedPreferences("preferencias", Context.MODE_PRIVATE)
        val nombre = prefs.getString("nombre", "No se ha guardado el nombre")
        val edad = prefs.getInt("edad", 0)
        val dinero = prefs.getFloat("dinero", 0f)

        // Configuramos el RecyclerView
        recyclerView = findViewById(R.id.recyclerViewJuegos)
        recyclerView.setHasFixedSize(true)

        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        val adapter = JuegoAdapter(generarJuegos(), edad)
        recyclerView.adapter = adapter
        val fabVolver: FloatingActionButton = findViewById(R.id.fabVolver)
        fabVolver.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        // Configuramos el botón de preferencias
        val fab: FloatingActionButton = findViewById(R.id.fabPreferencias)
        fab.setOnClickListener { view ->
            val mensaje = "Nombre: $nombre, Edad: $edad\nDinero en Monedero: $dinero"

            Snackbar.make(view, mensaje, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_juegos, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_horizontal -> {
                recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
                true
            }
            R.id.menu_vertical -> {
                recyclerView.layoutManager = LinearLayoutManager(this)
                true
            }
            R.id.menu_2_columnas -> {
                recyclerView.layoutManager = GridLayoutManager(this, 2)
                true
            }
            R.id.menu_3_columnas -> {
                recyclerView.layoutManager = GridLayoutManager(this, 3)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun generarJuegos(): ArrayList<Juego> {
        val juegos = ArrayList<Juego>()

        juegos.add(
            Juego(
                "Super Mario Odyssey",
                "Nintendo Switch",
                1200.00,
                "E",
                R.drawable.super_mario_odyssey
            )
        )
        juegos.add(
            Juego(
                "The Legend of Zelda: Breath of the Wild",
                "Nintendo Switch",
                1200.00,
                "E10+",
                R.drawable.zelda_breath_of_the_wild

            )
        )
        juegos.add(
            Juego(
                "Pokemon Sword",
                "Nintendo Switch",
                1200.00,
                "E",
                R.drawable.pokemon_sword
            )
        )
        juegos.add(
            Juego(
                "Minecraft",
                "Multiplataforma",
                400.00,
                "E10+",
                R.drawable.minecraft
            )
        )
        juegos.add(
            Juego(
                "Grand Theft Auto V",
                "Multiplataforma",
                600.00,
                "M",
                R.drawable.gta_v
            )
        )

        return juegos
    }
}
