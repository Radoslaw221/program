package com.example.program

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterListActivity : AppCompatActivity(), CharacterAdapter.OnCharacterClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var characterAdapter: CharacterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.character_list_layout)

        recyclerView = findViewById(R.id.characterRecyclerView)

        fetchCharacters()
    }

    private fun fetchCharacters() {
        val call = ApiClient.apiService.getCharacters(page = 1, limit = 20)

        call.enqueue(object : Callback<CharacterResponse> {
            override fun onResponse(call: Call<CharacterResponse>, response: Response<CharacterResponse>) {
                if (response.isSuccessful) {
                    val characterResponse = response.body()
                    val characters = characterResponse?.characters ?: emptyList()
                    characterAdapter = CharacterAdapter(characters)
                    characterAdapter.setOnCharacterClickListener(this@CharacterListActivity)
                    recyclerView.apply {
                        layoutManager = LinearLayoutManager(this@CharacterListActivity)
                        adapter = characterAdapter
                    }
                } else {
                    // Obsłuż błąd odpowiedzi z serwera
                }
            }

            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                // Obsłuż błąd komunikacji
            }
        })
    }

    override fun onCharacterClick(character: Character) {
        val imageUrl = character.image

        val intent = Intent(this, CharacterDetailsActivity::class.java)
        intent.putExtra("character", character as Parcelable)
        intent.putExtra("image", imageUrl)
        startActivity(intent)
    }


}
