package com.handy.projetfinalenativelependu

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultView : AppCompatActivity() , View.OnClickListener {
    private lateinit var btn_restart :Button
    private lateinit var btn_quitt:Button
    private lateinit var messageEnd:TextView
    private lateinit var imageEnd:ImageView
    private lateinit var reslutat:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btn_restart = findViewById(R.id.btn_restart_game)
        btn_quitt = findViewById(R.id.btn_quit_game)
        messageEnd = findViewById(R.id.message_end)
        imageEnd = findViewById(R.id.image_end)

        btn_quitt.setOnClickListener(this)
        btn_restart.setOnClickListener(this)

        // Les intentions
        val extras = intent.extras
        val messageExtra = extras?.getString("message")
        val motSecret = extras?.getString("motSecret")
        val temps = extras?.getString("tempsDeJeux")
        val erreurs = extras?.getString("nbErr")
        messageEnd.text = messageExtra

        if (messageExtra=="Vous avez gagné !"){
            reslutat = "Parti Gagné"
            imageEnd.setImageResource(R.drawable.winner)
        }else{
            reslutat = "Parti Perdu"
            imageEnd.setImageResource(R.drawable.err06)
        }

        //Database
        val gameInfo = Historique(motSecret.toString(),"facile",temps.toString(),reslutat)
        val dbHelper = DatabaseHelper(this)
        dbHelper.insertHistorique(gameInfo)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_restart_game->{
                val intent = Intent(this, JeuPendu::class.java)
                startActivity(intent)
            }
            R.id.btn_quit_game -> {
                val intent = Intent(this, Accueil::class.java)
                startActivity(intent)
            }
        }
    }
}