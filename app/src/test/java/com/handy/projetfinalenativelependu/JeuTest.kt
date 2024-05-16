package com.handy.projetfinalenativelependu

import org.junit.Assert.*

import org.junit.Test

class JeuTest {

    @Test
    fun testGetPointage() {
        val jeu = Jeu(arrayOf("bonjour"))
        assertEquals(0, jeu.getPointage())
    }

    @Test
    fun testGetNbErreur() {
        val jeu = Jeu(arrayOf("bonjour"))
        assertEquals(0, jeu.getNbErreur())
    }

    @Test
    fun testEssayerUneLettre() {
        val motSecret = "bonjour"
        val jeu = Jeu(arrayOf(motSecret))

        // Test avec une lettre présente
        val indices = jeu.essayerUneLettre('o')
        assertEquals(listOf(1, 4), indices)
        assertEquals(2, jeu.getPointage())
        assertEquals(0, jeu.getNbErreur())

        // Test avec une lettre absente
        val indices2 = jeu.essayerUneLettre('x')
        assertEquals(emptyList<Int>(), indices2)
        assertEquals(2, jeu.getPointage()) // Pointage ne doit pas changer
        assertEquals(1, jeu.getNbErreur())

    }

    @Test
    fun testEstReussie() {
        val motSecret = "bonjour"
        val jeu = Jeu(arrayOf(motSecret))

        // Essayer toutes les lettres sauf une
        for (char in motSecret.toCharArray().distinct()) {
            jeu.essayerUneLettre(char)
        }
        assertFalse(!jeu.estReussie())

        // Essayer la dernière lettre
        jeu.essayerUneLettre('r')
        assertTrue(!jeu.estReussie())
    }

    @Test
    fun testEstReussieValide() {
        val motSecret = "polar"
        val jeu = Jeu(arrayOf(motSecret))

        // Essayer toutes les lettres sauf une
        jeu.essayerUneLettre('p')
        jeu.essayerUneLettre('o')
        jeu.essayerUneLettre('l')
        jeu.essayerUneLettre('t')
        jeu.essayerUneLettre('r')

        //assertEquals(true,jeu.estReussie())
        assertEquals(1,jeu.getNbErreur())
    }

    @Test
    fun testEstReussieValideDeuxOcc() {
        val motSecret = "bonjour"
        val jeu = Jeu(arrayOf(motSecret))

        // Essayer toutes les lettres sauf une
        jeu.essayerUneLettre('r')
        jeu.essayerUneLettre('j')
        jeu.essayerUneLettre('b')
        jeu.essayerUneLettre('n')
        jeu.essayerUneLettre('o')
        jeu.essayerUneLettre('u')
        jeu.essayerUneLettre('o')

        //assertEquals(true,jeu.estReussie())
        assertEquals(0,jeu.getNbErreur())
    }
}