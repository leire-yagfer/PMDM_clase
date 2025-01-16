package com.example.a5_fragmento

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.a5_fragmento.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var mibinding: ActivityMainBinding
    var carga_fragmentoa = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        mibinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mibinding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        /*
        //CARGAR EL FRAGMENTO -> PRIMERA FORMA
        //1º: obtego una referencia de un FragmentTransaction
        val mifragmenttransaction = supportFragmentManager.beginTransaction()
            //instancio objeto de la clase A --> podría saltar este paso y directamente poner la clase
        val miobjeto = fragmentoA()
        //2º: añadir al activity una instancia del "fragmentoA"
        mifragmenttransaction.add(R.id.fragmentContainerView, miobjeto, "fragmentoA")
        //3º: hacer commit pq si no, no se hace
        mifragmenttransaction.commit()
        */


        //creo el Bundle y lo relleno con info (el bundle del fragmentoA) para pasar la info desde aquí al fragmento
        //PARA COMUNICAR LOS DOS FRAGMENTOS, TENGO QUE USAR ESTA CLASE (MAINACTIVITY) COMO INTERMEDIARIO
        val miBundle = bundleOf(fragmentoA.LOGIN to "Pepito", fragmentoA.PASSWORD to "pepitopassword")


        //CRAGAR FRAGMENTO DE LA FORMA MÁS OPTIMIZADA -> SEGUNDA FORMA
        //1º: añadir la dependencia ("implementation("androidx.fragment:fragment-ktx:1.8.5")")
        //2º: hacer todo lo anterior de forma simplificada en una lamda
        supportFragmentManager.commit {
            setReorderingAllowed(true) //OBLIGATORIO para reordenar/actualizar la pila de fragmentos
            //add<fragmentoA>(R.id.fragmentContainerView) --> lo comento pq es como iría de normal, pero quiero hacer uso del bundle
            //EXTRA --> PASO EL BUNDLE A FRAGMENTOA
            add<fragmentoA>(R.id.fragmentContainerView, "", miBundle)
        }//lamda de supportFragmentManager


        //obtener una referencia del FragmentCOntainerView para añadirle la acción onClickListener para que cuando clique, se cambie
            //VOY A UTILIZAR EL MIBINDING
        //1º: añadir lo necesario en el build-gradle
            /*buildFeatures{
                 viewBinding=true
              }*/
        //2º:instancio el mibinding --> ARRIBA PQ TENGO QUE HACERLO ANTES DEL setContentView
        //3º: acceder a la referencia del fragmentContainer
        mibinding.fragmentContainerView.setOnClickListener{
            //REEMPLAZAR EL FRAGMENTO VERDE POR EL AZUL DEL FRAGMENTCONTAINERVIEW
            //1º: creo un semaforo para comprobar que fragmento esta cargado, para cargar el contrario -> ARRIBA ("carga_fragmentoa")
            //2º: compruebo cual está cargado
            if(this.carga_fragmentoa){ //carga_fragmentoa = true, por lo que está cargado --> cargo el b
                supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    //replace<fragmentoB>(R.id.fragmentContainerView) --> creo un nuevo fragmento por lo que en caso de tener algo en otro framneto de B, lo perderia y se me creasria otro con solo rojo


                    //declaro un objeto en el que introduzco el fragmentoB para que cuando lo reemplace, no se cree uno nuevo, sino que utilizo el mismo
                    var objetoB = fragmentoB()
                    //reemplazo con la forma propia que tiene replace de instanciar el fragmento y añadir el fragmento (objetoB en este caso)
                    replace(R.id.fragmentContainerView, objetoB)

                    //3º: guardar en la pila los fgragmentos anteriores para que cuando se clique sobre volver atrás no se pierda
                    setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE) //efecto de como se pasa una a otra
                    addToBackStack("fragmentoB") //name: guía para el programador, no hace referencia a nada
                }
            } else{ //está cargado el fragmento b y lo reemplazo por a
                supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    //como estoy creando una nueva instancia del fragmentoA, piedo los datos de login y password
                    replace<fragmentoA>(R.id.fragmentContainerView)
                    setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                    addToBackStack("fragmentoA")
                }
            }//if-else
            //4º: cambio el valor de carga_fragmentoa porque sino, no se podría hacer más que una vez
            this.carga_fragmentoa =! carga_fragmentoa
        }//lambda de mibinding.fragmentContainerView.setOnClickListener
    }//onCreate
}//class