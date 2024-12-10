package com.example.ejemploreceptorintents

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ejemploreceptorintents.databinding.ActivityAgregarEventoCalendarioBinding

class AgregarEventoCalendario : AppCompatActivity() {
    lateinit var mibinding:ActivityAgregarEventoCalendarioBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        mibinding=ActivityAgregarEventoCalendarioBinding.inflate(layoutInflater)
        setContentView(mibinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Recibo los datos del evento calendario
        //Ejercicio, finalizar el layout para mostrar toda la información que proviene de
        //la intención que inicia esta actividad
    }
}