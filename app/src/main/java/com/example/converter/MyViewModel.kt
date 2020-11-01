package com.example.converter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.json.JSONObject

class MyViewModel: ViewModel() {
    var value: MutableLiveData<String> = MutableLiveData("")
    var convertedValue: MutableLiveData<String> = MutableLiveData("")
    var dot: Boolean = false
    var list: MutableLiveData<Int> = MutableLiveData(R.array.length)
    var item: MutableLiveData<String> = MutableLiveData("")
    var convertedItem: MutableLiveData<String> = MutableLiveData("")

}