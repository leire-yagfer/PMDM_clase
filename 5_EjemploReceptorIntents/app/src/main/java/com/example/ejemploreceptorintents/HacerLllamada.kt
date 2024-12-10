package com.example.ejemploreceptorintents

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HacerLllamada : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_hacer_lllamada)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Obtento la intent que permitió iniciar esta actividad
        val intent = intent
        //Accedo a los datos que se envian, por lo general el nº de telefono en este caso, que es una Uri
        val datos = intent.data
        //Muestro el nº de telefono marcado, mediante el atributo schemeSpecificPart que representa
        //lo que hay despues de los : en una uri, <Esquema>:<schemeSpecificPart
        val mitextview=findViewById<TextView>(R.id.textViewLlamada)
            mitextview.text="Telefono:${datos?.schemeSpecificPart?:"Numero no proporcionado"}"


    }
}