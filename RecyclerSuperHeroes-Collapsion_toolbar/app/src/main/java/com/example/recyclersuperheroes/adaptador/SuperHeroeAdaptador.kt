package com.example.recyclersuperheroes.adaptador

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.recyclersuperheroes.R
import com.example.recyclersuperheroes.SuperHeroe
import com.example.recyclersuperheroes.cargarImagen
import com.example.recyclersuperheroes.databinding.ElementoSuperheroeBinding

class SuperHeroeAdaptador(val superHeroes:List<SuperHeroe>):RecyclerView.Adapter<SuperHeroeAdaptador.SuperHeroeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroeViewHolder {
        //Este metodo crea un ViewHolder, se crearan como máximo unos 7 u 8 depende de la pantalla y del tamaño del
        //elemento
      //Tiene que obtener un inflador de vistas
      val minflater= LayoutInflater.from(parent.context)

        val miView=minflater.inflate(R.layout.elemento_superheroe,parent,false)
        //Retorno el viewHolder y le paso la vista inflada de un elemento
        return SuperHeroeViewHolder(miView,parent.context)
    }

    override fun getItemCount(): Int=
      //Solamente tiene que devolver el nº de elementos que habría que pintar
       superHeroes.size


    override fun onBindViewHolder(holder: SuperHeroeViewHolder, position: Int) {
        //Se invoca por cada elemento que se visualiza
        holder.vincular(superHeroes.get(position))

    }


    class SuperHeroeViewHolder(val vista: View, val context: Context): ViewHolder(vista) {
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
                Toast.makeText(context,"click en la vista", Toast.LENGTH_LONG).show()
            }
        }
    }

}