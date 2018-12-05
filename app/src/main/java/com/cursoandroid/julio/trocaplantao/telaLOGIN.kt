package com.cursoandroid.julio.trocaplantao

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_tela_login.*


class telaLOGIN : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_login)


             btn_entrar.setOnClickListener{
               if(usuario.text.toString() .equals("julio")
                && senha.text.toString() .equals("patota")){
                    val intent = Intent (  this,Listagem::class.java)
                    startActivity(intent)
                } else
                   "Erro"
            }

        }
    }

