package com.example.converter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel: ViewModel() {
    var value: MutableLiveData<String> = MutableLiveData("0")
    var convertedValue: MutableLiveData<String> = MutableLiveData("0")
    var dot: Boolean = false
    var list: MutableLiveData<Int> = MutableLiveData(R.array.length)
    var item: MutableLiveData<String> = MutableLiveData("")
    var convertedItem: MutableLiveData<String> = MutableLiveData("")

}