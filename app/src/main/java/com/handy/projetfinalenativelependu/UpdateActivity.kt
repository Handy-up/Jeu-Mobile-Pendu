package com.handy.projetfinalenativelependu

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class UpdateActivity : AppCompatActivity() {
    lateinit var editmotFrancais2: EditText
    lateinit var editmotAnglais2: EditText
    lateinit var radioGroup2: RadioGroup
    lateinit var btn_update: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_update)
       /* ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainLayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/
        editmotFrancais2 =findViewById(R.id.editMotFrancais2)
        editmotAnglais2 = findViewById(R.id.editMotAnglais2)
        radioGroup2 = findViewById(R.id.radioGroup2)
        btn_update = findViewById(R.id.btn_update)


        //btn_update.setOnClickListener()
    }



}