package com.example.a5_project_intentexplicito

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.a5_project_intentexplicito.databinding.ActivityBBinding
import com.example.a5_project_intentexplicito.databinding.ActivityMainBinding

class ActivityB : AppCompatActivity() {
    lateinit var mibindingB: ActivityBBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        inicializarComponentes()
    }//onCreate


    private fun inicializarComponentes(){
        //instancio el objeto binding
        mibindingB = ActivityBBinding.inflate(layoutInflater)

        //establezco el layout a mi actividad --> equivale a lo comentado en la linea 17
        setContentView(mibindingB.root) //root es el layout

        //obtengo el intent que sirvió para abrir esta actividad
        var miIntentB = intent //intent propiedad que me devuelve el intent

        //obtengo el bundle vinculado al intent
        var miBundleB = miIntentB.extras //extras alberga el Boundle

        //accedo a los datos del bundle
        if (miBundleB != null) {
            mibindingB.tvTextoPantallaMain.text = miBundleB.getString("dato1")
        } //no pongo else pq bundle nunca es nulo, sino que es vacío pq le instancio desde MainActivity


        //escuchador a mi botón
        mibindingB.btFinalizarB.setOnClickListener{
            //REALIZO LO MISMO QUE PARA PASAR DE MAIN A B
            //creo un intent que esta vinculado con la actividad Main y que me sirve para devolver datos
            var miIntentBaMain = Intent()

            //creo un bundle para introducir los datos
            var miBundleBaMain = Bundle()

            //relleno el bundle
            miBundleBaMain.putInt("dato_actividadB", mibindingB.edEdad.text.toString().toIntOrNull() ?: 0) //si falla sale 0

            //Vinculo el bundle al intent
            miIntentBaMain.putExtras(miBundleBaMain)

            //establezco el resultado
            setResult(Activity.RESULT_OK, miIntentBaMain)

            //termino una actividad y vuelvo a la anterior pantalla --> en este caso al main
            //al utilizar el finish desaparece de memoria
            finish()
        }
    }//inicializarComponentes
}