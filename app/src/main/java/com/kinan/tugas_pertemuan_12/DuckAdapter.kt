package com.kinan.tugas_pertemuan_12

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kinan.tugas_pertemuan_12.databinding.ItemDuckBinding
import com.kinan.tugas_pertemuan_12.model.Bebek

typealias OnClickUpdate = (Duck) -> Unit
typealias OnClickDelete = (Duck) -> Unit

class DuckAdapter (
    private val listBebek: MutableList<Duck>,
    private val onClickUpdate: OnClickUpdate,
    private val onClickDelete: OnClickDelete
) : RecyclerView.Adapter<DuckAdapter.ItemDuckViewHolder>(){
    inner class ItemDuckViewHolder(private val binding: ItemDuckBinding)
     :RecyclerView.ViewHolder(binding.root){
            fun bind(data: Duck){
                with(binding){
                    Glide.with(itemView.context)
                        .load(data.url)
                        .into(imageDuck)
                    namaBebek.text = data.title
                    descriptionBebek.text = data.description
                    messageBebek.text = data.message
                    updateButton.setOnClickListener{
                        onClickUpdate(data)
                    }
                    deleteButton.setOnClickListener{
                        onClickDelete(data)
                    }

                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemDuckViewHolder {
        val binding = ItemDuckBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)
        return ItemDuckViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listBebek.size
    }

    override fun onBindViewHolder(holder: ItemDuckViewHolder, position: Int) {
        holder.bind(listBebek[position])
    }

}


