package com.anderson.schedulecake.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anderson.schedulecake.R

class Adapter(
    private val listaEcomenda:List<String>
): RecyclerView.Adapter<Adapter.MyViewHolder>() {

    // Método que exiber as linhas
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_adapter,parent,false)
        return MyViewHolder(itemView)
    }
    //Método retorna o tamanho da lista
    override fun getItemCount() = listaEcomenda.size

    //Método que exiber informações
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val nome = listaEcomenda[position]

        holder.textNome.text = nome
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textNome: TextView = itemView.findViewById(R.id.textNome);
    }
}