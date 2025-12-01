package com.example.cardgame

data class Stats(
    var gamesPlayed: Int = 0,
    var gameWon: Int = 0,
    var gameLost: Int = 0,
    var blackjacks: Int = 0,
    var currentStreak: Int = 0,
    var bestStreak: Int = 0,
)

object GameStats {

}