package com.handy.projetfinalenativelependu

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class AddActivity : AppCompatActivity() {

    lateinit var editmotFrancais: EditText
    lateinit var editmotAnglais: EditText
    lateinit var radioGroup: RadioGroup
    lateinit var btn_ajouter: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add)

        editmotFrancais = findViewById(R.id.editMotFrancais)
        editmotAnglais = findViewById(R.id.editMotAnglais)
        radioGroup = findViewById(R.id.radioGroupSettingsLangue)
        btn_ajouter = findViewById(R.id.btn_ajouter)

        btn_ajouter.setOnClickListener {
            val DB = DatabaseHelper(this@AddActivity)
            val motFrancais = editmotFrancais.text.toString().trim()
            val motAnglais = editmotAnglais.text.toString().trim()
            val radioButtonId = radioGroup.checkedRadioButtonId
            val radioButton = findViewById<RadioButton>(radioButtonId)

            if (motFrancais.isNotEmpty() && motAnglais.isNotEmpty() && radioButton != null) {
                val difficulte = when (radioButton.id) {
                    R.id.radioFacile -> "Facile"
                    R.id.radioMoyen -> "Moyen"
                    R.id.radioDifficile -> "Difficile"
                    else -> "Facile"
                }
                DB.ajouterMot(this@AddActivity, motFrancais, motAnglais, difficulte)
                Toast.makeText(this, "Ajouté avec succès!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                    this,
                    "Veuillez remplir tous les champs et sélectionner la difficulté.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
