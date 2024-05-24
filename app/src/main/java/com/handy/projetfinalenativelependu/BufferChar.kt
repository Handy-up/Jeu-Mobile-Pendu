package com.handy.projetfinalenativelependu

class BufferChar {
    private val charArray = CharArray(5)

    fun addChar(char: Char): Boolean {
        for (i in charArray.indices) {
            if (charArray[i] == '\u0000') { // '\u0000' représente un caractère nul dans Kotlin
                charArray[i] = char
                return true // Caractère ajouté avec succès
            }
        }
        return false // Le tableau est déjà plein
    }

    fun clearArray() {
        charArray.fill('\u0000') // Remplit le tableau avec des caractères nuls
    }

    fun getArray(): CharArray {
        return charArray
    }

    fun isFull(): Boolean {
        return charArray.all { it != '\u0000' }
    }
}