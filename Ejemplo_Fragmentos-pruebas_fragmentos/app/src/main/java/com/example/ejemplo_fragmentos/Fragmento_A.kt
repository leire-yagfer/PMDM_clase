package com.example.ejemplo_fragmentos

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class Fragmento_A(/*var funcionclick:(Fragmento_A) -> Unit*/): Fragment() { //Unit es nada, es lo mismo que void. loq ue hay () es el constructor, pero lo comento pq lo declaro abajo
    lateinit var mitextview:TextView
    var num_veces=0
    lateinit var buttonMostrar: Button


    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i("ejemplo_fragmento","Fragmento_A_OnAttach")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("clave","dato")
        Log.i("ejemplo_fragmento","Se guarda el estado del fragmentoA")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("ejemplo_fragmento","Fragmento_A_OnCreate")
        if(savedInstanceState==null)
        {
            Log.i("ejemplo_fragmento","El bundle del fragment es vacio")
        }
        /*arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }*/
    }

    override fun onPause() {
        super.onPause()
        Log.i("ejemplo_fragmento","Fragmento_A_OnPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("ejemplo_fragmento","Fragmento_A_OnStop")
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("ejemplo_fragmento","Fragmento_A_OnCreateView")

        // Inflate the layout for this fragment
        var v= inflater.inflate(R.layout.fragment_fragmento__a, container, false )
        this.mitextview= v.findViewById<TextView>(R.id.TVfragA)
        this.mitextview.text=param1

        this.buttonMostrar = v.findViewById<Button>(R.id.button)
        buttonMostrar.setOnClickListener{
            //3º: invoco a esa funcion en el evento que me interese gestionar
            Fragmento_A.funcionclick(this) //accedo a la instancia de la clase pq es lo que necesita el método --> una referencia de la clase
        }
        return v
    }//onCreateView

    //PATRÓN SIGLETON --> SOLO SE PUEDE INSTANCIAR UNA VEZ
    companion object {
        //¿Por qué es privada?
        @SuppressLint("StaticFieldLeak")
        private var instancia: Fragmento_A?=null

        //1º: defino un atibuto que define la funcion que quiero ejecutar --> almacena el código de la funcion
        //le introduzco aquí pq necesito acceder desde el companion object a esta variable, pero si puedo hacer referencia
        lateinit var funcionclick:(Fragmento_A) -> Unit //funcion en una variable == lambda que no devuelve nada (Unit == void == nada)

        //
        fun getInstancia(funcion:(Fragmento_A) -> Unit): Fragmento_A{ //funcion que retorna una variable de Fragmento_A
            //2º: asigno a funcionclick el código que tuviera el parámatro funcion
            funcionclick = funcion
            //si la instancia que obtengo es nula, creo una nueva --> SIEMPRE LA PRIMERA INSTANCIA SERÁ NULA, LUEGO YA GUARDARÁ ALGÚN VALOR
            instancia = instancia?: Fragmento_A()
            /*EQUIVALENTE
            if(instancia==null){
                instancia = Fragmento_A()
            }*/
            return instancia!! //aseguro que no va a ser nulo con los !!
        }//getInstancia

    }//companion object


}