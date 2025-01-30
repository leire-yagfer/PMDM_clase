package com.example.recyclersuperheroes.adaptador

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.recyclersuperheroes.R
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.recyclersuperheroes.SuperHeroe

import com.example.recyclersuperheroes.cargarImagen
import com.example.recyclersuperheroes.databinding.ElementoSuperheroeBinding

class SuperHeroeViewHolder(val vista: View,val context: Context): ViewHolder(vista) {
   val binding= ElementoSuperheroeBinding.bind(vista)
    /* SIN USAR BIDING ES NECESARIO LO SIGUIENTE
    //Defino tantos elementos de vista como elementos tenga que visualizar
    //En este caso 3 textview y 1 ImageView
    val vista_nombre=vista.findViewById<TextView>(R.id.tvSuperHeroName)
    val vista_nombre_real=vista.findViewById<TextView>(R.id.tvRealName)
    val vista_imagen=vista.findViewById<ImageView>(R.id.ivSuperHero)
    val vista_publicador=vista.findViewById<TextView>(R.id.tvPublisher)
*/
    fun vincular(misuperHeroe:SuperHeroe)
    {
        binding.tvSuperHeroName.text=misuperHeroe.superHeroe
        binding.tvRealName.text=misuperHeroe.nombre
        binding.tvPublisher.text=misuperHeroe.publicador
        binding.ivSuperHero.cargarImagen(misuperHeroe.foto)
        /* SIN USAR BIDING
        this.vista_nombre.text=misuperHeroe.superHeroe

        this.vista_nombre_real.text=misuperHeroe.nombre
        this.vista_publicador.text=misuperHeroe.publicador
        this.vista_imagen.cargarImagen(misuperHeroe.foto)
*/

        //itemView representa toda la celda
       vista.setOnClickListener{
            //El codigo que quiero que se ejecute
            Toast.makeText(context,"click en la vista",Toast.LENGTH_LONG).show()
        }
    }
}