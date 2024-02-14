package com.example.program

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.squareup.picasso.Picasso

@Suppress("DEPRECATION")
class CharacterDetailsActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_details)

        val character = intent.getParcelableExtra<Character>("character")
        val imageUrl = intent.getStringExtra("image")

        if (character == null || imageUrl == null) {
            Log.e("CharacterDetailsActivity", "Character or image URL is null")
            finish()
            return
        }

        findViewById<TextView>(R.id.characterNameTextView).text = "Imię: ${character.name}"
        findViewById<TextView>(R.id.characterStatusTextView).text = "Status: ${character.status}"
        findViewById<TextView>(R.id.characterSpeciesTextView).text = "Gatunek: ${character.species}"
        findViewById<TextView>(R.id.characterTypeTextView).text = "Typ: ${character.type}"
        findViewById<TextView>(R.id.characterGenderTextView).text = "Płeć: ${character.gender}"

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        findViewById<Toolbar>(R.id.toolbar).setNavigationOnClickListener { onBackPressed() }

        Picasso.get()
            .load(imageUrl)
            .placeholder(R.drawable.placeholder_image)
            .into(findViewById<ImageView>(R.id.characterImageView))
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
