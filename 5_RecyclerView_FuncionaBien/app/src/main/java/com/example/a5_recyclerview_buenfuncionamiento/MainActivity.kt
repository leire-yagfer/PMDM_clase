package com.example.recycledview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a5_recyclerview_buenfuncionamiento.Coche
import com.example.a5_recyclerview_buenfuncionamiento.R
import com.example.a5_recyclerview_buenfuncionamiento.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var mibinding: ActivityMainBinding
    //defino el objeto del RecyclerView
    lateinit var mirecycler: RecyclerView
    //defino el objeto del Adapter
    lateinit var miadapter: AdaptadorRecycler
    //creo la lista de datos --> en formato data class
    var lista_datos = mutableListOf(
        Coche("Mercedes"), Coche("BMW"), Coche("Renault"), Coche("Opel"), Coche("Ford"), Coche("Kia"), Coche("Toyota"), Coche("Honda"), Coche("Hyundai"), Coche("Citroen"), Coche("Peugeot"),
        Coche("Mercedes"), Coche("BMW"), Coche("Renault"), Coche("Opel"), Coche("Ford"), Coche("Kia"), Coche("Toyota"), Coche("Honda"), Coche("Hyundai"), Coche("Citroen"), Coche("Peugeot"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //binding
        mibinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mibinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        inicializar_recyclerview()
    }//onCreate


    private fun inicializar_recyclerview(){
        //instanciar el recyclerView
        this.mirecycler = mibinding.viewMirecycler

        //Definir el manejador del Layout del recycler
        this.mirecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false) //LinearLayoutManager.VERTICAL --> indica como se disponen


        //añadir un separador vertical a los elementos del recyclerView
        val divisor = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        //le asigno al recyclerView el itmeDecoration
        this.mirecycler.addItemDecoration((divisor))


        //asignar una referencia del adapter
        this.miadapter = AdaptadorRecycler(this.lista_datos){
            posicion ->
            lista_datos.get(posicion).seleccionado =! lista_datos.get(posicion).seleccionado
            //repinto los elementos
            miadapter.notifyItemChanged(posicion)
        }



        //vinculo el RecyclerView al Adaptador
        this.mirecycler.adapter = this.miadapter
    }//inicializar_recyclerview
}//class