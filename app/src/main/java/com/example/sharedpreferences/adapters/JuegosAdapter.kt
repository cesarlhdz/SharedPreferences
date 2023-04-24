package com.example.sharedpreferences.adapters

import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.sharedpreferences.R
import com.example.sharedpreferences.models.Juego


class JuegoAdapter(private val juegos: List<Juego>, private val edad: Int) :
    RecyclerView.Adapter<JuegoAdapter.JuegoViewHolder>() {

    class JuegoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imagen: ImageView = itemView.findViewById(R.id.imagenJuego)
        val nombre: TextView = itemView.findViewById(R.id.nombreJuego)
        val consola: TextView = itemView.findViewById(R.id.consolaJuego)
        val precio: TextView = itemView.findViewById(R.id.precioJuego)
        val clasificacion: TextView = itemView.findViewById(R.id.clasificacionJuego)
        val botonComprar: Button = itemView.findViewById(R.id.botonComprar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JuegoViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_juego, parent, false)
        return JuegoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: JuegoViewHolder, position: Int) {
        val juego = juegos[position]

        holder.imagen.setImageResource(juego.imagen)
        holder.nombre.text = juego.nombre
        holder.nombre.setTextColor(Color.BLUE)
        holder.consola.text = juego.consola
        holder.precio.text = holder.itemView.context.getString(R.string.precio_format, juego.precio)
        holder.precio.setTypeface(null, Typeface.BOLD)
        holder.clasificacion.text = juego.clasificacionEdad
        when (juego.clasificacionEdad) {
            "E" -> { // Para cualquier edad
                holder.botonComprar.isEnabled = true
            }
            "E10+" -> { // Para 10 años o más
                holder.botonComprar.isEnabled = edad >= 10
            }
            "M" -> { // Para mayores de 17 años
                holder.botonComprar.isEnabled = edad >= 17
            }
        }
        holder.botonComprar.setOnClickListener {
            Toast.makeText(
                holder.itemView.context,
                "Compraste ${juego.nombre}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun getItemCount() = juegos.size
}
