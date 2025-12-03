package com.example.cardgame

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class GameMenuFragment : DialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_gamemenu,container, false)

        val newGameButton = view.findViewById<Button>(R.id.btnNewgame)
        val wipButton = view.findViewById<Button>(R.id.btnWIP)

        newGameButton.setOnClickListener {
            val intent = Intent(requireContext(), GameActivity::class.java)
            startActivity(intent)
            dismiss()
        }

        return view
    }


}