package com.gomezrondon.utils

import processing.core.PApplet

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