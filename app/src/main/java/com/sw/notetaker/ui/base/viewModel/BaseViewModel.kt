package com.sw.notetaker.ui.base.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sw.notetaker.data.model.Type

abstract class BaseViewModel: ViewModel() {
    protected val _title = MutableLiveData<String>()
    val title: MutableLiveData<String> = _title
    protected val _desc = MutableLiveData<String>()
    val desc: MutableLiveData<String> = _desc
    protected val _error = MutableLiveData<String>("")
    val error: MutableLiveData<String> = _error
    protected val _finish = MutableLiveData<Boolean>(false)
    val finished: MutableLiveData<Boolean> = _finish
    protected val _type = MutableLiveData<Type>()
    val type: MutableLiveData<Type> = _type

    open fun onCreate() {}
}