package com.sw.notetaker

import android.app.Application
import com.sw.notetaker.data.repository.WordRepoImpl

class MyApplication: Application() {
    val repos = WordRepoImpl()
}