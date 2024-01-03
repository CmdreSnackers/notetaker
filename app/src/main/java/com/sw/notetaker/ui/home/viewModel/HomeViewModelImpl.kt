package com.sw.notetaker.ui.home.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.sw.notetaker.MyApplication
import com.sw.notetaker.data.model.NoteTest
import com.sw.notetaker.data.repository.WordRepoImpl
import com.sw.notetaker.ui.base.viewModel.BaseViewModel

class HomeViewModelImpl(
    private val repo: WordRepoImpl
): BaseViewModel(), HomeViewModel

{
    val notes: MutableLiveData<List<NoteTest>> = MutableLiveData()
    val empty: MutableLiveData<Boolean> = MutableLiveData(false)

    init
    {
        fetchNotes()
    }


    override fun fetchNotes()
    {
        val res = repo.getNotes()
        notes.value = res
        empty.value = res.isEmpty()
    }

    override fun deleteNote(note: NoteTest)
    {
        repo.deleteNote(note.id)
        fetchNotes()
    }

    companion object
    {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val myRepository = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MyApplication).repos
                HomeViewModelImpl(
                    myRepository,
                )
            }
        }
    }

}