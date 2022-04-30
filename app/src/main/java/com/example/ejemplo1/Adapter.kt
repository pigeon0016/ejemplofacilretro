package com.example.ejemplo1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class Adapter(val articulos : List<Resultado>,private val onResClickListener: OnResClickListener): RecyclerView.Adapter<MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return MainViewHolder(inflater.inflate(R.layout.item_articulo,parent, false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        //mandar lamar al viewholder para pintar el elemento
        val articulo : Resultado = articulos.get(position)
        holder.render(articulo)
        holder.itemView.setOnClickListener{
            onResClickListener.onResItemClicked(position)
        }

    }

    override fun getItemCount(): Int {
       return articulos.size

    }



}


class MainViewHolder(val view:View):RecyclerView.ViewHolder(view) {
     fun render(articulo:Resultado){
         view.findViewById<TextView>(R.id.title).text = articulo.title
         view.findViewById<TextView>(R.id.date).text = articulo.published_date
         Picasso.get().load(articulo.media[0].metadata[0].urlmetadata).into(view.findViewById<ImageView>(R.id.imageView))

     }
}
