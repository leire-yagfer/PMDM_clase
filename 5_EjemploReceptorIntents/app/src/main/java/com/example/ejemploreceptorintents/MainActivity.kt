package com.example.ejemploreceptorintents

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.CalendarContract
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ejemploreceptorintents.databinding.ActivityMainBinding
import java.time.LocalDateTime
import java.time.ZoneOffset

class MainActivity : AppCompatActivity() {
    lateinit var mibinding:ActivityMainBinding
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        mibinding=ActivityMainBinding.inflate(layoutInflater)

        setContentView(mibinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Escuchador boton Navegador
        mibinding.botonNavegador.setOnClickListener {
        //Intent para navegar a una web
            var miIntent=Intent(Intent.ACTION_VIEW)
            //Establezco los datos, en esta caso la Uri para abrir www.google.es
            miIntent.data=Uri.parse("https://www.google.com")
            //Podría indicar el tipo Mime ver https://developer.android.com/guide/components/intents-common?hl=es-419#Browser
            //pero en este caso no lo creo conveniente

            //Compruebo si existe una actividad que sea capaz de responder a este acción

          if(miIntent.resolveActivity(packageManager)!=null)
            {
                startActivity(miIntent)
            }

        }
        //Escuchador boton Llamada (abrir el dial)
        mibinding.botonLlamada.setOnClickListener {
            //Intent para abrir el dial
            var miIntent=Intent(Intent.ACTION_DIAL)
            //Establezco los datos de la Intent, en este caso una URI telefono cuyo esquema es tel:
            miIntent.data= Uri.parse("tel:685424232")


            //Si quisiera mostrar un selector de aplicaciones en startActivity pasaría chooser
            val chooser = Intent.createChooser(miIntent, "Selecciona una aplicación")

            //Muy importante que en el archivo de manifiesto la propiedad exported sea true, porque
            //sino la actividad no responderá a esta petición de acción
            //Si existe una actividad en mi sistema capaz de responder a esta acción
            if(miIntent.resolveActivity(packageManager)!=null)
            {
                startActivity(miIntent)
            }
        }

        //Escuchador boton enviar email
        //https://developer.android.com/guide/components/intents-common?hl=es-419#Email
        mibinding.botonEmail.setOnClickListener {
            //Enviar email sin archivos adjuntos
            val miIntent=Intent(Intent.ACTION_SENDTO)
            //Indico el tipo mime, en este caso texto plano
            miIntent.type="text/plain"
            //Quiero solamente que responda una aplicación que pueda
            //enviar correo electronico y no una red social o app de mensajeria
            miIntent.data=Uri.parse("mailto:")
            //Y ahora indico los datos del mensaje
            //Primero el/los correo/s electronico/ al que se lo remito (pueden ser multimples)
            miIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("rsanchezro@educa.jcyl.es"))
            //El asunto del correo
            miIntent.putExtra(Intent.EXTRA_SUBJECT,"Email de prueba")
            //El mensaje
            miIntent.putExtra(Intent.EXTRA_TEXT,"Este es el texto del mensaje")

            if(miIntent.resolveActivity(packageManager)!=null)
            {
                startActivity(miIntent)
            }



        }

        //Escuchador boton agregarEventoCalendario
        //https://developer.android.com/guide/components/intents-common?hl=es-419#AddEvent
        mibinding.botonCalendario.setOnClickListener {

            //Creo el intent
            var miIntent=Intent(Intent.ACTION_INSERT)
            //defino el uri, tal y como indica la documentación
            miIntent.data= CalendarContract.Events.CONTENT_URI
            //defino el tipo mime
            miIntent.type="vnd.android.cursor.dir/event"
            //Defino los datos para añadir el evento al calendario
            //Primero el titulo del evento
            miIntent.putExtra(CalendarContract.Events.TITLE,"Titulo del evento")
            //Ahora añado una descripcion
            miIntent.putExtra(CalendarContract.Events.DESCRIPTION,"Descripcion del evento")
            //A continuación la hora de inicio del evento, puesto que el valor hay que darlo en formato epoch
            //defino el valor con las clases oportunas
            var dateTime = LocalDateTime.of(2024, 12, 12, 15, 30, 0) // 12 de diciembre de 2024, 15:30:00
            // Convertirla a epoch (milisegundos)
            var epochTime = dateTime.toInstant(ZoneOffset.UTC).toEpochMilli()

            miIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,epochTime)

            //La hora de finalización del evento de la misma forma que el valor anterior
            dateTime=LocalDateTime.of(2024,12,20,23,59,59)
            epochTime=dateTime.toInstant(ZoneOffset.UTC).toEpochMilli()
            miIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,epochTime)

            //Solo queda arrancar una actividad que responda a esta acción
            if(miIntent.resolveActivity(packageManager)!=null)
            {
                startActivity(miIntent)
            }



        }
    }
}