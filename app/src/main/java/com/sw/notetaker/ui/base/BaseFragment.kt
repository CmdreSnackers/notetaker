package com.sw.notetaker.ui.base

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.sw.notetaker.ui.base.viewModel.BaseViewModel

abstract class BaseFragment: Fragment() {

    protected lateinit var navController: NavController
}