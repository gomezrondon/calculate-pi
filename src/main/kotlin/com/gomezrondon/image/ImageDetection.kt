package com.gomezrondon.image


import com.gomezrondon.utils.getPixelPosi
import processing.core.PApplet
import processing.core.PImage

fun main() {
    PApplet.main("com.gomezrondon.image.ImageDetection")
}

class ImageDetection : PApplet() {

    lateinit var chrome: PImage
    val filepath = "C:\\temp\\test\\processing-3-kotlin\\src\\main\\kotlin\\com\\gomezrondon\\image\\my23x.png"

    override fun draw() {


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

    data class SamplingPoint(var x: Int = 0
                             , var y: Int = 0
                             , var color: Int
                             , var label:String = "NO_LABEL"
                             , var porcentajeList: List<Int> = mutableListOf()){

}

    private fun samplingImage() {
        loadPixels() //before doing anything with pixels
        chrome.loadPixels()
        val samplingList: MutableList<SamplingPoint> = mutableListOf<SamplingPoint>()
        val stepvalue = 2
        for (x in 0..width  step stepvalue) {
            for (y in 0..height - 2  step stepvalue) { // hacemos un sampling 4 x 4
                val cIndex = getPixelPosi(x, y, width)

                //   val g = green(chrome.get(x, y))
                var color = chrome.get(x, y)

  /*              //is black
                val isBlack = if_ColorBelow(color, 30)
                if (isBlack) {
                    color = color(0, 0, 0)
                }
                val isWhite = if_ColorAbove(color, 240)
                if (isWhite) {
                    color = color(255, 255, 255)
                }*/

                // create high contrast colors
                //color = oneColorHigh(color)



                val notEmpty = samplingList.map { it.color }.filter { containColor(it, color) }.isEmpty()

                if (notEmpty) {
                    val label= getColorLabel(color)
                    samplingList.add(SamplingPoint(x, y, color, label, getPorcentajeColor(color)))

                    kotlin.io.println("${label} = R " + red(color) + " G " + green(color) + " B " + blue(color))
                }

                //  pixels[cIndex] = color(color)

            }

        }


        var distList:MutableList<Int> = mutableListOf<Int>()



        kotlin.io.println("samplingList size: ${samplingList.size}")


        samplingList.forEach {
            kotlin.io.println( " X: ${it.x}, Y: ${it.y}  ${it.label}   R% " + it.porcentajeList[0] + " G% " + it.porcentajeList[1]+ " B% " + it.porcentajeList[2])
        }

        val filter = samplingList.filter { it.porcentajeList[0] > 80 }
        updatePixels() // after working with pixels*/


        samplingList.forEach {
            stroke(it.color)
            rect(it.x.toFloat(), it.y.toFloat(), 2f, 2f)
        }
    }


    fun getColorLabel(color: Int): String {
        val red =red(color)
        val green= green(color)
        val blue = blue(color)


        if (blue > 191) {
            if (red < 191 && green < 191) {
                return "BLUE"
            }
        }

        if (red > 154) {
            if (green < 91 && blue < 91) {
                return "RED"
            }
        }

        if (green > 143) {
            if (red < 191 && blue < 191) {
                return "GREEN"
            }
        }




        return "NO_LABEL"

    }

/*
    Red = 255 - 0 - 0
    240 -120 - 120


    Green = 0 - 255 - 0
    191- 240 - 191

    Azul =  0  - 0 - 230 -- min
    189 -189- 255 -- max

*/


    private fun f2dec(value: Float): String {
        return "%.1f".format(value)
    }


    fun getPorcentajeColor(color: Int): List<Int> {

        val total = red(color) + green(color) + blue(color)

        val red =  (red(color) * 100) / total
        val gree =  (green(color) * 100) / total
        val blue =  (blue(color) * 100) / total

        return listOf(red.toInt(), gree.toInt(), blue.toInt())
    }

    fun oneColorHigh(color: Int): Int {
        if (red(color) > green(color) + blue(color)) {
            return color(250,0, 0 )
        }

        if (green(color) > red(color) + blue(color)) {
            return color(0,250, 0 )
        }

        if (blue(color) > red(color) + green(color)) {
            return color(0,0, 250 )
        }

        return color
    }

    fun containColor(color1: Int, color2: Int): Boolean {
        val absRed = abs(red(color1) - red(color2))
        val absGreen = abs(green(color1) - green(color2))
        val absBlue = abs(blue(color1) - blue(color2))
        val umbral = 2
        if (absRed > umbral || absGreen > umbral || absBlue > umbral) {
            return false
        }

        return true
    }

    fun if_ColorBelow(color: Int, umbral:Int): Boolean {
         if (red(color) < umbral && green(color) < umbral && blue(color) < umbral) {
            return true
        }
        return false
    }

    fun if_ColorAbove(color: Int, umbral:Int): Boolean {
        if (red(color) > umbral && green(color) > umbral && blue(color) > umbral) {
            return true
        }
        return false
    }


    // identical use to setup in Processing IDE except for size()
    override fun setup() {
        background(255)
        samplingImage()
    }


    // method for setting the size of the window
    override fun settings() {
       // size(250, 180)
        size(384, 216)

        // image = PImage()

        chrome = loadImage(filepath)

    }

}