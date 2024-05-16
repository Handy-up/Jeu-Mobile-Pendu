package com.handy.projetfinalenativelependu

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Accueil : AppCompatActivity() {

    lateinit var btnStart: Button
    lateinit var btn_quit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accueil)
        enableEdgeToEdge()

        val btnStart = findViewById<Button>(R.id.btnStart)
        val btn_quit = findViewById<Button>(R.id.btn_quit)

        btnStart.setOnClickListener {
            val intent = Intent(this, JeuPendu::class.java)
            startActivity(intent)
        }

        btn_quit.setOnClickListener{
            finish()
        }

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
}