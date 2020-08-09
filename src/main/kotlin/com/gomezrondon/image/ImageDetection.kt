package com.gomezrondon.image


import com.gomezrondon.utils.distMy
import com.gomezrondon.utils.getCENTER
import com.gomezrondon.utils.getPixelPosi
import processing.core.PApplet
import processing.core.PConstants
import processing.core.PImage
import processing.core.PVector

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
                val index = getPixelPosi(x, y,crow.width)
                val index2 = getPixelPosi(x+1, y,crow.width)

                val b1 = crow.pixels[index]
                val b2 = crow.pixels[index2]
                // if the difference is low (very similar) then is going to be black
                // if diff is high then gray or white.
                val diff = abs(b1 - b2)

                if (diff < 100) {
                    pixels[cIndex] = color(0)
                } else {
                    pixels[cIndex] = color(255)
                }

            }

        }
        updatePixels() // after working with pixels
    }



    // identical use to setup in Processing IDE except for size()
    override fun setup() {
        background(255)

    }



    // method for setting the size of the window
    override fun settings() {
        size(858, 536)

       // image = PImage()
        crow =  loadImage("C:\\temp\\test\\processing-3-kotlin\\src\\main\\kotlin\\com\\gomezrondon\\image\\360.jpg")

    }

}