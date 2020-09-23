package br.com.wwwgomes.notes.ui.activity

import android.os.Bundle
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import br.com.wwwgomes.notes.R
import br.com.wwwgomes.notes.model.Note
import br.com.wwwgomes.notes.retrofit.client.NoteWebClient
import br.com.wwwgomes.notes.ui.adapter.NoteListAdapter
import br.com.wwwgomes.notes.ui.dialog.NoteDialog
import kotlinx.android.synthetic.main.activity_note_list.*

class NoteListActivity : AppCompatActivity() {

    private val notes: MutableList<Note> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)

        NoteWebClient().list({
            notes.addAll(it)
            configureList()
        }, {
            Toast.makeText(this, "Falha ao buscar notas", Toast.LENGTH_LONG).show()
        })

        fab_add_note.setOnClickListener {
            NoteDialog(window.decorView as ViewGroup, this)
                    .add {
                        notes.add(it)
                        configureList()
                    }
        }
    }

    private fun configureList() {
        val recyclerView = note_list_recyclerview
        recyclerView.adapter = NoteListAdapter(notes, this) { note, position ->
            NoteDialog(window.decorView as ViewGroup, this).alter(note) {
                notes[position] = it
                configureList()
            }
        }
        val layoutManager = StaggeredGridLayoutManager(
                2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
    }

}
