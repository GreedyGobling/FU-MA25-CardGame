package com.example.cardgame

class Deck {
    private val cards = mutableListOf<Card>()

    init {
        reset()
    }
    fun shuffle(){
        cards.shuffle()
    }

    fun draw(): Card {
        if (cards.isEmpty()) {
            reset()
        }
        return cards.removeAt(0)
        // return cards.removeFirst() api issue
    }

    fun reset() {
        cards.clear()

        // should do 54 cards. 1 ide hard dificultiy give it 4 or 6 decks
        for (suit in Suit.values()) {
            for (rank in Rank.values()){
                cards.add(Card(suit, rank))
            }
        }
        shuffle()
    }

    fun remainingCards(): Int {
        return cards.size
    }
}