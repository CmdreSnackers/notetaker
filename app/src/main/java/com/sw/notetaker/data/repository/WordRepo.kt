package com.sw.notetaker.data.repository

import com.sw.notetaker.data.model.NoteTest

interface WordRepo {

    fun getNotes(): List<NoteTest>


    fun getNote(id: Int): NoteTest?


    fun addNote(note: NoteTest)


    fun deleteNote(id: Int)


    fun updateNote(note: NoteTest)

}