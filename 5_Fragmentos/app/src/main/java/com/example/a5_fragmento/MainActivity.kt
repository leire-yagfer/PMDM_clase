package com.example.a5_fragmento

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //CARGAR EL FRAGMENTO
        //1º: obtego una referencia de un FragmentTransaction
        val mifragmenttransaction = supportFragmentManager.beginTransaction()
            //instancio objeto de la clase A --> podría saltar este paso y directamente poner la clase
        val miobjeto = fragmentoA()
        //2º: añadir al activity una instancia del "fragmentoA"
        mifragmenttransaction.add(R.id.fragmentContainerView, miobjeto, "fragmentoA")
        //3º: hacer commit pq si no, no se hace
        mifragmenttransaction.commit()
    }//onCreate
}//class