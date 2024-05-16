package com.handy.projetfinalenativelependu

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HistoriqueAdapter(
    ctx : Activity,
    private val data: ArrayList<Histirique>,
    private var checkBoxState: BooleanArray
) : RecyclerView.Adapter<HistoriqueAdapter.HistoriqueContent>() {

    inner class HistoriqueContent(itemView: View) : RecyclerView.ViewHolder(itemView) {
         var motJouer: TextView
         var difficulte: TextView
         var tempsDeJeux: TextView
         var tentative: TextView
         var resultat: TextView

        init {
            motJouer = itemView.findViewById(R.id.txt_motJouer)
            difficulte = itemView.findViewById(R.id.txt_difficulte)
            tempsDeJeux = itemView.findViewById(R.id.txt_tempsDeJeux)
            tentative = itemView.findViewById(R.id.txt_tentative)
            resultat = itemView.findViewById(R.id.txt_resultat)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoriqueContent {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: HistoriqueContent, position: Int) {
        TODO("Not yet implemented")
    }


}
