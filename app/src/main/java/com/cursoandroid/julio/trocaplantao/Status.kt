package com.cursoandroid.julio.trocaplantao

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_listagem.*
import kotlinx.android.synthetic.main.activity_status.*

class Status : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_status)

        btn_salvar.setOnClickListener{

            val intent = Intent (  this,Listagem::class.java)
            startActivity(intent)
        }

        btn_voltar.setOnClickListener{

            val intent = Intent (  this,Listagem::class.java)
            startActivity(intent)

        }
    }
}
