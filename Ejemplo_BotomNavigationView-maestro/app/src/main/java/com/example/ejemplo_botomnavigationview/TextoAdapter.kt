package com.example.ejemplo_botomnavigationview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.ejemplo_botomnavigationview.databinding.TextoreciclableBinding

class TextoAdapter(var lista:List<String>, var funcionPadre:(String)->Unit) : RecyclerView.Adapter<TextoHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextoHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.textoreciclable, parent, false)

        return TextoHolder(view)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: TextoHolder, position: Int) {
        //Metodos del Holder
        holder.bind(lista[position], funcion = {
            funcionPadre(lista[position])
        })
    }
}

class TextoHolder(var v:View) : ViewHolder(v){
    lateinit var binding:TextoreciclableBinding
    fun bind(texto:String, funcion:()->Unit){ //Recibe una función lambda
        binding = TextoreciclableBinding.bind(v)
        binding.textView2.setText(texto)
        v.setOnClickListener{
            funcion()
        }
    }
}