package com.example.myapplication

import java.util.*

object JavaTest {
    @JvmStatic
    fun main(args: Array<String>) {
        val map: MutableMap<Int, String> = HashMap()
        for (i in 0..9) {
            map[i] = i.toString() + ""
        }
        val iterator: Iterator<Int> = map.keys.iterator()
        run {
            var i: Iterator<Int?>
            while (iterator.hasNext()) {
                val next = iterator.next()
                println(next.toString() + "---" + map[next])
            }
        }
        val map1: MutableMap<String, Int> = LinkedHashMap()
        for (i in 0..9) {
            map1[i.toString() + ""] = i
        }
        //        for (String key : map1.keySet()){
//            System.out.println(key +"---" + map.get(key));
//        }
    }
}