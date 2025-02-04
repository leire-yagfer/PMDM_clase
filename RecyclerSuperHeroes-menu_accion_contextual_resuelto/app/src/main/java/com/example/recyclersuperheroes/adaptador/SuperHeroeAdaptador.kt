package com.example.recyclersuperheroes.adaptador

//DEFINO LA OPERACION QUE VAN A HACER MIS ELEMENTOS DEL RECYCLEDVIEW
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.recyclersuperheroes.MainActivity
import com.example.recyclersuperheroes.R
import com.example.recyclersuperheroes.SuperHeroe
import com.example.recyclersuperheroes.cargarImagen
import com.example.recyclersuperheroes.databinding.ElementoSuperheroeBinding

class SuperHeroeAdaptador(
    val superHeroes: List<SuperHeroe>, //lista de todos los superhéroes
    val seleccionados: List<Int>, //lista de los seleccionados
    val onClickLargo: (Int) -> Unit,
    val onclickCorto: (Int) -> Unit
) : RecyclerView.Adapter<SuperHeroeAdaptador.SuperHeroeViewHolder>() {


    //copia y pega en todos iguales
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroeViewHolder {
        //Este metodo crea un ViewHolder, se crearan como máximo unos 7 u 8 depende de la pantalla y del tamaño del
        //elemento
        //Tiene que obtener un inflador de vistas
        val minflater = LayoutInflater.from(parent.context)

        val miView = minflater.inflate(R.layout.elemento_superheroe, parent, false)
        //Retorno el viewHolder y le paso la vista inflada de un elemento
        return SuperHeroeViewHolder(miView)
    }

    override fun getItemCount(): Int =
        //Solamente tiene que devolver el nº de elementos que habría que pintar
        superHeroes.size


    override fun onBindViewHolder(holder: SuperHeroeViewHolder, position: Int) {
        //Se invoca por cada elemento que se visualiza
        holder.vincular(superHeroes.get(position), seleccionados.contains(position))
        holder.vista.setOnClickListener {
            onclickCorto(position)
        }
        holder.vista.setOnLongClickListener {
            onClickLargo(position)
            //Para que se consuma el evento
            false
        }


    }


    class SuperHeroeViewHolder(val vista: View) : ViewHolder(vista) {
        val binding = ElementoSuperheroeBinding.bind(vista)

        fun vincular(misuperHeroe: SuperHeroe, seleccionado: Boolean) {
            binding.tvSuperHeroName.text = misuperHeroe.superHeroe
            binding.tvRealName.text = misuperHeroe.nombre
            binding.tvPublisher.text = misuperHeroe.publicador
            //hago referencia a companion object
            if (MainActivity.actionMode_activo && seleccionado) {
                binding.ivSuperHero.setImageResource(R.drawable.ic_check_circle_24dp)
            } else {
                binding.ivSuperHero.cargarImagen(misuperHeroe.foto)
            }


            /* SIN USAR BIDING
            this.vista_nombre.text=misuperHeroe.superHeroe

            this.vista_nombre_real.text=misuperHeroe.nombre
            this.vista_publicador.text=misuperHeroe.publicador
            this.vista_imagen.cargarImagen(misuperHeroe.foto)
            */


        }
    }
}