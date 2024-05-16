package com.handy.projetfinalenativelependu

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class JeuPendu : AppCompatActivity() {
    // Declaration des boutons de jeux
    lateinit var btnA : Button
    lateinit var btnB : Button
    lateinit var btnC : Button
    lateinit var btnD : Button
    lateinit var btnE : Button
    lateinit var btn5 : Button
    lateinit var btn6 : Button
    lateinit var btn7 : Button
    lateinit var btn8 : Button





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    
    fun showA(view: android.view.View){
        if(view is Button){
            val b:Button = view
            b.isEnabled = false
        }
    }
}