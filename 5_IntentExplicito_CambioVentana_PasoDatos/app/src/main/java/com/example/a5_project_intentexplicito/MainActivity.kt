package com.example.a5_project_intentexplicito

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.a5_project_intentexplicito.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //declaro la variable mibinding: Activity[nombreActividad --> si es con Activity solo se añade lo de a mayores. Si se llamase ActividadPrincipal, sería ActivityActividadPrincipalBinding]Binding
    lateinit var mibinding: ActivityMainBinding

    //codigo que quiero que se ejecute cuando vuelve de la pantallaB
    var milauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            miObjeto: ActivityResult ->
        /*
        miObjeto.data -->
        miObjeto.resultCode -->
        */
        if(miObjeto.resultCode == Activity.RESULT_OK){
            //obtengo los datos de B
            mibinding.tvEdad.text = miObjeto.data?.extras?.getInt("dato_actividadB").toString()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //setContentView(R.layout.activity_main) --> lo quito porque en este punto estoy vinculando el objeto con la aplicación y con el binding ya lo hago --> no hace falta poner lo de R.layout
        //quito todo lo siguiente pq sino no funciona
        /*
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

        inicializarComponentes()
    }//onCreate


    private fun inicializarComponentes(){
        //instancio el objeto binding
        mibinding = ActivityMainBinding.inflate(layoutInflater)

        //establezco el layout a mi actividad --> equivale a lo comentado en la linea 19
        setContentView(mibinding.root) //root es el layout

        //escuchador a mi botón
        mibinding.btIrAb.setOnClickListener{
            //abrir la actividad B
            //1º creo el intent explicita (contexto (quien abre la actividad, en este caso this), ref. clase que quiero abrir)
            var miIntent = Intent(this, ActivityB::class.java)

            //2º creo el Bundle --> paso los datos de esta clase a la pantallaB
            var miBundle = Bundle()

            //3º relleno el Bundle --> miBundle.put[tipoDatoQuePaso]("[nombreReferencia]("nombreClave", valor -> en este caso le paso el texto del editText) --> es un mapa
            miBundle.putString("dato1", mibinding.etPalabraPasarPantalla.text.toString())

            //4º vinculo el bundle al intent --> si no lo vinculo ActivityB no puede acceder a esos datos
            miIntent.putExtras(miBundle)

            //5º abro/inicio la actividadB --> lo comento pq si abro de esta forma la actividadB, actividadB no me va a dejar retornar nada a MAinActivity
            //startActivity(miIntent) //le paso intent pq quiero abrir/inicializar la clase ActivityB

            //5º Inicio la actividad B para que me devuelva datos
            milauncher.launch(miIntent)
        }
    }//inicializarComponentes
}