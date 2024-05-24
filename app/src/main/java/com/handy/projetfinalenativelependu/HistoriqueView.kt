package com.handy.projetfinalenativelependu

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HistoriqueView : AppCompatActivity() {
    private lateinit var historiques: ArrayList<Historique>
    private lateinit var adapterList: RecyclerView
    private var dbLocal:DatabaseHelper = DatabaseHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_historique_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        adapterList = findViewById(R.id.id_listHistoriqueRv)
        historiques = ArrayList()

        val adapter = HistoriqueAdapter(historiques)

        adapterList.adapter = adapter

        // Initialisation du gestionnaire de disposition
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)
        adapterList.layoutManager = layoutManager
        adapterList.itemAnimator = DefaultItemAnimator()

        // Récupération des données depuis la base de données

        historiques.addAll(dbLocal.getAllHistoriques())
    }

    fun clearStory(view: android.view.View){
        dbLocal.deleteAllHistoriques()
    }
}
