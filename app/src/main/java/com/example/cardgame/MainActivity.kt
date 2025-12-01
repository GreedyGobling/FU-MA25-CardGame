package com.example.cardgame

import android.R
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.cardgame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.startmenu.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)

        }


    }

    override fun onResume() {
        super.onResume()
        binding.textView.text = "Games Played: ${GameStats.getGamesPlayed(this)}"
    }

}