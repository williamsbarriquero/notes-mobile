package br.com.wwwgomes.notes.retrofit

import br.com.wwwgomes.notes.retrofit.service.NoteService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {

    private val retrofit = Retrofit.Builder()
        // alterar pelo seu ip aqui
        .baseUrl("http://192.168.43.218:3333")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun noteService(): NoteService = retrofit.create(NoteService::class.java)
}