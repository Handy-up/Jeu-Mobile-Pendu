package com.handy.projetfinalenativelependu

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Accueil : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accueil)
        enableEdgeToEdge()





        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 1- connect xml with Kotlin
        // 2- creat var adapter
        // 3- var arrayVal = ArrayAdapter.createFromResource(this, R.array.list_diff, android.R.layout.simple_spinner_item)
        // 4-

        // var arrayVal = ArrayAdapter.createFromResource(this, R.array.list_diff, android.R.layout.simple_spinner_item)
        // arrayVal.setDropDownViewResource(android.R.layout.simple_spinner_item)
        // varibleLieaAdapter.adapter = arrayVal

    }

    // OnClick Game Navigation
    fun quiteGame(view: android.view.View){
        finishAffinity()
    }

    fun startGame(view: android.view.View){
        val intent = Intent(this, JeuPendu::class.java)
        startActivity(intent)
    }

    fun goPreferences(view: android.view.View){
        val intent = Intent(this, SettingsView::class.java)
        startActivity(intent)
    }

    fun showStory(view: android.view.View){
        val intent = Intent(this, HistoriqueView::class.java)
        startActivity(intent)
    }
}