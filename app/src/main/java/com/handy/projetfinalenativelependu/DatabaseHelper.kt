package com.handy.projetfinalenativelependu

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.DatabaseUtils
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {

        private const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Pendu.db"
        const val TABLE_NAME_MOTS = "Mots"
        const val COLUMN_MOTS_ID = "idMot"
        const val COLUMN_MOTS_FRANCAIS = "motFrancais"
        const val COLUMN_MOTS_ANGLAIS = "motAnglais"
        const val COLUMN_MOTS_DIFFICULTE = "difficulté"

        const val TABLE_NAME_HISTORIQUE = "historique"
        const val COLUMN_HISTORIQUE_ID = "idParti"
        const val COLUMN_HISTORIQUE_MOT = "motJouer"
        const val COLUMN_HISTORIQUE_DIFFICULTE = "jeuDifficulté"
        const val COLUMN_HISTORIQUE_TEMPSJEU = "tempsDeJeu"
        const val COLUMN_HISTORIQUE_RESULTATPARTI = "resultatParti"

        // Table préférence

        const val TABLE_NAME_PREFERENCES = "Preferences"
        const val COLUMN_PREFERENCES_ID = "id"
        const val COLUMN_PREFERENCES_LANGUE = "langue"
        const val COLUMN_PREFERENCES_DIFFICULTE = "difficulte"
    }


    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_MOT =
            "CREATE TABLE $TABLE_NAME_MOTS ($COLUMN_MOTS_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_MOTS_FRANCAIS TEXT, $COLUMN_MOTS_ANGLAIS TEXT, $COLUMN_MOTS_DIFFICULTE TEXT)"
        db?.execSQL(CREATE_TABLE_MOT)

        val CREATE_TABLE_HISTORIQUE =
            "CREATE TABLE $TABLE_NAME_HISTORIQUE ($COLUMN_HISTORIQUE_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_HISTORIQUE_MOT TEXT, $COLUMN_HISTORIQUE_DIFFICULTE TEXT, $COLUMN_HISTORIQUE_RESULTATPARTI INTEGER, $COLUMN_HISTORIQUE_TEMPSJEU TEXT)"
        db?.execSQL(CREATE_TABLE_HISTORIQUE)

        val CREATE_TABLE_PREFERENCES =
            "CREATE TABLE $TABLE_NAME_PREFERENCES ($COLUMN_PREFERENCES_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_PREFERENCES_LANGUE TEXT, $COLUMN_PREFERENCES_DIFFICULTE TEXT)"
        db?.execSQL(CREATE_TABLE_PREFERENCES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val DROP_TABLE_MOT = "DROP TABLE IF EXISTS $TABLE_NAME_MOTS"
        db?.execSQL(DROP_TABLE_MOT)

        val DROP_TABLE_HISTORIQUE = "DROP TABLE IF EXISTS $TABLE_NAME_HISTORIQUE"
        db?.execSQL(DROP_TABLE_HISTORIQUE)

        val DROP_TABLE_PREFERENCES = "DROP TABLE IF EXISTS $TABLE_NAME_PREFERENCES"
        db?.execSQL(DROP_TABLE_PREFERENCES)

        onCreate(db)
    }

    fun getReadableDatabaseInstance(): SQLiteDatabase {
        return readableDatabase
    }

    fun ajouterMot(context: Context, motFrancais: String, motAnglais: String, difficulte: String) {
        val db = this.writableDatabase
        val cv = ContentValues()

        cv.put(COLUMN_MOTS_FRANCAIS, motFrancais)
        cv.put(COLUMN_MOTS_ANGLAIS, motAnglais)
        cv.put(COLUMN_MOTS_DIFFICULTE, difficulte)
        val result = db.insert(TABLE_NAME_MOTS, null, cv)
        if (result == -1L) {
            Toast.makeText(context, "Erreur : Le mot n'a pas pu être ajouté", Toast.LENGTH_SHORT)
                .show()
        } else {
            Toast.makeText(context, "Succès : Le mot a été ajouté", Toast.LENGTH_SHORT).show()
        }


        Log.d("DatabaseHelper", "Résultat des insertions: $result")
    }


    fun afficherMots(): Cursor? {
        val query = "SELECT * FROM $TABLE_NAME_MOTS"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        cursor = db.rawQuery(query, null)
        return cursor
    }

    fun afficherMotsForGame(): ArrayList<Mots> {
        val motsList: ArrayList<Mots> = arrayListOf()
        val query = "SELECT * FROM $TABLE_NAME_MOTS"
        val db = this.readableDatabase
        val cursor: Cursor? = db.rawQuery(query, null)

        cursor?.use {
            if (it.moveToFirst()) {
                do {
                    val id = it.getInt(it.getColumnIndexOrThrow("idMot"))
                    val motFrancais = it.getString(it.getColumnIndexOrThrow("motFrancais"))
                    val motAnglais = it.getString(it.getColumnIndexOrThrow("motAnglais"))
                    val niveau = it.getString(it.getColumnIndexOrThrow("difficulté"))

                    val mot = Mots(id, motFrancais, motAnglais, niveau)
                    motsList.add(mot)
                } while (it.moveToNext())
            }
        }

        return motsList
    }

    fun supprimerMotParId(context: Context, idMot: Int) {
        val db = this.writableDatabase

        val whereClause = "$COLUMN_MOTS_ID = ?"
        val whereArgs = arrayOf(idMot.toString())
        val result = db.delete(TABLE_NAME_MOTS, whereClause, whereArgs)
        if (result == -1) {
            Toast.makeText(context, "Erreur : Le mot n'a pas pu être supprimé", Toast.LENGTH_SHORT)
                .show()
        } else {
            Toast.makeText(context, "Succès : Le mot a été supprimé", Toast.LENGTH_SHORT).show()
        }
        db.close()
    }


    fun insertHistorique(historique: Historique) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_HISTORIQUE_MOT, historique.motJouer)
            put(COLUMN_HISTORIQUE_DIFFICULTE, historique.difficulte)
            put(COLUMN_HISTORIQUE_TEMPSJEU, historique.tempsDeJeu)
            put(COLUMN_HISTORIQUE_RESULTATPARTI, historique.resultatParti)
        }
        db.insert(TABLE_NAME_HISTORIQUE, null, values)
        db.close()
    }

    fun getAllHistoriques(): List<Historique> {
        val historiqueList = mutableListOf<Historique>()
        val db = readableDatabase
        val selectQuery = "SELECT * FROM $TABLE_NAME_HISTORIQUE"
        val cursor: Cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {
            do {
                val motJouer = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_HISTORIQUE_MOT))
                val difficulte =
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_HISTORIQUE_DIFFICULTE))
                val tempsDeJeu =
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_HISTORIQUE_TEMPSJEU))
                val resultatParti =
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_HISTORIQUE_RESULTATPARTI))
                val historique = Historique(motJouer, difficulte, tempsDeJeu, resultatParti)
                historiqueList.add(historique)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return historiqueList
    }

    fun deleteHistorique(idParti: Int) {
        val db = writableDatabase
        db.delete(TABLE_NAME_HISTORIQUE, "$COLUMN_HISTORIQUE_ID=?", arrayOf(idParti.toString()))
        db.close()
    }

    fun deleteAllHistoriques() {
        val db = writableDatabase
        db.delete(TABLE_NAME_HISTORIQUE, null, null)
        db.close()
    }

    fun insertOrUpdatePreferences(preferences: Preferences) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_PREFERENCES_DIFFICULTE, preferences.difficultePreferences)
            put(COLUMN_PREFERENCES_LANGUE, preferences.languePreferences)
        }

        // Vérifier si des préférences existent déjà
        //val count = DatabaseUtils.queryNumEntries(db, TABLE_NAME_PREFERENCES)
        val cout = getPreferences()
        if (cout == null) {
            // Si aucune entrée n'existe, insérer les préférences
            db.insert(TABLE_NAME_PREFERENCES, null, values)
        } else {
            // Sinon, mettre à jour les préférences existantes
            db.update(TABLE_NAME_PREFERENCES, values, null, null)
        }

        db.close()
    }

    fun getPreferences(): Preferences? {
        val db = readableDatabase
        var preferences: Preferences? = null

        val query = "SELECT * FROM $TABLE_NAME_PREFERENCES"
        val cursor = db.rawQuery(query, null)

        cursor?.use {
            if (it.moveToFirst()) {
                val difficulte =
                    it.getString(it.getColumnIndexOrThrow(COLUMN_PREFERENCES_DIFFICULTE))
                val langue = it.getString(it.getColumnIndexOrThrow(COLUMN_PREFERENCES_LANGUE))
                preferences = Preferences(difficulte, langue)
            }
        }

        cursor.close()
        db.close()

        return preferences
    }
}
