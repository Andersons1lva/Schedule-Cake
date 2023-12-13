package com.anderson.schedulecake.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anderson.schedulecake.R
import com.anderson.schedulecake.model.Cliente

class Adapter(private val listaClientes:MutableList<Cliente>
): RecyclerView.Adapter<Adapter.MyViewHolder>() {

    // Método que exiber as linhas
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_adapter,parent,false)
        return MyViewHolder(itemView)
    }
    //Método retorna o tamanho da lista
    override fun getItemCount(): Int{
        return  listaClientes.size
    }

    //Método que exiber informações
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val cliente = listaClientes[position]

        holder.bind(cliente)
    }

    fun atualizarClientes(novaLista: List<Cliente>){
        listaClientes.clear()
        listaClientes.addAll(novaLista)
        notifyDataSetChanged()

    }

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
       private val textNome: TextView = itemView.findViewById(R.id.textNome)
       fun bind(cliente: Cliente) {
           textNome.text = "${cliente.nome}"
       }
    }
}