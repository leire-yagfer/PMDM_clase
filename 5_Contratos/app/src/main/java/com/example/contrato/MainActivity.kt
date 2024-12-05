package com.example.contrato

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.contrato.Contrato.Activity_CCadena
import com.example.contrato.Contrato.Activity_CEntero
import com.example.contrato.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //declaro la variable mibinding: sirve para acceder a los componentes del programa --> no hago R.id ni declarar objetos de lsoa tributos
    //me permite no inicializar los componentes, en este caso los botones
    lateinit var mibinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //setContentView(R.layout.activity_main)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

        inicializarComponentes()

    }//onCreate


    fun inicializarComponentes(){
        //instancio el objeto binding
        mibinding = ActivityMainBinding.inflate(layoutInflater)

        //establezco el layout a mi actividad --> equivale a lo comentado en la linea 24
        setContentView(mibinding.root) //root es el layout

        //les pongo un listener para que al presionarlos se vayan a la pantalla correspondiente
        mibinding.btContratoCadena.setOnClickListener {
            val miintent= Intent(this, Activity_CCadena::class.java)
            startActivity(miintent)
        }//bt_contratoCadena.setOnClickListener

        mibinding.btContratoEnteros.setOnClickListener {
            val miintent= Intent(this, Activity_CEntero::class.java)
            startActivity(miintent)
        }//bt_contratoEntero.setOnClickListener
    }//inicializarComponentes
}//class