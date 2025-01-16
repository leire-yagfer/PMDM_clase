package com.example.recycledview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.RecyclerListener
import com.example.recycledview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var mibinding: ActivityMainBinding
    lateinit var mirecycler: RecyclerView

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

        inicializar_recyclerview()
    }//onCreate


    private fun inicializar_recyclerview(){
        //instanciar el recyclerView
        this.mirecycler = mibinding.viewMirecycler

        //Definir el manejador del LAyout del recycler
        this.mirecycler.layoutManager = LinearLayoutManager(this)
    }//inicializar_recyclerview
}//class