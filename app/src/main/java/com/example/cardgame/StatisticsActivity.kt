package com.example.cardgame

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cardgame.databinding.ActivityStatisticsBinding

class StatisticsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStatisticsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStatisticsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val statsObject = GameStats

        callNumbers()

        binding.btnBack.setOnClickListener {
            finish()
        }
        binding.btnReset.setOnClickListener {
            statsObject.resetStats(this)
            callNumbers()
        }
    }

    fun callNumbers(){
        val statsObject = GameStats
        binding.text1.text = "Games Played: ${statsObject.getGamesPlayed(this)}"
        binding.text2.text = "Games Won: ${statsObject.getGamesWon(this)}"
        binding.text3.text = "Games Lost: ${statsObject.getGamesLost(this)}"
        binding.text4.text = "Current Win Streak: ${statsObject.getCurrentStreak(this)}"
        binding.text5.text = "Best Win Streak: ${statsObject.getBestStreak(this)}"
        binding.text6.text = "BlackJacks: ${statsObject.getBlackJackCount(this)}"
    }
}