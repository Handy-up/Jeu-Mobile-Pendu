package com.handy.projetfinalenativelependu

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper (context : Context): SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION) {

    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "MotsDictionnaire.db"
        private const  val TABLE_NAME = "Mots"
        private const val COLUMN_ID = "id"
        private const val COLUMN_FRANCAIS = "motFrancais"
        private const val COLUMN_ANGLAIS = "motAnglais"
        private const val COLUMN_DIFFICULTÉ = "difficulté"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE = "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_FRANCAIS TEXT, $COLUMN_ANGLAIS TEXT, $COLUMN_DIFFICULTÉ TEXT)"
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(DROP_TABLE)
        onCreate(db)
    }

    fun insertMots(mots : Mots){
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_FRANCAIS,mots.motFrancais)
            put(COLUMN_ANGLAIS,mots.motAnglais)
            put(COLUMN_DIFFICULTÉ, mots.difficulte)
        }

        db.insert(TABLE_NAME,null,values)
        db.close()
    }



    fun deleteMots(mots: Mots){
        val db = writableDatabase
        val selection = "$COLUMN_ID= ?"
        val selectionArgs = arrayOf(mots.id.toString())
        db.delete(TABLE_NAME, selection, selectionArgs)
        db.close()
    }

    fun getAllMots():List<Mots>{
        val motsList = mutableListOf<Mots>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(query,null)
        while (cursor.moveToNext()){
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val motFrancais = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FRANCAIS))
            val motAnglais = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ANGLAIS))
            val difficutlé = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DIFFICULTÉ))


            val mot = Mots(id,motFrancais,motAnglais,difficutlé)
            motsList.add(mot)
        }
        cursor.close()
        db.close()
        return motsList
    }
    /*fun getMotsInFrench():List<Mots>{
        val motsListFrench = mutableListOf<Mots>()
        val db = readableDatabase
        val query = "SELECT $COLUMN_DIFFICULTÉ,$COLUMN_FRANCAIS,$COLUMN_DIFFICULTÉ FROM $TABLE_NAME"
        val cursor = db.rawQuery(query,null)
        while(cursor.moveToNext()){
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val motFrançais = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FRANCAIS))
            val difficulté = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DIFFICULTÉ))

            val mot = Mots(id,motFrançais,difficulté,null)
            motsListFrench
        }
    }*/



}