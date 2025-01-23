package com.example.recycledview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a5_recyclerview_buenfuncionamiento.Coche
import com.example.a5_recyclerview_buenfuncionamiento.R
import com.example.a5_recyclerview_buenfuncionamiento.databinding.ElementsRecycleBinding


//VER EL PDF DEL SEGUNDO PARCIAL DE ESTE APARTADO DE RECYCLER
class AdaptadorRecycler(private val datos: MutableList<Coche>, val onClick_item: (Int) -> Unit /*funcion que tiene parámetro de tipo entero y no devuelve nada*/): RecyclerView.Adapter<AdaptadorRecycler.MiViewHolder>() { //clase que hereda de RecycleView

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
        holder.render(datos.get(position), position)

        //cambio de color de los elementos
        holder.itemView.setOnClickListener{
            onClick_item(position)
        }//holder.itemView.setOnClickListener
    }//onBindViewHolder





    //clase que representa un ViewHolder que hereda de RecyclerView
    class MiViewHolder(val vista: View): RecyclerView.ViewHolder(vista) {
        //hacemos el binding dentro del holder
        private var binding_holder = ElementsRecycleBinding.bind(vista)

        //se invoca siempre que haga sroll
        fun render(dato: Coche, pos:Int){
            //asociar a los elementos visuales de cada elemnto del recyclerView los datos
            binding_holder.textViewElement.text = dato.marca

            //cambiar el color del elemetno seleccionado
            //cambio el color aquí fuera
            if(dato.seleccionado){
                vista.setBackgroundColor(vista.context.getColor(R.color.seleccionado))
            }
            else{
                vista.setBackgroundColor(vista.context.getColor(R.color.white))
            }//if-else


            //NO ES NECESARIO PQ LO DEFINO EN EL MAIN Y LLAMO AL MÉTODO QUE EFECTUA EL CAMBIO DEL FONDO EN onBindViewHolder
            /*
            vista.setOnClickListener{
                //TODO ESTE CÓDIGO SE DEBERÍA EJECUTAR EN LA CLASE QUE DEFINA EL RECYCLERVIEW, en este caso en el Main
                //NO PUEDO HACER AQUÍ EL IF-ELSE PQ LA PROPIEDAD DE VIEWHOLDER CAMBIARÍA TODOS AQUELLOS QUE SE RECICLAN EN UN HOLDER CONCRETO
                //cambio el valor de seleccionado
                dato.seleccionado =! dato.seleccionado

                //repintar SOLO el dato que se cambie de color
                this.bindingAdapter?.notifyItemChanged(pos)
            }//vista.setOnClickListene

            */
        }//render
    }//MiViewHolder
}//class