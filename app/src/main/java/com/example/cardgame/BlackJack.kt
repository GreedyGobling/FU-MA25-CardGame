package com.example.cardgame

class BlackJack {
    private val deck = Deck()
    private val playerHand = Hand()
    private val dealerHand = Hand()
    private var state = GameState.BETTING

    fun startNewRound(){
        playerHand.clear()
        dealerHand.clear()

        playerHand.addCard(deck.draw())
        dealerHand.addCard(deck.draw())
        playerHand.addCard(deck.draw())
        dealerHand.addCard(deck.draw())

        state = GameState.PLAYER_TURN
    }

    fun playerHit(): Card {
        val card = deck.draw()
        playerHand.addCard(card)
        if (playerHand.isBust()) {
            state = GameState.GAME_OVER
        }
        return card
    }

    fun playerStand() {
        state = GameState.DEALER_TURN
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
        playerHand.isBust() -> GameResult.PLAYERBUST
        playerHand.isBlackJack() && !dealerHand.isBlackJack() -> GameResult.PLAYEBLACKJACK
        dealerHand.isBlackJack() && !playerHand.isBlackJack() -> GameResult.DEALERBLACKJACK
        dealerHand.isBust() -> GameResult.DEALERBUST
        playerValue > dealerValue -> GameResult.PLAYERWIN
        dealerValue > playerValue -> GameResult.DEALERWIN
        else -> GameResult.PUSH

    }
    }

    fun getPlayerHand(): Hand = playerHand
    fun getDealerHand(): Hand = dealerHand
    fun getState(): GameState = state
    fun getDeck(): Deck = deck

}