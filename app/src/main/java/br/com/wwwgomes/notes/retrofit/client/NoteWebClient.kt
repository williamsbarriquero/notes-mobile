package br.com.wwwgomes.notes.retrofit.client

import br.com.wwwgomes.notes.model.Note
import br.com.wwwgomes.notes.retrofit.RetrofitInitializer
import br.com.wwwgomes.notes.retrofit.callback

class NoteWebClient {

    fun list(success: (notes: List<Note>) -> Unit,
             failure: (throwable: Throwable) -> Unit) {
        val call = RetrofitInitializer().noteService().list()
        call.enqueue(callback({ response ->
            response?.body()?.let {
                success(it)
            }
        }, { throwable ->
            throwable?.let {
                failure(it)
            }
        }))
    }

    fun insert(note: Note, success: (note: Note) -> Unit,
               failure: (throwable: Throwable) -> Unit) {
        val call = RetrofitInitializer().noteService().insert(note)
        call.enqueue(callback({ response ->
            response?.body()?.let {
                success(it)
            }
        }, { throwable ->
            throwable?.let {
                failure(it)
            }
        }))
    }

    fun alter(note: Note, success: (note: Note) -> Unit,
              failure: (throwable: Throwable) -> Unit) {
        val call = RetrofitInitializer().noteService().alter(note, note.id)
        call.enqueue(callback({ response ->
            response?.body()?.let {
                success(it)
            }
        }, { throwable ->
            throwable?.let {
                failure(it)
            }
        }))
    }

    fun del(note: Note, success: (note: Note) -> Unit,
            failure: (throwable: Throwable) -> Unit) {
        val call = RetrofitInitializer().noteService().del(note.id)
        call.enqueue(callback({ response ->
            response?.code() == 200
            success
        }, {
                throwable ->
            throwable?.let(failure)
        }))
    }

}