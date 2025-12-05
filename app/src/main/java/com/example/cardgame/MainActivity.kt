package com.example.cardgame

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.cardgame.databinding.ActivityMainBinding
import com.example.cardgame.fragment.GameMenuFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        binding.startmenu.setOnClickListener {
            //val intent = Intent(this, GameActivity::class.java)
            //startActivity(intent)

            if (GameStats.getCurrentStreak(this) > 0) {
                val gameMenu = GameMenuFragment()
                gameMenu.show(supportFragmentManager, "GameMenuDialog")

            } else {
                val intent = Intent(this, GameActivity::class.java)
                startActivity(intent)
            }
        }

        binding.statsmenu.setOnClickListener {
            val intent = Intent(this, StatisticsActivity::class.java)
            startActivity(intent)
        }
    }

}