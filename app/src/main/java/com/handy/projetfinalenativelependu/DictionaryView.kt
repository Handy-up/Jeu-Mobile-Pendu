package com.handy.projetfinalenativelependu

import MotsAdapter
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DictionaryView : AppCompatActivity() {

    lateinit var recyclerView : RecyclerView
    lateinit var  add_button : FloatingActionButton
    lateinit var  databaseHelper: DatabaseHelper
    lateinit var listMotFrancais: ArrayList<String>
    lateinit var listMotAnglais: ArrayList<String>
    lateinit var listdifficulte: ArrayList<String>
    lateinit var  listIdMot: ArrayList<String>
    lateinit var  motsAdapter : MotsAdapter
    lateinit var  btn_supprimer : Button
    lateinit var  idMotSuppression : EditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dictionnaire_view)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/
        recyclerView = findViewById(R.id.RecycleViewDictionary)
        add_button = findViewById(R.id.add_button)
        btn_supprimer = findViewById(R.id.btn_supprimer)
        idMotSuppression = findViewById(R.id.idMotSuppression)

        add_button.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }

        btn_supprimer.setOnClickListener {
            val idMot = idMotSuppression.text.toString().toIntOrNull()

            if (idMot != null) {
                databaseHelper.supprimerMotParId(this, idMot)
            } else {
                Toast.makeText(this, "Veuillez entrer un ID valide", Toast.LENGTH_SHORT).show()
            }
        }




        databaseHelper = DatabaseHelper(this@DictionaryView)
        listIdMot = ArrayList<String>()
        listMotFrancais = ArrayList<String>()
        listMotAnglais = ArrayList<String>()
        listdifficulte = ArrayList<String>()
        stockerLesDonnees()
        val motAdapter = MotsAdapter(this@DictionaryView, this,listIdMot , listMotFrancais, listMotAnglais, listdifficulte)
        recyclerView.adapter = motAdapter
        recyclerView.layoutManager = LinearLayoutManager(this@DictionaryView)

    }



    fun stockerLesDonnees() {
        val cursor = databaseHelper.afficherMots()
        if (cursor != null) {
            if (cursor.count == 0) {
                Toast.makeText(this, "Aucun mot", Toast.LENGTH_SHORT).show()
            } else {
                while (cursor.moveToNext()) {
                    listIdMot.add(cursor.getString(0))
                    listMotFrancais.add(cursor.getString(1))
                    listMotAnglais.add(cursor.getString(2))
                    listdifficulte.add(cursor.getString(3))
                }
                Log.d("Mots", "Id Mots : $listIdMot")
                Log.d("Mots", "Mot français : $listMotFrancais")
                Log.d("Mots", "Mot anglais : $listMotAnglais")
                Log.d("Mots", "Difficulté : $listdifficulte")
            }
        } else {
            Toast.makeText(this, "Erreur lors du chargement des données", Toast.LENGTH_SHORT).show()
        }
    }



    fun backSettings(){
        finish()
    }


}