package com.example.cardgame

import android.content.Context
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
        binding.dealerHandContainer.removeAllViews()

        game.startNewRound()

        //init hands
        updatePlayerHand()
        updateDealerHand()


        if(game.getPlayerHand().isBlackJack()){
            onStandClick()
        }
    }

    private fun updatePlayerHand(){
        binding.playerHandContainer.removeAllViews()

        val playerHand = game.getPlayerHand()
        for (card in playerHand.getCards()) {
            displayCard(card, binding.playerHandContainer)
        }

        binding.playerScore.text = "Score: ${playerHand.getValue()}"
    }

    private fun updateDealerHand(){
        binding.dealerHandContainer.removeAllViews()

        var dealerhand = game.getDealerHand()
        val cards = dealerhand.getCards()

        cards.forEachIndexed { index, card ->
            displayCard(card, binding.dealerHandContainer)
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
        updateDealerHand()
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

        GameStats.saveGameResult(this, result)
        showGameResult(result)


    }

    private fun showGameResult(result: GameResult){
        val (message, colorRes) = when (result) {
            GameResult.PLAYEBLACKJACK -> "BLACKJACK! YOU WIN!" to R.color.win
            GameResult.DEALERBLACKJACK -> "DEALER HAS BLACKJACK. YOU LOSE." to R.color.lose
            GameResult.PLAYERWIN -> "YOU WIN!" to R.color.win
            GameResult.DEALERWIN -> "DEALER WINS." to R.color.lose
            GameResult.PUSH -> "PUSH" to R.color.push
            GameResult.PLAYERBUST -> "YOU BUSTED. DEALER WINS." to R.color.lose
            GameResult.DEALERBUST-> "DEALER BUSTED. YOU WIN!" to R.color.win

        }

        // this create the box
        val builder = androidx.appcompat.app.AlertDialog.Builder(this)
        builder.setTitle("Game Over")
        builder.setMessage(message)
        builder.setCancelable(false)

        val dialog = builder.create()
        dialog.setOnShowListener {
            val messageView = dialog.findViewById<android.widget.TextView>(android.R.id.message)
            messageView?.setTextColor(ContextCompat.getColor(this, colorRes))
            messageView?.textSize = 24f
            messageView?.gravity = android.view.Gravity.CENTER
        }


        builder.setPositiveButton("NEW GAME") { _, _ ->
            startNewRound()
        }

        builder.show()
    }
}