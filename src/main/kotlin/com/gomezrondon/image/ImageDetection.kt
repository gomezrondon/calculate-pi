package com.gomezrondon.image


import com.gomezrondon.utils.getPixelPosi
import processing.core.PApplet
import processing.core.PImage

fun main() {
    PApplet.main("com.gomezrondon.image.ImageDetection")
}


class ImageDetection: PApplet() {

    lateinit var chrome:PImage


    override fun draw() {
        loadPixels() //before doing anything with pixels
        chrome.loadPixels()
        for (x in 0..width) {
            for (y in 0..height-2) { // hay un problema con los 2 ultimos pixeles
                val cIndex = getPixelPosi(x, y,width)

             //   val g = green(chrome.get(x, y))
                val get = chrome.get(x, y)

                pixels[cIndex] = color(get)

            }

        }
        updatePixels() // after working with pixels*/


/*        loadPixels() //before doing anything with pixels
      //  crow.loadPixels()
        for (x in 0..width) {
            for (y in 0..height-2) { // hay un problema con los 2 ultimos pixeles
                val cIndex = getPixelPosi(x, y,width)

                val g = green(crow.get(x, y))

                pixels[cIndex] = color(0f, g, 0f)

            }

        }
        updatePixels() // after working with pixels*/
    }



    // identical use to setup in Processing IDE except for size()
    override fun setup() {
        background(255)

    }



    // method for setting the size of the window
    override fun settings() {
        size(56, 46)

       // image = PImage()
        chrome =  loadImage("C:\\temp\\test\\processing-3-kotlin\\src\\main\\kotlin\\com\\gomezrondon\\image\\test5.png")

    }

}