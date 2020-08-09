package com.gomezrondon.image


import processing.core.PApplet
import processing.core.PImage

fun main() {
    PApplet.main("com.gomezrondon.image.ImageDetection")
}


class ImageDetection: PApplet() {

    lateinit var crow:PImage

    override fun draw() {
        // how to place an image on the canvas
        // image(crow, 0f, 0f)

        loadPixels() //before doing anything with pixels
      //  crow.loadPixels()

        for (x in 0..width) {
            for (y in 0..height-2) { // hay un problema con los 2 ultimos pixeles
                val cIndex = getPixelPosi(x, y,width)
                val ImageIndex = getPixelPosi(x, y,crow.width)
             //    println(">>> y: $y")
                pixels[cIndex] = crow.pixels[ImageIndex]
            }

        }
        updatePixels() // after working with pixels
    }


    // identical use to setup in Processing IDE except for size()
    override fun setup() {
        background(255)

    }

    fun getPixelPosi(x:Int, y:Int, w:Int): Int {
        return x + y * w
    }

    // method for setting the size of the window
    override fun settings() {
        size(858, 536)

       // image = PImage()
        crow =  loadImage("C:\\temp\\test\\processing-3-kotlin\\src\\main\\kotlin\\com\\gomezrondon\\image\\360.jpg")

    }

}