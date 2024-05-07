package com.handy.projetfinalenativelependu

import kotlin.random.Random

class Jeu ( private val jeu : Array<String>) {
    private var pointage : Int = 0
    private var nbErreur : Int = 0
    public var motÀDeviner : String = ""


    init {

        if (jeu.isEmpty()){
            throw IllegalArgumentException("La liste de mot est vide, veuillez la remplir pour jouer")
        }
        motÀDeviner = jeu[Random.nextInt(jeu.size)]
    }


    fun getPointage():Int{
        return pointage
    }
    fun getNbErreur():Int{
        return nbErreur
    }


    fun essayerUneLettre(lettre: Char): ArrayList<Int> {
        val indices = ArrayList<Int>()
        motÀDeviner.forEachIndexed { index, char ->
            if (char == lettre) {
                indices.add(index)
            }
        }
        if (indices.isEmpty()) {
            nbErreur++
        } else {
            pointage += indices.size
        }
        return indices
    }

    fun estRéussie(motTentative: String) : Boolean{
        return motTentative == motÀDeviner
    }

}




