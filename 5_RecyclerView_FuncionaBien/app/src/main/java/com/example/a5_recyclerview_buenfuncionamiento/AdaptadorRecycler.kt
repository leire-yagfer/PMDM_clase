package com.example.recycledview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a5_recyclerview_buenfuncionamiento.R
import com.example.a5_recyclerview_buenfuncionamiento.databinding.ElementsRecycleBinding


//VER EL PDF DEL SEGUNDO PARCIAL DE ESTE APARTADO DE RECYCLER
class AdaptadorRecycler(private val datos: List<String>): RecyclerView.Adapter<AdaptadorRecycler.MiViewHolder>() { //clase que hereda de RecycleView

    //vincula la vista con el holder y genera y retorna un objeto de tipo ViewHolder --> SE VA A EJECUTAR TANTAS VECES COMO OBJETOS HAYA EN EL RECYCLEVIEW
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiViewHolder {

        //inflar la vista del elemento --> devuelve una vista, por lo que lo almaceno en una variable
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.elements_recycle, parent, false) //false pq da error sino

        //construir un objeto ViewHolder de mi propia clase --> les relaciono
        val objeto_viewholder = MiViewHolder(vista)

        //vincular --> devuelvo un objeto viewHolder
        return objeto_viewholder
    }//onCreateViewHolder


    //saber cuantas veces tiene que invocar a la lista de datos
    override fun getItemCount(): Int {
        //retornar el numero de elemtos que tiene la lista de datos
        return datos.size

    }//getItemCount


    //viuncular los datos con el Holder --> se reciclan cuando haga scroll --> se ejecuta tantas veces como elemntos aparezcan en la pantalla cuando haga sroll
    override fun onBindViewHolder(holder: MiViewHolder, position: Int) {
        //vinculo cada uno de los elementos visuales (vistas) del holder con los datos
        //itemView == vista --> representa a la vista individual y tiene la misma referencia que vista
        holder.render(datos.get(position)) //trato como si fuese una lista
    }//onBindViewHolder





    //clase que representa un ViewHolder que hereda de RecyclerView
    class MiViewHolder(val vista: View): RecyclerView.ViewHolder(vista) {

        //hacemos el binding dentro del holder
        private var binding_holder = ElementsRecycleBinding.bind(vista)




        //asociar a los elementos visuales de cada elemnto del recyclerView los datos
        fun render(dato: String){
            binding_holder.textViewElement.text = dato

            //cambiar el color del elemetno seleccionado
            vista.setOnClickListener{
                vista.setBackgroundColor(vista.context.getColor(R.color.seleccionado))
            }
        }//render

    }//MiViewHolder
}//class