package com.example.converter

import androidx.lifecycle.MutableLiveData

class Service {

    fun numClick(value: MutableLiveData<String>, num: String) {
        value.value = value.value + num
    }

    fun dotClick(value: MutableLiveData<String>): MutableLiveData<String> {
        return  value
    }

    fun delClick(value: MutableLiveData<String>): MutableLiveData<String> {
        return  value
    }
}