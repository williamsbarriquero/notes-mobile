package br.com.wwwgomes.notes.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.wwwgomes.notes.R
import br.com.wwwgomes.notes.model.Note
import kotlinx.android.synthetic.main.note_item.view.*


class NoteListAdapter(
    private val notes: MutableList<Note>,
    private val context: Context,
    private var onItemClickListener: (note: Note, position: Int) -> Unit) : RecyclerView.Adapter<NoteListAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = notes[position]
        holder?.let {
            it.bindView(note)
            it.itemView.setOnClickListener {
                onItemClickListener(note, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.note_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(note: Note) {
            val title = itemView.note_item_title
            val description = itemView.note_item_description

            title.text = note.title
            description.text = note.description
        }

    }

}
