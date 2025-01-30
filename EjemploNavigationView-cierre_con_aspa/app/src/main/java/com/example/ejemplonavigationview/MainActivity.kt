package com.example.ejemplonavigationview

import android.app.ActionBar
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.ejemplonavigationview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var mibinding:ActivityMainBinding
    lateinit var mi_toolbar:Toolbar
    lateinit var mi_drawer:DrawerLayout
    lateinit var toggle:ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mibinding=ActivityMainBinding.inflate(layoutInflater)
      enableEdgeToEdge()
        setContentView(mibinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mi_drawerlayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            //Ahora actualizo el margen del NavigationView
            // Configurar LayoutParams personalizados

            val layoutParams = mibinding.navigationView.layoutParams as DrawerLayout.LayoutParams

            // Establecer el margen superior para que solo cubra el toolbar
            layoutParams.topMargin = systemBars.top
            // Opcional: establecer márgenes inferiores también (por ejemplo, para navegación gestual)


            // Aplicar los LayoutParams al NavigationView
            mibinding.navigationView.layoutParams = layoutParams

            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }
        //DrawerLayout
        mi_drawer=findViewById(R.id.mi_drawerlayout)

        //Establecemos el Toolbar
        setSupportActionBar(mibinding.myToolbar)

        //Ponemos icono al menu si queremos cambiarlo
       // supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_action_restaurant_menu)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mibinding.apply {

            //El ActionBarDrawerToggle es un componente que sincroniza el DrawerLayout con la barra de herramientas (Toolbar) para:
            //
            //Mostrar el ícono de menú (hamburguesa) cuando el menú lateral (Drawer) está cerrado.
            //Cambiar ese ícono por una flecha de retroceso cuando el Drawer está abierto.
            //Manejar la animación de transición entre el ícono de menú y la flecha automáticamente.

            toggle=ActionBarDrawerToggle(this@MainActivity,miDrawerlayout,R.string.abierto,R.string.cerrado)

         //   Esto vincula el toggle (el controlador de animación y eventos del Drawer) con el DrawerLayout.
           // De esta manera, el ActionBarDrawerToggle detecta cuando el Drawer se abre o se cierra y actualiza automáticamente el ícono en la barra de herramientas.
            miDrawerlayout.addDrawerListener(toggle)

            // Este método sincroniza el estado inicial del DrawerLayout con la barra de herramientas al cargar la actividad.
            //Por ejemplo:
            //Si el Drawer ya está abierto cuando se carga la actividad, el ActionBarDrawerToggle asegurará que el ícono inicial sea la flecha de retroceso.
            //Si está cerrado, mostrará el ícono de menú (hamburguesa).
            toggle.syncState()

            //Establezco la operación cuando pulso al X del navigationView

            navigationView.getHeaderView(0).findViewById<ImageView>(R.id.imageCruz).setOnClickListener{
                miDrawerlayout.closeDrawers()
            }


            //Añade escuchador a las opciones de menu
            navigationView.setNavigationItemSelectedListener {
                when(it.itemId)
                {   //Cada vez creo una instancia nueva de fragmentos
                    R.id.faltas-> openFragment(Faltas_Fragment())
                    R.id.positivos-> openFragment(Positivos_Fragment())
                    R.id.trabajos-> openFragment(Trabajos_Fragment())
                    R.id.BD->Toast.makeText(this@MainActivity,"BD",Toast.LENGTH_LONG).show()
                    else -> Toast.makeText(this@MainActivity,"RESTO",Toast.LENGTH_LONG).show()
                }
                //Si queremos que quede marcada la opción,
              // it.setChecked(true)
                mi_drawer.closeDrawers()
                true

            }
        }


    }
/*
Cuando el usuario interactúa con el botón de menú hamburguesa o la flecha de retroceso:

toggle.onOptionsItemSelected(item) te indica si es el toggle el que gestiona ese elemento del toolbar
dado que previamente se ha configurado esa gestion cuando se ha indicado que el escuchador
de las acciones es el toggle
El método devuelve true, indicando que el evento fue manejado.
Si el usuario selecciona cualquier otro ítem del menú:

El método delega el evento al sistema (super.onOptionsItemSelected(item)),
que se encargará de manejarlo (por ejemplo, ejecutando acciones específicas para otros ítems).
 */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
/*     Se puede implementar de otra forma
  if(item.itemId==android.R.id.home){
            mibinding.miDrawerlayout.openDrawer(GravityCompat.START);
        }
    return true */
       if(toggle.onOptionsItemSelected(item)){
            //supportActionBar?.setDisplayHomeAsUpEnabled(true)
          return true
        }

        return super.onOptionsItemSelected(item)
    }

/*Funcion para cargar un fragmento, cuidado que no lo añado en la pila */
    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, fragment)
            .commit()
    }
}