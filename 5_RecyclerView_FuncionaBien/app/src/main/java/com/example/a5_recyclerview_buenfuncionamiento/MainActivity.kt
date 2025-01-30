package com.example.recycledview

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a5_recyclerview_buenfuncionamiento.Coche
import com.example.a5_recyclerview_buenfuncionamiento.R
import com.example.a5_recyclerview_buenfuncionamiento.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var mibinding: ActivityMainBinding

    //defino el objeto del RecyclerView
    lateinit var mirecycler: RecyclerView

    //defino el objeto del Adapter
    lateinit var miadapter: AdaptadorRecycler

    //creo la lista de datos --> en formato data class
    var lista_datos = mutableListOf(
        Coche("Mercedes"),
        Coche("BMW"),
        Coche("Renault"),
        Coche("Opel"),
        Coche("Ford"),
        Coche("Kia"),
        Coche("Toyota"),
        Coche("Honda"),
        Coche("Hyundai"),
        Coche("Citroen"),
        Coche("Peugeot"),
        Coche("Mercedes"),
        Coche("BMW"),
        Coche("Renault"),
        Coche("Opel"),
        Coche("Ford"),
        Coche("Kia"),
        Coche("Toyota"),
        Coche("Honda"),
        Coche("Hyundai"),
        Coche("Citroen"),
        Coche("Peugeot")
    )

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //binding
        mibinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mibinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        inicializar_recyclerview()

        //manejo del FloatingActionButton
        mibinding.FABAdd.setOnClickListener {
            AlertDialog.Builder(this).apply {
                setMessage("ESCRIBE MARCA COCHE")
                val mieditText = EditText(this@MainActivity)
                setView(mieditText)
                setPositiveButton("Crear") { _, _ ->
                    lista_datos.add(Coche(mieditText.text.toString()))
                    miadapter.notifyItemChanged(lista_datos.size - 1) //obliga a invocar al metido onBindViewModel --> actualiza los datos de la lista en la posición que le indico
                    //miadapter.notifyDataSetChanged() //repinta todo --> NO ÓPTIMO
                    setCancelable(false)
                }
            }.create().show()
        }//mibinding.FABAdd.setOnClickListener
        }//onCreate


        private fun inicializar_recyclerview() {
            //instanciar el recyclerView
            this.mirecycler = mibinding.viewMirecycler

            //Definir el manejador del Layout del recycler
            this.mirecycler.layoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
            ) //LinearLayoutManager.VERTICAL --> indica como se disponen


            //añadir un separador vertical a los elementos del recyclerView
            val divisor = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
            //le asigno al recyclerView el itmeDecoration
            this.mirecycler.addItemDecoration((divisor))


            //asignar una referencia del adapter --> lo que pasa cuando clico sobre el elemnto
            this.miadapter = AdaptadorRecycler(this.lista_datos) { posicion ->
                lista_datos.get(posicion).seleccionado = !lista_datos.get(posicion).seleccionado
                //repinto los elementos
                miadapter.notifyItemChanged(posicion)
            }


            //LO SIGUIENTE EQUIVALE A LO ANTERIOR. LLAMO A LA CLASE AdaptadorRecycler Y LE PASO LOS PARÁMETROS DEL CONSTRUCTOR (lista de datos y la función) --> LA FUNCION VA ENTRE {} PQ SE TRATA DE UNA FUNCIÓN Y ES LO QUE LE PASO A LA OTRA CLASE QUE SE LLAMA "onClick_item"
            /*this.miadapter = AdaptadorRecycler(this.lista_datos, { posicion ->
                lista_datos.get(posicion).seleccionado = !lista_datos.get(posicion).seleccionado
                //repinto los elementos
                miadapter.notifyItemChanged(posicion)
            })*/


            //vinculo el RecyclerView al Adaptador
            this.mirecycler.adapter = this.miadapter
        }//inicializar_recyclerview
    }//class