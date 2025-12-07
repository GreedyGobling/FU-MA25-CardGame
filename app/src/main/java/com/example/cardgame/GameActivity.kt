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
import com.example.cardgame.fragment.GameResultFragment
import kotlin.coroutines.Continuation

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding

    private var game = BlackJack()

    private val gson = com.google.gson.Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startNewRound()

        binding.bstand.setOnClickListener {
        onStandClick()
    }
        binding.bhit.setOnClickListener {
        onHitClick()
    }
        binding.midGameExit.setOnClickListener {
            finish()
        }

}

    override fun onPause() {
        super.onPause()
        saveGameState()
    }

    private fun saveGameState(){
        if (game.getPlayerHand().getValue() > 0) {
            val json = gson.toJson(game)
            val prefs = getSharedPreferences("blackjack_state", Context.MODE_PRIVATE)
            prefs.edit().putString("saved_game_state", json).apply()
        }
    }

    private fun loadGameState(): Boolean {
        val prefs = getSharedPreferences("blackjack_state", Context.MODE_PRIVATE)
        val json = prefs.getString("saved_game_state", null)
        if (json != null) {
            val savedGame = gson.fromJson(json, BlackJack::class.java)
            game = savedGame
            updatePlayerHand()
            updateDealerHand()
            return true
        }
        return false
    }

    fun startNewRound(){
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

        binding.playerScore.text = "${playerHand.getValue()}"
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
        if (game.getPlayerHand().isBust()) {
            endGame()
        }
        if (game.getPlayerHand().getValue() == 21){
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
        val prefs = getSharedPreferences("blackjack_state", Context.MODE_PRIVATE)
        prefs.edit().remove("saved_game_state").apply()
        showGameResult(result)
    }

    private fun showGameResult(result: GameResult) {
        val fragment = GameResultFragment()
        val args = Bundle()
        args.putSerializable("game_result", result)
        fragment.arguments = args
        fragment.show(supportFragmentManager, "GameResultFragment")
    }

}