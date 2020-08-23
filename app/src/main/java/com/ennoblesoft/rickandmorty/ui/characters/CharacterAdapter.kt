package com.ennoblesoft.rickandmorty.ui.characters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
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
        return CharacterViewHolder(binding, listener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class CharacterViewHolder(
        private val binding: ItemCharacterBinding,
        private val listener: ItemClickListener
    ) : RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {
        private lateinit var character: Character

        init {
            binding.root.setOnClickListener(this)
        }

        @SuppressLint("SetTextI18n")
        fun bind(item: Character) {
            this.character = item
            binding.tvName.text = item.name
            binding.tvStatus.text = """${item.species} - ${item.status}"""
            Glide.with(binding.root)
                .load(item.image)
                .transform(CircleCrop())
                .into(binding.image)
        }

        override fun onClick(p0: View?) {
            listener.onItemClicked(character.id)
        }
    }
}