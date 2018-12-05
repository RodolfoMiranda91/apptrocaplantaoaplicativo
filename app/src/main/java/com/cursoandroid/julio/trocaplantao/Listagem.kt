package com.cursoandroid.julio.trocaplantao

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.view.menu.ActionMenuItemView
import android.support.v7.view.menu.ListMenuItemView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_listagem.*
import kotlinx.android.synthetic.main.activity_tela_login.*
import kotlinx.android.synthetic.main.item_listagem.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class Listagem : AppCompatActivity() {


    private lateinit var pacienteAdapter : PacientesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listagem)

        btn_status.setOnClickListener(){
            val intent = Intent (  this,Status::class.java)
            startActivity(intent)
        }

        PacientesAdapter()
        paciente_lista.adapter = PacientesAdapter()

        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("https://github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        val apiListagensPacientes = retrofit.create(ApiListagemPaciente::class.java)
        apiListagensPacientes.getListagemPaciente()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    pacienteAdapter.setPacientes(it.patota_apps!!)}, // o que tem q por no lugar do data??? patota_apps tbm da erro.. VERIFICAR
                        //adicionei o !! que significa q pode ser null... VERIFICAR se pode
                        {
                            Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT)
                        })
    }

    inner class PacientesAdapter : RecyclerView.Adapter<PacientesAdapter.PacienteViewHolder>() {

        private val pacientes : List<Pacientes> = mutableListOf()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PacienteViewHolder {
            return PacienteViewHolder(layoutInflater.inflate(R.layout.item_listagem, parent, false))
        }

        override fun getItemCount(): Int {
            return pacientes.size
        }

        override fun onBindViewHolder(holder: PacienteViewHolder, position: Int) {
            holder.binModel(pacientes[position])
        }

        fun setPacientes(data: List<Pacientes>){
            pacientes.addAll(data)// pq não funciona o addAll??? VERIFICAR
            notifyDataSetChanged()
        }


        inner class PacienteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

            val paciente_nomeTxt : TextView = itemView.findViewById(R.id.paciente_nome)


            fun binModel(pacientes: Pacientes){
                paciente_nomeTxt.text = pacientes.nome
            }
        }
    }
}

private fun <E> List<E>.addAll(data: Collection<E>) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}
