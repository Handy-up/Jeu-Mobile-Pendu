package com.handy.projetfinalenativelependu

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HistoriqueAdapter(
    private val data: ArrayList<Historique>,
) : RecyclerView.Adapter<HistoriqueAdapter.HistoriqueContent>() {

    inner class HistoriqueContent(itemView: View) : RecyclerView.ViewHolder(itemView) {
         var motJouer: TextView
         var difficulte: TextView
         var tempsDeJeux: TextView
         var resultat: TextView

        init {
             motJouer = itemView.findViewById(R.id.txt_motJouer)
             difficulte = itemView.findViewById(R.id.txt_difficulte)
             tempsDeJeux = itemView.findViewById(R.id.txt_tempsDeJeux)
             resultat = itemView.findViewById(R.id.txt_resultat)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoriqueContent {
        val itemView : View  = LayoutInflater.from(parent.context).inflate(R.layout.activity_historique_item, parent, false)
        return HistoriqueContent(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: HistoriqueContent, position: Int) {
        val historique = data[position]
        holder.motJouer.text = historique.motJouer
        holder.difficulte.text = historique.difficulte
        holder.tempsDeJeux.text = historique.tempsDeJeu
        holder.resultat.text = historique.resultatParti
    }
}
