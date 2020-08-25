package com.ennoblesoft.rickandmorty.ui.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ennoblesoft.rickandmorty.data.entities.Character
import com.ennoblesoft.rickandmorty.databinding.ItemCharacterBinding
import com.ennoblesoft.rickandmorty.ui.listener.ItemClickListener

class CharacterAdapter(private val listener: ItemClickListener) :
    RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {
    private val items = ArrayList<Character>()

    fun setItems(items: ArrayList<Character>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = items[position]
        holder.binding.item = character
        holder.binding.listener = listener
    }

    class CharacterViewHolder(val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root)
}