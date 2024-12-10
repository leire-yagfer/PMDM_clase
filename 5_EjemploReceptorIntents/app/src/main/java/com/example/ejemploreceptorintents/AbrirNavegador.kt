package com.example.ejemploreceptorintents

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AbrirNavegador : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_abrir_navegador)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Obtengo la intent que permitió abrir esta actividad
        var miIntent=intent
        //Obtengo los datos
        var datos=miIntent.data?.schemeSpecificPart?:"Vacio"
        var mitexto=findViewById<TextView>(R.id.textNavegador)
        mitexto.text=datos

        //Voy a mostar en un visor Web (WebView) la web de google

        val webView: WebView = findViewById(R.id.mivisorWeb)
        webView.webViewClient = WebViewClient() // Asegura que se mantenga en la aplicación
        webView.settings.javaScriptEnabled = true // Si necesitas soporte para JS

        // Cargar la página si la URI coincide con lo esperado
        if (miIntent.data != null && miIntent.data.toString().equals( "https://www.google.com")){
            webView.loadUrl(miIntent.data.toString())
        } else {
            webView.loadUrl("https://www.android.com/") // Por defecto, cargar Android
        }
    }
}