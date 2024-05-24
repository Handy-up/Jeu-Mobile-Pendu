package com.handy.projetfinalenativelependu

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HistoriqueItem : AppCompatActivity() {
    private lateinit var motJouer:TextView
    private lateinit var tempsJouer:TextView
    private lateinit var resultatPartie:TextView
    private lateinit var dificulte:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_historique_item)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        motJouer = findViewById(R.id.txt_motJouer)
        tempsJouer = findViewById(R.id.txt_tempsDeJeux)
        resultatPartie = findViewById(R.id.txt_resultat)
        dificulte = findViewById(R.id.txt_difficulte)
    }

    fun backPage(){
        val intent = Intent(this, Accueil::class.java)
        startActivity(intent)
    }
}