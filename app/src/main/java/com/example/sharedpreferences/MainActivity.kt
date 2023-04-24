package com.example.sharedpreferences

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import com.example.sharedpreferences.models.JuegosActivity

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var switchGuardar: Switch
    private lateinit var editTextNombre: EditText
    private lateinit var editTextEdad: EditText
    private lateinit var editTextAltura: EditText
    private lateinit var editTextDinero: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        switchGuardar = findViewById(R.id.switchGuardar)
        editTextNombre = findViewById(R.id.editTextNombre)
        editTextEdad = findViewById(R.id.editTextEdad)
        editTextAltura = findViewById(R.id.editTextAltura)
        editTextDinero = findViewById(R.id.editTextDinero)
        val botonGuardar = findViewById<Button>(R.id.botonGuardar)

        sharedPreferences = getSharedPreferences("preferencias", Context.MODE_PRIVATE)

        // Habilitar o deshabilitar el botón según el estado del Switch
        switchGuardar.setOnCheckedChangeListener { _, isChecked ->
            botonGuardar.isEnabled = isChecked
        }

        botonGuardar.setOnClickListener {
            // Verificar que se ha habilitado el Switch de guardar
            if (switchGuardar.isChecked) {
                // Obtener los valores introducidos por el usuario
                val nombre = editTextNombre.text.toString()
                val edad = editTextEdad.text.toString().toInt()
                val altura = editTextAltura.text.toString().toFloat()
                val dinero = editTextDinero.text.toString().toFloat()


                // Guardar los valores en SharedPreferences
                editor = sharedPreferences.edit()
                editor.putString("nombre", nombre)
                editor.putInt("edad", edad)
                editor.putFloat("altura", altura)
                editor.putFloat("dinero", dinero)
                editor.apply()
                val intent = Intent(this, JuegosActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Habilite el Switch para guardar los datos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()

        // Mostrar los valores guardados en SharedPreferences
        editTextNombre.setText(sharedPreferences.getString("nombre", ""))
        editTextEdad.setText(sharedPreferences.getInt("edad", 0).toString())
        editTextAltura.setText(sharedPreferences.getFloat("altura", 0f).toString())
        editTextDinero.setText(sharedPreferences.getFloat("dinero", 0f).toString())
    }
}
