package com.handy.projetfinalenativelependu

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class DatabaseHelperTest {

    private lateinit var dbHelper: DatabaseHelper
    private lateinit var context: Context

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        dbHelper = DatabaseHelper(context)
        dbHelper.writableDatabase // This will call onCreate or onUpgrade
    }

    @After
    fun tearDown() {
        dbHelper.close()
        context.deleteDatabase(DatabaseHelper.DATABASE_NAME)
    }

    @Test
    fun onCreate() {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            DatabaseHelper.TABLE_NAME_MOTS,
            null,
            null,
            null,
            null,
            null,
            null
        )
        assertNotNull(cursor)
        cursor.close()
    }

    @Test
    fun onUpgrade() {
        val oldVersion = dbHelper.readableDatabase.version
        dbHelper.onUpgrade(dbHelper.writableDatabase, oldVersion, oldVersion + 1)
        val newVersion = dbHelper.readableDatabase.version
        assertEquals(oldVersion + 1, newVersion)
    }

    @Test
    fun getReadableDatabaseInstance() {
        val db = dbHelper.getReadableDatabaseInstance()
        assertNotNull(db)
        db.close()
    }

    @Test
    fun insertHistorique() {
        val historique = Historique("chien", "facile", "00:05:00", 1.toString())
        dbHelper.insertHistorique(historique)

        val db = dbHelper.readableDatabase
        val cursor = db.query(
            DatabaseHelper.TABLE_NAME_HISTORIQUE,
            null,
            "${DatabaseHelper.COLUMN_HISTORIQUE_MOT} = ?",
            arrayOf("chien"),
            null,
            null,
            null
        )

        assertTrue(cursor.moveToFirst())
        assertEquals("chien", cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_HISTORIQUE_MOT)))
        cursor.close()
        db.close()
    }

    @Test
    fun getAllHistoriques() {
        val historique1 = Historique("chien", "facile", "00:05:00", 1.toString())
        val historique2 = Historique("chat", "moyen", "00:10:00", 0.toString())
        dbHelper.insertHistorique(historique1)
        dbHelper.insertHistorique(historique2)

        val historiques = dbHelper.getAllHistoriques()
        assertEquals(2, historiques.size)
        assertEquals("chien", historiques[0].motJouer)
        assertEquals("chat", historiques[1].motJouer)
    }

    @Test
    fun deleteHistorique() {
        val historique = Historique("chien", "facile", "00:05:00", 1.toString())
        dbHelper.insertHistorique(historique)

        val db = dbHelper.readableDatabase
        val cursorBeforeDelete = db.query(
            DatabaseHelper.TABLE_NAME_HISTORIQUE,
            null,
            "${DatabaseHelper.COLUMN_HISTORIQUE_MOT} = ?",
            arrayOf("chien"),
            null,
            null,
            null
        )
        assertTrue(cursorBeforeDelete.moveToFirst())
        val idParti = cursorBeforeDelete.getInt(cursorBeforeDelete.getColumnIndexOrThrow(DatabaseHelper.COLUMN_HISTORIQUE_ID))
        cursorBeforeDelete.close()

        dbHelper.deleteHistorique(idParti)

        val cursorAfterDelete = db.query(
            DatabaseHelper.TABLE_NAME_HISTORIQUE,
            null,
            "${DatabaseHelper.COLUMN_HISTORIQUE_ID} = ?",
            arrayOf(idParti.toString()),
            null,
            null,
            null
        )
        assertFalse(cursorAfterDelete.moveToFirst())
        cursorAfterDelete.close()
        db.close()
    }

    @Test
    fun deleteAllHistoriques() {
        val historique1 = Historique("chien", "facile", "00:05:00", 1.toString())
        val historique2 = Historique("chat", "moyen", "00:10:00", 0.toString())
        dbHelper.insertHistorique(historique1)
        dbHelper.insertHistorique(historique2)

        dbHelper.deleteAllHistoriques()

        val db = dbHelper.readableDatabase
        val cursor = db.query(
            DatabaseHelper.TABLE_NAME_HISTORIQUE,
            null,
            null,
            null,
            null,
            null,
            null
        )
        assertFalse(cursor.moveToFirst())
        cursor.close()
        db.close()
    }
}