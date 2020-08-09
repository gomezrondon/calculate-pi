package com.gomezrondon.image


import processing.core.PApplet
import processing.core.PImage
import processing.core.*

fun main() {
    PApplet.main("com.gomezrondon.image.ImageDetection")
}


class ImageDetection: PApplet() {

    lateinit var image:PImage

    override fun draw() {

    }


    // identical use to setup in Processing IDE except for size()
    override fun setup() {
        background(255)

        //1) how to create a line manually
 /*       for (x in 0..width) {
            // how to set pixel on the screen
            set(x, 200 , color(255, 0, 0))
        }*/

        //2) how to create a line manually
        loadPixels() //before doing anything with pixels
        for (x in 0..width) {
            val index = getPixelPosi(x, 200)
            pixels[index] = color(255, 0, 0)
        }
        updatePixels() // after working with pixels
    }

    fun getPixelPosi(x:Int, y:Int): Int {
        return x + y * width
    }

    // method for setting the size of the window
    override fun settings() {
        size(600, 400)

       // image = PImage()
        image =  loadImage("C:\\temp\\test\\processing-3-kotlin\\src\\main\\kotlin\\com\\gomezrondon\\image\\360.jpg")

    }

}