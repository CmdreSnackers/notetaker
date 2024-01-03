package com.sw.notetaker.ui.note.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.sw.notetaker.MyApplication
import com.sw.notetaker.data.model.NoteTest
import com.sw.notetaker.data.repository.WordRepoImpl

class NoteViewModelImpl(
    private val repo: WordRepoImpl
) : ViewModel()

{
    val note: MutableLiveData<NoteTest> = MutableLiveData()

    private var id: Int = -1

    fun init(id: Int)
    {
        this.id = id
        val res = repo.getNote(id)
        res?.let {
            note.value = it
        }

    }

    companion object
    {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val myRepository =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MyApplication).repos
                NoteViewModelImpl(
                    myRepository,
                )
            }
        }
    }

}