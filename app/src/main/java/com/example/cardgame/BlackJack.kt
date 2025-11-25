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

    }

    fun playerHit(): Card {
        val card = deck.draw()
        playerHand.addCard(card)
        if (playerHand.isBust()) {
            state = GameState.Game_Over
        }
        return card
    }

    fun plauerStand() {
        state = GameState.Dealer_Turn
        dealerPlay()
    }

    fun dealerPlay(){
            // 16 minimal or 1, 2 higher
        while (dealerHand.getValue() < 16) {
            dealerHand.addCard(deck.draw())
        }
    }


    fun determineWinner() {
        val playerValue = playerHand.getValue()
        val dealerValue = dealerHand.getValue()

        // TODO make game result
        //return when {
            //playerValue > dealerValue
        //}

    }

    fun getPlayerHand(): Hand = playerHand
    fun getDealerHand(): Hand = dealerHand
    fun getState(): GameState = state
    fun getDeck(): Deck = deck
}