package com.sw.notetaker.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.sw.notetaker.data.model.NoteTest
import com.sw.notetaker.databinding.LayoutWordBinding


class WordAdapter(
    private var notes:List<NoteTest>,
    private val onNoteC: (NoteTest) -> Unit,
    private val onDeleteC: (NoteTest) -> Unit
):RecyclerView.Adapter<RecyclerView.ViewHolder>()

{

    private lateinit var binding: LayoutWordBinding



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
    {
        binding = LayoutWordBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return WordViewHolder(binding)
    }

    override fun getItemCount(): Int
    {
        return notes.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int)
    {
        val note = notes[position]
        if (holder is WordViewHolder)
        {
            holder.binding.run {
                tvTitle.text = note.title
                tvDesc.text = note.desc

                ivDelete.setOnClickListener {
                    onDeleteC(note)
                }

                llTask.setOnClickListener {
                    onNoteC(note)
                }
                Log.d("color", "${note.type.colour}")
                cvTask.setCardBackgroundColor(
                    ContextCompat.getColor(
                        root.context,
                        note.type.colour
                    )
                )
            }

        }
    }

    fun setNewNote(items: List<NoteTest>) {
        this.notes = notes
        notifyDataSetChanged()
    }

    class WordViewHolder(val binding: LayoutWordBinding): RecyclerView.ViewHolder(binding.root)


}