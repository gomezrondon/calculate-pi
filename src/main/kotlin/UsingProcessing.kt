import org.w3c.dom.ranges.Range
import processing.core.PApplet
import processing.core.PConstants


fun main() {
    PApplet.main("UsingProcessing")
}

class UsingProcessing : PApplet() {

    val r:Float= 200f
    val COLOR_BLACK = 50
    val COLOR_WHITE = 255
    var circle:Float = 0f
    var total:Float = 1f
    var i = 1
    //  stroke(255f, 0f, 0f) // RED
    //stroke(0f, 255f, 0f) // GREEN

    override fun draw() {
        translate((width/2).toFloat(), (height/2).toFloat()) // offset the center of the coordinate from the upper corner to the center


        (1..i).forEach {
           var x:Float = random(-r, r)
           var y:Float = random(-r, r)


           val distTwoPoints = distTwoPoints(0f, 0f, x, y)
           // kotlin.io.println(distTwoPoints)
           total++
           if (distTwoPoints < r) {
               circle++
               stroke(0f, 255f, 0f) // GREEN
           }else{
               stroke(COLOR_WHITE) // GREEN
           }
           point(x, y)


            speedUp()


       }



        val pi:Float = (circle.div(total)).times(4)
        kotlin.io.println("Pi: ${pi.toString()} / Pi=3.14159265")

    }

    private fun speedUp() {
        if (total.toInt() == 500) {
            i += 10
        } else if (total.toInt() == 3000) {
            i += 100
        } else if (total.toInt() == 40000) {
            i += 100
        } else if (total.toInt() == 100000) {
            i += 10000
        }
    }


    fun distTwoPoints(x1: Float, y1: Float, x2: Float, y2: Float): Double {
        val dis: Double  = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1).toDouble())

        return dis
    }


    // method for setting the size of the window
    override fun settings() {
        size(400, 400)


    }

    // identical use to setup in Processing IDE except for size()
    override fun setup() {
        background(COLOR_BLACK)
        translate((width/2).toFloat(), (height/2).toFloat()) // offset the center of the coordinate from the upper corner to the center
        stroke(COLOR_WHITE)
        noFill()
        ellipse(0f,0f,r*2,r*2)

        rectMode(PConstants.CENTER)
        stroke(255f, 0f, 0f) // RED
        strokeWeight(5f)
        rect(0f,0f, r*2, r*2)




    }

}