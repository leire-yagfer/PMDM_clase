package com.example.a5_fragmento

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class fragmentoA : Fragment() { //clase que hereda de fragment
    private var login: String? = null
    private var password: String? = null

    /*LO PRIMERO QUE SE EJECUTA*/
    //EN ESTE PUNTO OBTENGO LOS DATOS DEL BUNDLE Y SE ALMACENAN EN EL ARGUMENTS (LOGIN Y PASSWORD)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*arguments --> propiedad de fragment que puede tener informacion siempre y cuando se la pase alguien
        Almacena un mapa que proviene de la
        */                                          //EQUIVALENTE A LO DE LA IZQDA
        arguments?.let {                                //if(arguments!=null){
            login = it.getString(LOGIN)               //param1 = arguments.gentString(ARG_PARAM1)
            password = it.getString(PASSWORD)               //param2 = arguments.gentString(ARG_PARAM2)
        }                                               //}
    }//onCreate



    /*LO SEGUNDO QUE SE EJECUTA*/
    //AQUÍ YA TENGO LOS VALORES EN LAS VARIABLES, POR LO QUE ACCEDO A LOS TV EN LOS QUE QUIERO MOSTRAR LA INFO Y LA MUESTRO
    override fun onCreateView( //infla el Layout
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //crea una vista para incluirla dentro del contenedor --> es todo el layout por lo que puedo acceder a lo que sea
        val v = inflater.inflate(R.layout.fragment_fragmento_a, container, false)

        //obtengo los valores del Bundle y los muestro en los TV
        v.findViewById<TextView>(R.id.login).text = this.login //pongo el .text para que se pase a texto
        v.findViewById<TextView>(R.id.login).text = this.password

        return v //devuelve una vista
    }//view



    companion object { //zona estática de la clase
        //las siguientes variables las cargo en el activity y las paso al fragment
            //he creado el bundle en el MainActivity y le paso info al fragmento
        const val LOGIN = "login" //key:login
        const val PASSWORD = "password" //key: password
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragmentoA().apply {
                arguments = Bundle().apply {
                    putString(LOGIN, param1)
                    putString(PASSWORD, param2)
                }
            }
    }//companion object
}//class