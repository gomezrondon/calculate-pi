package com.gomezrondon.utils

import processing.core.PApplet
import java.io.File

fun getCENTER(width:Int, height:Int): MyCenter {
    return MyCenter(width/2, height/2)
}

data class MyCenter(val x: Int, val y: Int)


fun distMy(x1: Int, y1: Int, x2: Int, y2: Int): Float {
    return PApplet.dist(x1.toFloat(), y1.toFloat(), x2.toFloat(), y2.toFloat())
}

fun getPixelPosi(x:Int, y:Int, w:Int): Int {
    return x + (y * w)
}

// 950 = x + (y * 50)

fun main() {
    val fileName = "C:\\temp\\test\\processing-3-kotlin\\src\\main\\kotlin\\com\\gomezrondon\\utils\\text.txt"
    File(fileName).readLines()
         //   .filter { it.contains("RED") } // 220
         //   .filter { it.contains("BLUE") } // 374
            .filter { it.contains("YELLO") } //31
            .filter { !it.contains("B%") }
            .map { it.split(":") }
            .filter { !it.isNullOrEmpty() }
            .map { it[1].trim().toInt() }
            //.min() // 125
           // .max() // 175
            .count() // 38 - 395
            .also { println(it) }
}