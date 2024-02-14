package com.example.program

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CharacterAdapter(private val characters: List<Character>) :
    RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    // Interfejs do obsługi kliknięcia na element
    interface OnCharacterClickListener {
        fun onCharacterClick(character: Character)
    }

    private var characterClickListener: OnCharacterClickListener? = null

    // Metoda ustawiająca nasłuchiwanie na kliknięcie
    fun setOnCharacterClickListener(listener: OnCharacterClickListener) {
        characterClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.character_item_layout, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = characters[position]
        holder.characterName.text = character.name

        Glide.with(holder.itemView)
            .load(character.image)
            .placeholder(R.drawable.placeholder_image)
            .into(holder.characterImage)

        // Obsługa kliknięcia na element
        holder.itemView.setOnClickListener {
            characterClickListener?.onCharacterClick(character)
        }
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val characterName: TextView = itemView.findViewById(R.id.characterName)
        val characterImage: ImageView = itemView.findViewById(R.id.characterImage)
    }
}
