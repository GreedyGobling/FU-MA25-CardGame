package com.example.cardgame

class Hand {
    private val cards = mutableListOf<Card>()

    fun addCard(card: Card){
        cards.add(card)
    }

    fun getValue(): Int {
        var total = 0
        var aces = 0

        for (card in cards) {
            if (card.rank == Rank.ACE) {
                aces++
                total += 11
            } else {
                total += card.rank.value
            }
        }

        // avoid bust
        while (total > 21 && aces > 0) {
            total -= 10
            aces--
        }

        return total
    }

    fun isBust(): Boolean {
        return getValue() > 21
    }

    fun isBlackJack(): Boolean {
        return cards.size == 2 && getValue() == 21
    }

    fun getCards(): List<Card> {
        return cards.toList()
    }

    fun clear() {
        cards.clear()
    }

    fun size(): Int {
        return cards.size
    }

}