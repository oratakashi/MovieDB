package com.oratakashi.oratamovie

import org.junit.Test


class LeftRotationTest {
    private fun rotation(arr: List<Int>, rotation: Int) : List<Int>{
        val tmpArr : MutableList<Int> = ArrayList()
        return tmpArr.apply {
            addAll(arr.subList(rotation, arr.size))
            addAll(arr.subList(0, rotation))
        }
    }

    @Test
    fun testLeftRotation(){
        println(rotation(listOf(1,2,3,4,5,6,7), 5))
    }
}