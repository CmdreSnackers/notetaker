package com.sw.notetaker.ui.home.viewModel

import com.sw.notetaker.data.model.NoteTest

interface HomeViewModel {
    fun fetchNotes()


    fun deleteNote(note: NoteTest)

}