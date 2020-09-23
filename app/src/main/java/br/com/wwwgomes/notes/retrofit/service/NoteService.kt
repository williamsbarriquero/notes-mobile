package br.com.wwwgomes.notes.retrofit.service

import br.com.wwwgomes.notes.model.Note
import retrofit2.Call
import retrofit2.http.*

interface NoteService {

    @GET("notes")
    fun list(): Call<List<Note>>

    @POST("notes")
    fun insert(@Body note: Note): Call<Note>

    @PUT("notes/{id}")
    fun alter(@Body note: Note, @Path("id") id: String): Call<Note>

    @DELETE("notes/{id}")
    fun del(@Path("id") id: String): Call<Note>

}