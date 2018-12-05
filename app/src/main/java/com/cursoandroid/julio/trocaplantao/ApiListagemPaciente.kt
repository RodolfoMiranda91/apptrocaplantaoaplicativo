package com.cursoandroid.julio.trocaplantao

import io.reactivex.Observable
import retrofit2.http.GET

interface ApiListagemPaciente {
    @GET ("RodolfoMiranda91/apptrocaplantaobd/blob/master/Banco%20de%20Dados/patota_apps.json")
    fun getListagemPaciente() : Observable<ListagemResponse>
}