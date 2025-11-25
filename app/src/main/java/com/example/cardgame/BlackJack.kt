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

}