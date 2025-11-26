package com.example.cardgame

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cardgame.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding

    private val game = BlackJack()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Buttons hit and stand
    }

    private fun onHitClicked(){
        game.playerHit()
        //TODO update for hand draw
        if (game.getPlayerHand().isBust()) {
            endGame()
        }
    }
    private fun onStandClicked(){
        game.playerStand()
        // update dealerhand if needed
        endGame()
    }

    private fun endGame(){
        val result = game.determineWinner()
    }

    //TODO create Card for win/lose
}