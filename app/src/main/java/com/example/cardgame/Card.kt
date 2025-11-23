package com.example.cardgame

data class Card(
    val suit: suit

)

enum class suit{
    Heart, Spades, Clubs, Diamond
}

// TODO enum for the ranks
