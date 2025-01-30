package com.example.recyclersuperheroes

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclersuperheroes.adaptador.SuperHeroeAdaptador

class MainActivity : AppCompatActivity() {
    lateinit var mirecyclerView:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Inicializar_RecyclerView()
    }

    private fun Inicializar_RecyclerView() {
        this.mirecyclerView=findViewById(R.id.mirecyclerview)
        //Definimos el tipo de recyclerView
        val manager=LinearLayoutManager(this)
        //Definimos el tipo de recyclerView
        mirecyclerView.layoutManager=manager
        //Fijar el adaptador
        mirecyclerView.adapter=SuperHeroeAdaptador(SuperHeroeProveedor.SuperHeroeList)

        //Añadimos un separador a los elementos dle recyclerView
        val decorador=DividerItemDecoration(this,manager.orientation)
        mirecyclerView.addItemDecoration(decorador)
    }
}