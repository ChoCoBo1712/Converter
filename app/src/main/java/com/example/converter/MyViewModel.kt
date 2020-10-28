package com.example.converter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel: ViewModel() {
    var value: MutableLiveData<String> = MutableLiveData("")
    var convertedValue: MutableLiveData<String> = MutableLiveData("")
    var dot: Boolean = false

}