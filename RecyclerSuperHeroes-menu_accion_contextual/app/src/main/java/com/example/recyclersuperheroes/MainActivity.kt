package com.example.recyclersuperheroes

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclersuperheroes.adaptador.SuperHeroeAdaptador

class MainActivity : AppCompatActivity() {
    lateinit var mirecyclerView: RecyclerView
    lateinit var miadaptador: SuperHeroeAdaptador
    lateinit var mi_toolbar: Toolbar

    //Atributos para gestionar el action mode
    companion object {
        public var actionMode_activo = false

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Inicializar_RecyclerView()

        //DEFINO EL TOOLBAR
        mi_toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(findViewById(R.id.toolbar))




    }

    private fun limpiar_actionMode() {
     actionMode_activo=false;
        //Limpio el menu
        mi_toolbar.menu.clear()
        //Inflo el menu principal
        mi_toolbar.inflateMenu(R.menu.menu_principal)
        //Desaparece la flecha hacia Arriba
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        //Pongo el titulo de la aplicacion
        mi_toolbar.title=ContextCompat.getString(this,R.string.app_name)
        //Borro la lista de elementos seleccionados
        SuperHeroeProveedor.SuperHeroe_seleccionados.clear()
    }

    private fun Inicializar_RecyclerView() {
        this.mirecyclerView = findViewById(R.id.mirecyclerview)
        //Sirve para indicar que el tamaño del recyclerview no depende del contenido
        //del adaptador, se optimiza
        mirecyclerView.setHasFixedSize(true)
        //Definimos el tipo de recyclerView
        val manager = LinearLayoutManager(this)
        //Definimos el tipo de recyclerView
        mirecyclerView.layoutManager = manager
        //Fijar el adaptador
        miadaptador = SuperHeroeAdaptador(SuperHeroeProveedor.SuperHeroeList,
            { pos -> preparo_toolbar(pos) },
            { pos -> preparo_selecion(pos) })
        mirecyclerView.adapter = miadaptador

        //Añadimos un separador a los elementos dle recyclerView
        val decorador = DividerItemDecoration(this, manager.orientation)
        mirecyclerView.addItemDecoration(decorador)
    }

    //Funcion que prepara el toolbar cuando hago pulsacion larga sobre un elemento
    fun preparo_toolbar(posicion: Int) {
        //Primero borro el menu que hubiera en Toolbar
        mi_toolbar.menu.clear()
        //Inflo el menu de accion contextual casero
        mi_toolbar.inflateMenu(R.menu.menu_accion_contextual)
        //Indico que estamos en ActionMode
        actionMode_activo = true

        //Habilita el botón de "navegación hacia arriba" (o "back")
        // en la barra de acciones (ActionBar o Toolbar) del Activity.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        preparo_selecion(posicion)
    }

    //Funcion para guardar en una lista los elementos que voy seleccionando, clickcorto
    fun preparo_selecion(posicion: Int) {
        //Solo tiene efecto si esta el actionMode activo
        if (actionMode_activo) {
            with(SuperHeroeProveedor) {
                val superHeroe = SuperHeroeList[posicion]
                if (!SuperHeroe_seleccionados.contains(superHeroe)) {
                    SuperHeroe_seleccionados.add(superHeroe)
                } else {
                    SuperHeroe_seleccionados.remove(superHeroe)
                }
                actualiza_contador()
                //Notifico de los cambios al adaptador
                miadaptador.notifyItemChanged(posicion)
            }

        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        when(item.itemId)
        {
            R.id.item_edit->{
                //Aunque el elemento de menu edit solamente
                //aparece con 1 elemento seleccionado me aseguro por si acaso
                if(SuperHeroeProveedor.SuperHeroe_seleccionados.size==1){
                    val editText=EditText(this)
                    val builder_dialog=AlertDialog.Builder(this)
                        .setTitle("Edit")
                        .setView(editText)
                        .setPositiveButton("Ok"){dialog,button->
                            //Modifico el valor del elemento
                            val superheroe=SuperHeroeProveedor.SuperHeroe_seleccionados.get(0)
                            //Cambio su nombre
                            superheroe.nombre=editText.text.toString()

                            //Salgo del actionMode
                            actionMode_activo=false
                            //Actualizo los cambios de ese elemento
                            //Necesito saber que elemento es, claro
                            //Necesario implementar esto
                         //   miadaptador.cambiarDatos(SuperHeroeProveedor.SuperHeroeList.indexOf(SuperHeroeProveedor.SuperHeroe_seleccionados.get(0)),superheroe)
                            //Limpio el action mode
                            limpiar_actionMode()
                        }
                }
            }
            R.id.item_delete->{
                //Salgo del action mode
                actionMode_activo=false
                //Elimino los elementos de la lista que se han seleccionado
               // miadaptador.eliminar_elementos(SuperHeroeProveedor.SuperHeroe_seleccionados)
                //Limpio el action mode
                limpiar_actionMode()

            }

           android.R.id.home->{
                //Se ha pulsado a la flecha de atras
                actionMode_activo=false
                limpiar_actionMode()
                //Notifico de los cambios
                miadaptador.notifyDataSetChanged()
            }
        }
        return true

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            //Inflo el menu principal
            menuInflater.inflate(R.menu.menu_principal, menu)
            return super.onCreateOptionsMenu(menu)
        }


    private fun actualiza_contador() {
        var contador = SuperHeroeProveedor.SuperHeroe_seleccionados.size

        //Aparece el edit si solo hay un elemento seleccionado
        mi_toolbar.menu.getItem(0).setVisible(contador == 1)
        //Añado como titulo el nº de elementos seleccionados
        mi_toolbar.setTitle("$contador elementos seleccionados")

    }
}