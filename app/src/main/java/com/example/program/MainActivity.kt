package com.example.program

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val showCharacterListButton: Button = findViewById(R.id.showCharacterListButton)


        showCharacterListButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.showCharacterListButton -> {

                val intent = Intent(this, CharacterListActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
