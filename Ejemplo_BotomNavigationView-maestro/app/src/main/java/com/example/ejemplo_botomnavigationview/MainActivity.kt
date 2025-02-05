package com.example.ejemplo_botomnavigationview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    lateinit var mi_bottom: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        mi_bottom = findViewById(R.id.bottomnav)

        mi_bottom.setOnItemSelectedListener { menuItem -> //obtiene el menuItem y devuelve un boolean
            when(menuItem.itemId){
                R.id.home -> {
                    cargar_fragmento(FragmentHome())
                }

                R.id.favorito -> {
                    cargar_fragmento(FragmentFavourite())
                }

                R.id.conf -> {
                    cargar_fragmento(Configuracion())
                }
            }
            true //ha funcionado
        }
    }

    fun cargar_fragmento(f:Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.contenedor_fragmentos, f).commit()
    }
}