package com.gomezrondon.image


import com.gomezrondon.utils.distMy
import com.gomezrondon.utils.getPixelPosi
import processing.core.PApplet
import processing.core.PConstants
import processing.core.PImage

fun main() {
    PApplet.main("com.gomezrondon.image.ImageDetection")
}

class ImageDetection : PApplet() {

    lateinit var chrome: PImage
    val filepath = "C:\\temp\\test\\processing-3-kotlin\\src\\main\\kotlin\\com\\gomezrondon\\image\\22.png"

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
                             , var group:Int= 0
                             , var porcentajeList: List<Int> = mutableListOf()){

}

    private fun samplingImage() {

       // image(chrome, 0f, 0f)

        loadPixels() //before doing anything with pixels
        chrome.loadPixels()
        val samplingList: MutableList<SamplingPoint> = mutableListOf<SamplingPoint>()
        val stepvalue = 2
        for (x in 0..width  step stepvalue) {
            for (y in 0..height - 2  step stepvalue) { // hacemos un sampling 4 x 4
                val cIndex = getPixelPosi(x, y, width)

                //   val g = green(chrome.get(x, y))
                var color = chrome.get(x, y)

                val notEmpty = samplingList.map { it.color }.filter { containColor(it, color) }.isEmpty()
                val label= getColorLabel(color)
                if (notEmpty && label!= "NO_LABEL") {

                    val colorDistance = colorDistance(color(255, 76, 76), color)
                    samplingList.add(SamplingPoint(x, y, color, label, 0 ,getPorcentajeColor(color)))

                    kotlin.io.println("${label} = R " + red(color) + " G " + green(color) + " B " + blue(color) +  " Color Distance: " + colorDistance)
                }

                //  pixels[cIndex] = color(color)

            }

        }


        var distList:MutableList<Int> = mutableListOf<Int>()

        kotlin.io.println("samplingList size: ${samplingList.size}")

        samplingList.forEach {

            val colorDistance = colorDistance(color(255, 255, 103), it.color) //Blue


            kotlin.io.println( " X: ${it.x}, Y: ${it.y}  ${it.label}   R% " + it.porcentajeList[0] + " G% " + it.porcentajeList[1]+ " B% " + it.porcentajeList[2]+ " Color Distance: " + colorDistance)
        }

        val filterRed = samplingList.filter { it.label == "RED" }
        val filterGre = samplingList.filter { it.label == "GREEN" }
        val filterYel = samplingList.filter { it.label == "YELLOW" }
        val filterBlu = samplingList.filter { it.label == "BLUE" }
        updatePixels() // after working with pixels*/


// using euclidean idstance to compre colors
        noFill()
        samplingList .forEach {
            stroke(it.color)
            rect(it.x.toFloat(), it.y.toFloat(), 2f, 2f)
        }



        val maxDist = 20
        var minDis = 100
        var group= 1
        var samplGroup: MutableList<SamplingPoint> = mutableListOf<SamplingPoint>()
        for (i in 0..filterRed.size - 2) {
            val samp1 = filterRed[i]
            val samp2 = filterRed[i+1]




            val dis  = distMy(samp1.x, samp1.y, samp2.x, samp2.y).toInt()
  /*          if (dis < minDis) {
                minDis = dis
            }*/

            if (dis <= maxDist) {
                samp1.group = group
                samp2.group = group
                samplGroup.add(samp1)
                samplGroup.add(samp2)
            } else {
                samp1.group = group
                samplGroup.add(samp1)
                group++
                samp2.group = group
                samplGroup.add(samp2)
            }

            kotlin.io.println("Dist red: "+ dis + " group "+group)
        }

        kotlin.io.println("min Dist red: "+ minDis)

        val filter = samplGroup.filter { it.group == 5 }

        val mutableByLength: MutableMap<Int, MutableList<SamplingPoint>> = samplGroup.groupByTo(mutableMapOf()) { it.group }

        mutableByLength.forEach { key, Value ->
            kotlin.io.println(">>>>>>>>>>> Group : "+ key)
            findCluster(Value)
        }

        /*        findCluster(filterGre)
               findCluster(filterYel)
               findCluster(filterBlu)*/


    }


     fun colorDistance(color1: Int, color2: Int): Int {
        val red =  red(color1)
        val red2 = red(color2)

        val green =  green(color1)
        val green2 = green(color2)

        val blue =  blue(color1)
        val blue2 = blue(color2)

        val dist = sqrt(pow(red2 - red, 2f) + pow(green2 - green, 2f) + pow(blue2 - blue, 2f) )
        return dist.toInt()
    }


    private fun findCluster(filterRed: List<SamplingPoint>) {
        var centerPoint = Pair(0f, 0f)
        val xAvg = filterRed.map { it.x }.average()
        val yAvg = filterRed.map { it.y }.average()
        stroke(0f, 0f, 255f)
        rectMode(PConstants.CENTER)
        rect(xAvg.toFloat(), yAvg.toFloat(), 10f, 10f)
    }


    fun midPoint(x: Float, y: Float, x1:Float, y1:Float): Pair<Float, Float> {
        val mx: Float = ((x + x1) / 2).toFloat()
        val my: Float = ((y + y1) / 2).toFloat()
        return Pair (mx , my)
    }

    fun getColorLabel(color: Int): String {
        val red =red(color)
        val green= green(color)
        val blue = blue(color)


        val distRed = colorDistance(color(255, 76, 76), color)
        val disBlue = colorDistance(color(68, 63, 255), color)
        val distYel = colorDistance(color(255, 255, 0), color)


        if (disBlue >= 175 && disBlue < 200) {
            return "BLUE"
        }
        if (distRed >= 0 && distRed < 100) {
            return "RED"
        }

        if (distYel >= 130 && distYel < 140) {
            return "YELLOW"
        }



        if (green > 143) {
            if (red < 191 && blue < 191) {
                return "GREEN"
            }
        }


        return "NO_LABEL"

    }

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