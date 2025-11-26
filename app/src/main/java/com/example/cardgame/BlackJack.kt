package com.example.cardgame

class BlackJack {
    private val deck = Deck()
    private val playerHand = Hand()
    private val dealerHand = Hand()
    private var state = GameState.Betting

    fun startNewRound(){
        playerHand.clear()
        dealerHand.clear()

        playerHand.addCard(deck.draw())
        dealerHand.addCard(deck.draw())
        playerHand.addCard(deck.draw())
        dealerHand.addCard(deck.draw())

        state = GameState.Player_Turn
    }

    fun playerHit(): Card {
        val card = deck.draw()
        playerHand.addCard(card)
        if (playerHand.isBust()) {
            state = GameState.Game_Over
        }
        return card
    }

    fun playerStand() {
        state = GameState.Dealer_Turn
        dealerPlay()
    }

    fun dealerPlay(){
            // 16 minimal or 1, 2 higher
        while (dealerHand.getValue() < 16) {
            dealerHand.addCard(deck.draw())
        }
    }


    fun determineWinner(): GameResult {
        val playerValue = playerHand.getValue()
        val dealerValue = dealerHand.getValue()

        return when {
        playerHand.isBust() -> GameResult.PlayerBust
        playerHand.isBlackJack() && !dealerHand.isBlackJack() -> GameResult.PlayerBlackJack
        dealerHand.isBlackJack() && !playerHand.isBlackJack() -> GameResult.DealerWin
        dealerHand.isBust() -> GameResult.DealerBust
        playerValue > dealerValue -> GameResult.PlayerWin
        dealerValue > playerValue -> GameResult.DealerWin
        else -> GameResult.Push

    }
    }

    fun getPlayerHand(): Hand = playerHand
    fun getDealerHand(): Hand = dealerHand
    fun getState(): GameState = state
    fun getDeck(): Deck = deck

}