package com.example.converter

import androidx.lifecycle.MutableLiveData

class Service {

    fun numClick(value: MutableLiveData<String>, num: String) {
        value.value += num
    }

    fun dotClick(value: MutableLiveData<String>) {
        if (value.value == "") {
            value.value = "0."
        }
        else {
            value.value += '.'
        }
    }

    fun delClick(value: MutableLiveData<String>) {
        value.value = value.value?.dropLast(1)
    }
}