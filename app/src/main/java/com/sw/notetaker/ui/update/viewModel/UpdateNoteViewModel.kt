package com.sw.notetaker.ui.update.viewModel

import com.sw.notetaker.data.model.NoteTest
import com.sw.notetaker.data.model.Type

interface UpdateNoteViewModel {
    fun init(id: Int)


    fun updateNote()


    fun redClick()

    fun yellowClick()

    fun greenClick()


    fun deleteNote()
}