package com.handy.projetfinalenativelependu

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class JeuPendu : AppCompatActivity() {
    // Declaration des vues et variables
    private lateinit var timer: TextView
    private lateinit var secretWord: TextView
    private lateinit var image: ImageView
    private lateinit var score: TextView
    private lateinit var dbLocal: DatabaseHelper
    private lateinit var listMots: ArrayList<Mots>
    private val charArrayManager = BufferChar()
    private val gameMots = mutableListOf<String>()
    private val handler = Handler(Looper.getMainLooper())
    private val updateInterval: Long = 1000L
    private val gameTimer = GameTimer()
    private val updateTask = object : Runnable {
        override fun run() {
            timer.text = gameTimer.getFormattedTime()
            handler.postDelayed(this, updateInterval)
        }
    }

    // Variables pour le jeu
    private lateinit var jeu: Jeu
    private var motCache: StringBuilder = StringBuilder()
    private lateinit var motClaire: StringBuilder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialisation des vues
        timer = findViewById(R.id.score_value)
        image = findViewById(R.id.imagePlay)
        secretWord = findViewById(R.id.secretWord)
        score = findViewById(R.id.textErr)

        // Initialisation de la base de données
        dbLocal = DatabaseHelper(this)
        listMots = dbLocal.afficherMotsForGame()

        // Remplissage de gameMots avec les mots français de listMots
        for (mot in listMots) {
            gameMots.add(mot.motFrancais)
        }

        // Initialisation du jeu
        jeu = Jeu(gameMots.toTypedArray())
        initialiserMotCache()

        // Démarrage du timer
        gameTimer.startTimer()
    }

    private fun initialiserMotCache() {
        motCache = StringBuilder(jeu.motÀDeviner.map { '#' }.joinToString(""))
        motClaire = StringBuilder(jeu.motÀDeviner)
        secretWord.text = motCache.toString()
    }

    private fun isFind(char: Char) {
        val charLower = char.lowercaseChar()
        for (i in motClaire.indices) {
            if (motClaire[i].lowercaseChar() == charLower) {
                motCache[i] = motClaire[i]
            }
        }
        secretWord.text = motCache.toString()
    }

    private fun switchImage(nombreErreur: Int) {
        val imageResource = when (nombreErreur) {
            0 -> R.drawable.acceuil
            1 -> R.drawable.err01
            2 -> R.drawable.err02
            3 -> R.drawable.err03
            4 -> R.drawable.err04
            5 -> R.drawable.err05
            6 -> R.drawable.err06
            else -> R.drawable.acceuil
        }
        image.setImageResource(imageResource)
    }

    private fun jouer(char: Char) {
        val messageLost = getString(R.string.textview_messageDéfaite) + jeu.motÀDeviner
        val messageWin = getString(R.string.textview_messageVictoire)
        var tempDeJeux = "00.00.00"
        charArrayManager.addChar(char)
        isFind(char)
        jeu.essayerUneLettre(char.lowercaseChar())
        switchImage(jeu.getNbErreur())
        score.text = "Erreur : ${jeu.getNbErreur()}"
        if (jeu.estReussie()){
            gameTimer.stopTimer()
            tempDeJeux = gameTimer.getFormattedTime()
            val intent = Intent(this, ResultView::class.java)
            intent.putExtra("message", messageWin)
            intent.putExtra("tempsDeJeux",tempDeJeux)
            intent.putExtra("motSecret", jeu.motÀDeviner)
            intent.putExtra("nbErr",jeu.getNbErreur())
            Toast.makeText(this,messageWin, Toast.LENGTH_LONG).show()
            startActivity(intent)
        }

        if(jeu.getNbErreur() == 6){
            val intent = Intent(this, ResultView::class.java)
            intent.putExtra("message", messageLost)
            intent.putExtra("tempsDeJeux",tempDeJeux)
            intent.putExtra("motSecret", jeu.motÀDeviner)
            intent.putExtra("nbErr",jeu.getNbErreur())
            Toast.makeText(this,messageLost, Toast.LENGTH_LONG).show()
            startActivity(intent)
        }
    }

    // Méthodes de cycle de vie
    override fun onResume() {
        super.onResume()
        handler.post(updateTask)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(updateTask)
    }

    override fun onDestroy() {
        super.onDestroy()
        gameTimer.stopTimer()
    }


    fun showA(view: android.view.View){
        if(view is Button){
            val b:Button = view
            b.isEnabled = false
            b.setBackgroundColor(R.color.black.dec())
            jouer('A')
        }
    }

    fun showB(view: android.view.View){
        if(view is Button){
            val b:Button = view
            b.isEnabled = false
            b.setBackgroundColor(R.color.black.dec())
            jouer('B')
        }
    }

    fun showC(view: android.view.View){
        if(view is Button){
            val b:Button = view
            b.isEnabled = false
            b.setBackgroundColor(R.color.black.dec())
            jouer('C')
        }
    }

    fun showD(view: android.view.View){
        if(view is Button){
            val b:Button = view
            b.isEnabled = false
            b.setBackgroundColor(R.color.black.dec())
            jouer('D')
        }
    }

    fun showE(view: android.view.View){
        if(view is Button){
            val b:Button = view
            b.isEnabled = false
            b.setBackgroundColor(R.color.black.dec())
            jouer('E')
        }
    }

    fun showF(view: android.view.View){
        if(view is Button){
            val b:Button = view
            b.isEnabled = false
            b.setBackgroundColor(R.color.black.dec())
            jouer('F')
        }
    }

    fun showG(view: android.view.View){
        if(view is Button){
            val b:Button = view
            b.isEnabled = false
            b.setBackgroundColor(R.color.black.dec())
            jouer('G')
        }
    }

    fun showH(view: android.view.View){
        if(view is Button){
            val b:Button = view
            b.isEnabled = false
            b.setBackgroundColor(R.color.black.dec())
            jouer('H')
        }
    }

    fun showI(view: android.view.View){
        if(view is Button){
            val b:Button = view
            b.isEnabled = false
            b.setBackgroundColor(R.color.black.dec())
            jouer('I')
        }
    }

    fun showJ(view: android.view.View){
        if(view is Button){
            val b:Button = view
            b.isEnabled = false
            b.setBackgroundColor(R.color.black.dec())
            jouer('J')
        }
    }

    fun showK(view: android.view.View){
        if(view is Button){
            val b:Button = view
            b.isEnabled = false
            b.setBackgroundColor(R.color.black.dec())
            jouer('K')
        }
    }

    fun showL(view: android.view.View){
        if(view is Button){
            val b:Button = view
            b.isEnabled = false
            b.setBackgroundColor(R.color.black.dec())
            jouer('L')
        }
    }

    fun showM(view: android.view.View){
        if(view is Button){
            val b:Button = view
            b.isEnabled = false
            b.setBackgroundColor(R.color.black.dec())
            jouer('M')
        }
    }

    fun showN(view: android.view.View){
        if(view is Button){
            val b:Button = view
            b.isEnabled = false
            b.setBackgroundColor(R.color.black.dec())
            jouer('N')
        }
    }

    fun showO(view: android.view.View){
        if(view is Button){
            val b:Button = view
            b.isEnabled = false
            b.setBackgroundColor(R.color.black.dec())
            jouer('O')
        }
    }

    fun showP(view: android.view.View){
        if(view is Button){
            val b:Button = view
            b.isEnabled = false
            b.setBackgroundColor(R.color.black.dec())
            jouer('P')
        }
    }

    fun showQ(view: android.view.View){
        if(view is Button){
            val b:Button = view
            b.isEnabled = false
            b.setBackgroundColor(R.color.black.dec())
            jouer('Q')
        }
    }

    fun showR(view: android.view.View){
        if(view is Button){
            val b:Button = view
            b.isEnabled = false
            b.setBackgroundColor(R.color.black.dec())
            jouer('R')
        }
    }

    fun showS(view: android.view.View){
        if(view is Button){
            val b:Button = view
            b.isEnabled = false
            b.setBackgroundColor(R.color.black.dec())
            jouer('S')
        }
    }

    fun showT(view: android.view.View){
        if(view is Button){
            val b:Button = view
            b.isEnabled = false
            b.setBackgroundColor(R.color.black.dec())
            jouer('T')
        }
    }

    fun showU(view: android.view.View){
        if(view is Button){
            val b:Button = view
            b.isEnabled = false
            b.setBackgroundColor(R.color.black.dec())
            jouer('U')
        }
    }

    fun showV(view: android.view.View){
        if(view is Button){
            val b:Button = view
            b.isEnabled = false
            b.setBackgroundColor(R.color.black.dec())
            jouer('V')
        }
    }

    fun showW(view: android.view.View){
        if(view is Button){
            val b:Button = view
            b.isEnabled = false
            b.setBackgroundColor(R.color.black.dec())
            jouer('W')
        }
    }

    fun showX(view: android.view.View){
        if(view is Button){
            val b:Button = view
            b.isEnabled = false
            b.setBackgroundColor(R.color.black.dec())
            jouer('X')
        }
    }

    fun showY(view: android.view.View){
        if(view is Button){
            val b:Button = view
            b.isEnabled = false
            b.setBackgroundColor(R.color.black.dec())
            jouer('Y')
        }
    }

    fun showZ(view: android.view.View){
        if(view is Button){
            val b:Button = view
            b.isEnabled = false
            b.setBackgroundColor(R.color.black.dec())
            jouer('Z')
        }
    }
}
