package com.example.ejemploreceptorintents

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ejemploreceptorintents.databinding.ActivityEnviarEmailBinding

class Enviar_email : AppCompatActivity() {
    lateinit var mibinding:ActivityEnviarEmailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        mibinding=ActivityEnviarEmailBinding.inflate(layoutInflater)
        setContentView(mibinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        //Obtengo el intent y los datos del email
        var miIntent=intent
        var datos=intent.data
        if(datos?.scheme.equals("mailto"))
        {
            mibinding.textDestinatario.text= miIntent.getStringArrayExtra(Intent.EXTRA_EMAIL)?.get(0) ?: ""
            mibinding.textAsunto.text=miIntent.getStringExtra(Intent.EXTRA_SUBJECT)
            mibinding.textMensaje.text=miIntent.getStringExtra(Intent.EXTRA_TEXT)
        }

    }
}