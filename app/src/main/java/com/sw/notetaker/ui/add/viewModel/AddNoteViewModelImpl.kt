package com.sw.notetaker.ui.add.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.sw.notetaker.MyApplication
import com.sw.notetaker.data.model.NoteTest
import com.sw.notetaker.data.model.Type
import com.sw.notetaker.data.repository.WordRepoImpl
import com.sw.notetaker.ui.base.viewModel.BaseViewModel

class AddNoteViewModelImpl(
    private val repo: WordRepoImpl
) : BaseViewModel(), AddNoteViewModel
{


    override fun addNote()
    {
        if (title.value != null && desc.value != null && type.value != null)
        {
            repo.addNote(
                NoteTest(title = title.value!!, desc = desc.value!!, type = type.value!!)
            )
            finished.value = true
        } else if (title.value == null || desc.value == null)
        {
            error.value = "Title and Desc are null"
        } else
        {
            error.value = "Select a Colour"
        }
    }

    override fun redClick()
    {
        type.value = Type.Red
    }
    override fun yellowClick()
    {
        type.value = Type.Yellow
    }
    override fun greenClick()
    {
        type.value = Type.Green
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val myRepository =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MyApplication).repos
                AddNoteViewModelImpl(
                    myRepository,
                )
            }
        }
    }

}