package com.example.cardgame.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.example.cardgame.GameActivity
import com.example.cardgame.R

class GameMenuFragment : DialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_gamemenu,container, false)

        // if current streak > 0 continy
        val newGameButton = view.findViewById<Button>(R.id.btnNewgame)

        // new game if above is true
        val wipButton = view.findViewById<Button>(R.id.btnWIP)

        newGameButton.setOnClickListener {
            val intent = Intent(requireContext(), GameActivity::class.java)
            startActivity(intent)
            dismiss()
        }

        return view
    }


}