package com.example.cardgame.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.example.cardgame.GameActivity
import com.example.cardgame.GameResult
import com.example.cardgame.R
import com.example.cardgame.databinding.FragmentGameResultBinding

class GameResultFragment : DialogFragment() {

    private var _binding: FragmentGameResultBinding? = null
    private val binding get() = _binding!!

    private lateinit var gameResult: GameResult

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameResultBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        isCancelable = false

        val (message, colorRes) = when (gameResult) {
            GameResult.PLAYEBLACKJACK -> "BLACKJACK! YOU WIN!" to R.color.win
            GameResult.DEALERBLACKJACK -> "DEALER HAS BLACKJACK. YOU LOSE." to R.color.lose
            GameResult.PLAYERWIN -> "YOU WIN!" to R.color.win
            GameResult.DEALERWIN -> "DEALER WINS." to R.color.lose
            GameResult.PUSH -> "PUSH" to R.color.push
            GameResult.PLAYERBUST -> "YOU BUSTED. DEALER WINS." to R.color.lose
            GameResult.DEALERBUST-> "DEALER BUSTED. YOU WIN!" to R.color.win
        }
        binding.resultMessage.text = message
        binding.resultMessage.setTextColor(
            ContextCompat.getColor(requireContext(), colorRes)
        )

        binding.newGameButton.setOnClickListener {
            (activity as? GameActivity)?.startNewRound()
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}