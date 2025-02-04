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
        public var actionMode_activo = false //el estado de la toolbar

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
        actionMode_activo = false;
        //Limpio el menu
        mi_toolbar.menu.clear()
        //Inflo el menu principal
        mi_toolbar.inflateMenu(R.menu.menu_principal)
        //Desaparece la flecha hacia Arriba
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        //Pongo el titulo de la aplicacion
        mi_toolbar.title = ContextCompat.getString(this, R.string.app_name)
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
        miadaptador = SuperHeroeAdaptador(SuperHeroeProveedor.SuperHeroeList, //lista de superhéroes
            SuperHeroeProveedor.SuperHeroe_seleccionados, //lista de superhéroes seleccionados
            { pos -> preparo_toolbar(pos) }, //clic largo
            { pos -> preparo_selecion(pos) }) //clic corto --> solo cuando anteriormente se ha seleccionado algún elemento con clic largo
        mirecyclerView.adapter = miadaptador
    }

    //Funcion que prepara el toolbar cuando hago pulsacion larga sobre un elemento
    fun preparo_toolbar(posicion: Int) {
        //Solo preparo el toolbar si no lo he preparado antes
        if (!actionMode_activo) {
            //Primero borro el menu que hubiera en Toolbar
            mi_toolbar.menu.clear()
            //Inflo el menu de accion contextual casero
            mi_toolbar.inflateMenu(R.menu.menu_accion_contextual)
            //Indico que estamos en ActionMode
            actionMode_activo = true

            //Habilita el botón de "navegación hacia arriba" (o "back")
            // en la barra de acciones (ActionBar o Toolbar) del Activity.
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            //llamo al método que guarda todos los elementos seleccionados
            preparo_selecion(posicion)
        }
    }

    //Funcion para guardar en una lista los elementos que voy seleccionando, clickcorto
    fun preparo_selecion(posicion: Int) {
        //Solo tiene efecto si esta el actionMode activo
        if (actionMode_activo) {

            //es tipo un companion object que tiene acceso a la clase indicada
            with(SuperHeroeProveedor) {
                //val superHeroe = SuperHeroeList[posicion]
                if (!SuperHeroe_seleccionados.contains(posicion)) {
                    SuperHeroe_seleccionados.add(posicion)
                } else {
                    SuperHeroe_seleccionados.remove(posicion)
                }
                actualiza_contador()
                //Notifico de los cambios al adaptador
                miadaptador.notifyItemChanged(posicion)
            }
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        when (item.itemId) { //item.itemId me devuelve el id del item seleccionado
            R.id.item_edit -> {
                //Aunque el elemento de menu edit solamente
                //aparece con 1 elemento seleccionado me aseguro por si acaso
                if (SuperHeroeProveedor.SuperHeroe_seleccionados.size == 1) {
                    val editText = EditText(this)
                    //Modifico el valor del elemento seleccionado
                    //superheroe_indice obtiene el elemento seleccionado y le da la posicion 0
                    val superheroe_indice = SuperHeroeProveedor.SuperHeroe_seleccionados.get(0)
                    val super_hero_seleccionado =
                        SuperHeroeProveedor.SuperHeroeList.get(superheroe_indice)
                    editText.setText(super_hero_seleccionado.nombre)
                    val builder_dialog = AlertDialog.Builder(this)
                        .setTitle("Nombre SuperHeroe")
                        .setView(editText)
                        .setPositiveButton("Ok") { dialog, button ->
                            //Modifico el valor del elemento

                            //Cambio su nombre
                            super_hero_seleccionado.nombre = editText.text.toString()

                            //Salgo del actionMode
                            actionMode_activo = false
                            //Actualizo los cambios de ese elemento
                            miadaptador.notifyItemChanged(superheroe_indice)
                            //Necesito saber que elemento es, claro
                            //Necesario implementar esto
                            //   miadaptador.cambiarDatos(SuperHeroeProveedor.SuperHeroeList.indexOf(SuperHeroeProveedor.SuperHeroe_seleccionados.get(0)),superheroe)
                            //Limpio el action mode
                            limpiar_actionMode()

                        }.create().show()
                }
            }

            R.id.item_delete -> {
                //Salgo del action mode
                actionMode_activo = false
                //Elimino los elementos de la lista que se han seleccionado
                // miadaptador.eliminar_elementos(SuperHeroeProveedor.SuperHeroe_seleccionados)
                SuperHeroeProveedor.SuperHeroeList.removeIf {
                    SuperHeroeProveedor.SuperHeroe_seleccionados.contains(
                        SuperHeroeProveedor.SuperHeroeList.indexOf(it) //it es el superheroe
                    )

                }
                miadaptador.notifyDataSetChanged()
                //Limpio el action mode
                limpiar_actionMode()

            }

            android.R.id.home -> {
                //Se ha pulsado a la flecha de atras
                actionMode_activo = false
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


    //el menú solo tiene dos item (pq así está puesto en el menu__accion_contextual.xml) el resto les ayudo manualmente
    private fun actualiza_contador() {
        var contador = SuperHeroeProveedor.SuperHeroe_seleccionados.size

        //Aparece el edit si solo hay un elemento seleccionado
        mi_toolbar.menu.getItem(0).setVisible(contador == 1) //solo es visible si el contador es 1, sino no
        //Añado como titulo el nº de elementos seleccionados
        mi_toolbar.setTitle("$contador elementos seleccionados")
    }
}