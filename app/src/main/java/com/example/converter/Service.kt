package com.example.converter

import androidx.lifecycle.MutableLiveData

class Service() {

    fun numClick(value: MutableLiveData<String>, num: String) {
        value.value += num
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

    fun convert(value: Double, list: Int, item: String, convertedItem: String): Double {
        return value * when(list) {
            R.array.length -> {
                lengthConvert(item, convertedItem)
            }
            R.array.weight -> {
                weightConvert(item, convertedItem)
            }
            else -> {
                volumeConvert(item, convertedItem)
            }
        }
    }

    private fun lengthConvert(item1: String, item2: String): Double {
        return when(item1) {
            "Meter" -> when(item2) {
                "Meter" -> 1.0
                "Inch" -> 39.3700787
                "Foot" -> 3.2808399
                "Yard" -> 1.0936133
                else -> 0.000621371192
            }
            "Inch" -> when(item2) {
                "Meter" -> 0.0254
                "Inch" -> 1.0
                "Foot" -> 0.08333333
                "Yard" -> 0.02777778
                else -> 0.0000157828
            }
            "Foot" -> when(item2) {
                "Meter" -> 0.3048
                "Inch" -> 12.0
                "Foot" -> 1.0
                "Yard" -> 0.33333333
                else -> 0.0001893939
                }
            "Yard" -> when(item2) {
                "Meter" -> 0.9144
                "Inch" -> 36.0
                "Foot" -> 3.0
                "Yard" -> 1.0
                else -> 0.000568181818
            }
            else -> when(item2) {
                "Meter" -> 1609.344
                "Inch" -> 63360.0
                "Foot" -> 5280.0
                "Yard" -> 1760.0
                else -> 1.0
            }
        }
    }

    private fun weightConvert(item1: String, item2: String): Double {
        return when(item1) {
            "Kilogram" -> when(item2) {
                "Kilogram" -> 1.0
                "Grain" -> 15432.3584
                "Dram" -> 564.383391
                "Ounce" -> 35.2739619
                else -> 2.20462262
            }
            "Grain" -> when(item2) {
                "Kilogram" -> 0.00006479891
                "Grain" -> 1.0
                "Dram" -> 0.0365714286
                "Ounce" -> 0.00228571429
                else -> 0.000142857143
            }
            "Dram" -> when(item2) {
                "Kilogram" -> 0.0017718452
                "Grain" -> 27.34375
                "Dram" -> 1.0
                "Ounce" -> 0.0625
                else -> 0.00390625
            }
            "Ounce" -> when(item2) {
                "Kilogram" -> 0.0283495231
                "Grain" -> 437.5
                "Dram" -> 16.0
                "Ounce" -> 1.0
                else -> 0.0625
            }
            else -> when(item2) {
                "Kilogram" -> 0.45359237
                "Grain" -> 7000.0
                "Dram" -> 256.0
                "Ounce" -> 16.0
                else -> 1.0
            }
        }
    }

    private fun volumeConvert(item1: String, item2: String): Double {
        return when(item1) {
            "Liter" -> when(item2) {
                "Liter" -> 1.0
                "Cup" -> 4.22675
                "Pint" -> 2.11338
                "Quart" -> 1.05669
                "Gallon" -> 0.264172
                else -> 0.00852168
            }
            "Cup" -> when(item2) {
                "Liter" -> 0.236588
                "Cup" -> 1.0
                "Pint" -> 0.5
                "Quart" -> 0.25
                "Gallon" -> 0.0625
                else -> 0.00201613
            }
            "Pint" -> when(item2) {
                "Liter" -> 0.473176
                "Cup" -> 2.0
                "Pint" -> 1.0
                "Quart" -> 0.5
                "Gallon" -> 0.125
                else -> 0.00403226
            }
            "Quart" -> when(item2) {
                "Liter" -> 0.946353
                "Cup" -> 4.0
                "Pint" -> 2.0
                "Quart" -> 1.0
                "Gallon" -> 0.25
                else -> 0.00806452
            }
            "Gallon" -> when(item2) {
                "Liter" -> 3.78541
                "Cup" -> 16.0
                "Pint" -> 8.0
                "Quart" -> 4.0
                "Gallon" -> 1.0
                else -> 0.0322581
            }
            else -> when(item2) {
                "Liter" -> 117.348
                "Cup" -> 496.0
                "Pint" -> 248.0
                "Quart" -> 124.0
                "Gallon" -> 31.0
                else -> 1.0
            }
        }
    }
}
