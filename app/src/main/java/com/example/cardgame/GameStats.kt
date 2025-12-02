package com.example.cardgame

import android.content.Context

object GameStats {
    private const val PREFS_NAME = "blackjack_stats"
    private const val KEY_GAMES_PLAYED = "games_played"
    private const val KEY_GAMES_WON = "games_won"
    private const val KEY_GAMES_LOST = "games_lost"
    private const val KEY_BLACKJACKS = "blackjacks"
    private const val KEY_CURRENT_STREAK = "current_streak"
    private const val KEY_BEST_STREAK = "best_streak"

    // TODO fix savegameresult and GET for all other
    fun saveGameResult(context: Context, result: GameResult) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = prefs.edit()

        val gamesPlayed = prefs.getInt(KEY_GAMES_PLAYED, 0)
        editor.putInt(KEY_GAMES_PLAYED, gamesPlayed + 1)

        val playerWon = when (result) {
            GameResult.PLAYERWIN, GameResult.PLAYEBLACKJACK, GameResult.DEALERBUST -> true
            else -> false
        }

        if (playerWon) {
            val gamesWon = prefs.getInt(KEY_GAMES_WON, 0)
            editor.putInt(KEY_GAMES_WON, gamesWon + 1)

            val currentStreak = prefs.getInt(KEY_CURRENT_STREAK, 0) + 1
            editor.putInt(KEY_CURRENT_STREAK, currentStreak)

            val bestStreak = prefs.getInt(KEY_BEST_STREAK, 0)
            if (currentStreak > bestStreak) {
                editor.putInt(KEY_BEST_STREAK, currentStreak)
            }
        } else {
            val gamesLost = prefs.getInt(KEY_GAMES_LOST, 0)
            editor.putInt(KEY_GAMES_LOST, gamesLost + 1)
            editor.putInt(KEY_CURRENT_STREAK, 0)
        }

        if (result == GameResult.PLAYEBLACKJACK) {
            val blackjacks = prefs.getInt(KEY_BLACKJACKS, 0)
            editor.putInt(KEY_BLACKJACKS, blackjacks + 1)
        }
        editor.apply()
    }

    fun getGamesPlayed(context: Context): Int {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .getInt(KEY_GAMES_PLAYED, 0)
    }

}