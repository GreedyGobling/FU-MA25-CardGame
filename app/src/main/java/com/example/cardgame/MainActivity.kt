package com.example.cardgame

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.cardgame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        binding.startmenu.setOnClickListener {
            // TODO
            // if active game is true show 2 button. continy and new game
            // if active game is false start new game
        }


    }



    fun addStartMenu(){
        val startMenu = Gamemenu()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.container, startMenu, startMenu)
    }
}