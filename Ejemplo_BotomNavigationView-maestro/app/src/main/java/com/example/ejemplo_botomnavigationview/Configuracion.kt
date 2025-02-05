package com.example.ejemplo_botomnavigationview

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.ejemplo_botomnavigationview.databinding.FragmentConfiguracionBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Configuracion.newInstance] factory method to
 * create an instance of this fragment.
 */
class Configuracion : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_configuracion, container, false)
        var binding = FragmentConfiguracionBinding.bind(view)
        var recycler:RecyclerView = binding.textorecycler


        //Esto iría en el proveedor
        var lista = listOf("Hola", "Adios", "Que Tal","Hola", "Adios", "Que Tal","Hola", "Adios", "Que Tal","Hola", "Adios", "Que Tal","Hola", "Adios", "Que Tal")

        var adapter = TextoAdapter(lista, funcionPadre = { texto->
            var miIntent:Intent = Intent(context, Descripcion::class.java)
            miIntent.putExtra("nombre", texto)
            startActivity(miIntent)
        })

        recycler.layoutManager = LinearLayoutManager(container?.context, LinearLayoutManager.VERTICAL, false)
        recycler.adapter = adapter

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Configuracion.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Configuracion().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}