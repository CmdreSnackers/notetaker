package com.sw.notetaker.ui.update.viewModel

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


class UpdateNoteViewModelImpl(

    private val repo: WordRepoImpl

): BaseViewModel(), UpdateNoteViewModel
{

    private var id: Int = -1

    override fun init(id: Int)
    {
        this.id = id
        val res = repo.getNote(id)
        res?.let {
            title.value = it.title
            desc.value = it.desc
        }
    }

    override fun updateNote()

    {
        if(title.value != null && desc.value != null && type.value != null)
        {
            repo.updateNote(
                NoteTest(id = id, title = title.value!!, desc = desc.value!!, type = type.value!!)
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

    override fun deleteNote() {
        repo.deleteNote(id)
        finished.value = true
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val myRepository =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MyApplication).repos
                UpdateNoteViewModelImpl(
                    myRepository,
                )
            }
        }
    }
}



