package com.dadang.resepmakanan_dadangwibowo020

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.list_item_makanan.view.*

class RVMakananAdapter : RecyclerView.Adapter<RVMakananAdapter.ViewHolder>() {
    private lateinit var itemList: List<ListItemMakanan>
    lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_makanan, parent, false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int {
        return if (::itemList.isInitialized) itemList.size else 0
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }
    fun updateData(list: List<ListItemMakanan>) {
        itemList = list;
        notifyDataSetChanged()
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {
            val item = itemList.get(adapterPosition)
            itemView.tv_makanan.text = item.title
            Glide.with(context)
                    .load(item.thumb)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(itemView.image_view)
            itemView.tv_waktu.text = item.times
            itemView.tv_porsi.text = item.portion
            itemView.tv_level.text = item.dificulty

        }
    }
}

