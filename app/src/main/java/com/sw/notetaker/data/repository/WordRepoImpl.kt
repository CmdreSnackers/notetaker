package com.sw.notetaker.data.repository

import com.sw.notetaker.data.model.NoteTest

class WordRepoImpl(): WordRepo {
    private var added = 0
    private val notes: MutableMap<Int,NoteTest> = mutableMapOf()


    override fun getNotes(): List<NoteTest>
    {
        return notes.values.toList()
    }

    override fun getNote(id: Int): NoteTest?
    {
        return notes[id]
    }

    override fun addNote(note: NoteTest)
    {
        added++
        notes[added] = note.copy(id = added)
    }

    override fun deleteNote(id: Int)
    {
        notes.remove(id)
    }

    override fun updateNote(note: NoteTest)
    {
        notes[note.id] = note
    }


}