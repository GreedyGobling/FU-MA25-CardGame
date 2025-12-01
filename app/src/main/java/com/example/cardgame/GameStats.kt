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

        editor.apply()
    }

    fun getGamesPlayed(context: Context): Int {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .getInt(KEY_GAMES_PLAYED, 0)
    }

}