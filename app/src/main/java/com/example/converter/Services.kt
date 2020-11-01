package com.example.converter

import androidx.lifecycle.MutableLiveData
import org.json.JSONObject

class keyboardService() {

    fun numClick(value: MutableLiveData<String>, num: String) {
        if (value.value == "0") {
            value.value = num
        }
        else {
            value.value += num
        }
    }

    fun dotClick(value: MutableLiveData<String>) {
        if (value.value == "") {
            value.value = "0."
        } else {
            value.value += '.'
        }
    }

    fun delClick(value: MutableLiveData<String>) {
        value.value = value.value?.dropLast(1)
    }
}

class JSONService() {

    fun convert(value: Double, list: Int, item: String, convertedItem: String): Double {
        lateinit var type: JSONObject

        when(list) {
            R.array.length -> {
                type = JSONObject("Length")
            }
            R.array.weight -> {
                type = JSONObject("Weight")
            }
            R.array.volume -> {
                type = JSONObject("Volume")
            }
        }

        val coeff = (type[item] as JSONObject)[convertedItem] as Double

        return value * coeff
    }
}