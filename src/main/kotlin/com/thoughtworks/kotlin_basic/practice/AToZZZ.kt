package com.thoughtworks.kotlin_basic.practice

import kotlin.math.pow

class AToZZZ {
    fun convert(start: Int, count: Int): Array<String> {
        val maxStart = 26.0.pow(3) + 26.0.pow(2) + 26
        require(start > 0 && count > 0 && start <= maxStart && start + count <= maxStart){
            "Start number and count should be greater than 0"
        }

        val result = mutableListOf<String>()
        var num = start

        repeat(count){
            var dividend = num
            var label = ""

            while(dividend > 0){
                val temp = (dividend - 1) % 26
                label = ('A' + temp).toString() + label
                dividend = (dividend - 1) / 26
            }

            result.add(label)
            num++
        }

        return result.toTypedArray()
    }
}