package com.example.cardgame

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cardgame.databinding.ActivityGameBinding
import com.example.cardgame.databinding.CardViewBinding

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding

    private val game = BlackJack()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startNewRound()

        // Buttons hit and stand
    binding.bstand.setOnClickListener {
        onStandClick()
    }
    binding.bhit.setOnClickListener {
        onHitClick()
    }

}

    private fun startNewRound(){
        // clear ui
        binding.playerHandContainer.removeAllViews()

        game.startNewRound()

        //init hands
        updatePlayerHand()


        if(game.getPlayerHand().isBlackJack()){
            onStandClick()
        }
    }

    private fun updatePlayerHand(){
        val playerHand = game.getPlayerHand()
        for (card in playerHand.getCards()) {
            displayCard(card, binding.playerHandContainer)
        }
    }

    private fun onHitClick(){
        game.playerHit()
        updatePlayerHand()
        //TODO update for hand draw
        if (game.getPlayerHand().isBust()) {
            endGame()
        }
    }

    private fun onStandClick(){
        game.playerStand()
        // update dealerhand if needed
        endGame()
    }

    private fun displayCard(card: Card, container: LinearLayout){
        val cardBinding = CardViewBinding.inflate(LayoutInflater.from(this))
        val cardView = cardBinding.root

        cardBinding.rankText.text = card.rank.symbol
        cardBinding.suitText.text = card.getSuitSymbol()

        val textColor = if (card.isRed()){
            ContextCompat.getColor(this, R.color.red)
        } else {
            ContextCompat.getColor(this, R.color.black)
        }

        container.addView(cardView)
    }


    private fun endGame(){
        val result = game.determineWinner()
    }

    //TODO create Card for win/lose
}