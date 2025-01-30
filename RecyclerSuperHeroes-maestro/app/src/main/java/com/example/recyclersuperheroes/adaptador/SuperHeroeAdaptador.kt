package com.example.recyclersuperheroes.adaptador

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclersuperheroes.R
import com.example.recyclersuperheroes.SuperHeroe

class SuperHeroeAdaptador(val superHeroes:List<SuperHeroe>):RecyclerView.Adapter<SuperHeroeViewHolder>() {
    private var num=0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroeViewHolder {
        //Este metodo crea un ViewHolder, se crearan como máximo unos 7 u 8 depende de la pantalla y del tamaño del
        //elemento
      //Tiene que obtener un inflador de vistas
      val minflater= LayoutInflater.from(parent.context)
     //   Log.i("Adaptador","Cuando se crea un vieHolder ${num++}")
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
        Log.i("Adaptador","OnBindViewHolder, cuando se vincula con el holder de la posicion:$position")
    }
}