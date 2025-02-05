package com.example.recyclersuperheroes

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.recyclersuperheroes.databinding.ActivityDetallesSuperheroeBinding

class detalles_superheroe : AppCompatActivity() {

    //binding
    lateinit var mibinding: ActivityDetallesSuperheroeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        mibinding = ActivityDetallesSuperheroeBinding.inflate(layoutInflater)
        setContentView(mibinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //obtengo el intent --> los datos
        var pos = intent.extras?.getInt("posicion")?:0 //si fuera nulo le paso la posicion 0

        //relleno los datos
        mibinding.descripcionSuperheroe.text = SuperHeroeProveedor.SuperHeroeList.get(pos).superHeroe
        mibinding.imageView.cargarImagen(SuperHeroeProveedor.SuperHeroeList.get(pos).foto)
    }
}