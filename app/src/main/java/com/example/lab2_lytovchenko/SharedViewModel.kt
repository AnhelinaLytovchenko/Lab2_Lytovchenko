package com.example.lab2_lytovchenko

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    val resultText = MutableLiveData<String>()
    val shouldClear = MutableLiveData<Boolean>(false)
}
