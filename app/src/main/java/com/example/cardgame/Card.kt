package com.example.cardgame

data class Card(
    val suit: Suit,
    val rank: Rank
){
    fun getSuitSymbol(): String{
        return when (suit) {
            Suit.Heart -> "♥"
            Suit.Diamond -> "♦"
            Suit.Clubs -> "♣"
            Suit.Spades -> "♠"
        }
    }
}

enum class Suit{
    Heart, Spades, Clubs, Diamond
}

// TODO enum for the ranks

enum class Rank(val value: Int val symbol: String){
    ACE(1, "A"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    JACK(11, "J"),
    QUEEN(12, "Q"),
    KING(13, "K")
}