import processing.core.PApplet
import processing.core.PVector


//verctors
//https://www.youtube.com/watch?v=mWJkvxQXIa8
fun main() {
    PApplet.main("UsingProcessing")
}

class UsingProcessing: PApplet() {

  //  val COLOR_BLACK = 50
    val COLOR_WHITE = 255

    lateinit var m:Mover

    override fun draw() {
        background(COLOR_WHITE)

        val gravity = PVector(0.0f, 0.3f)
        m.applyForce(gravity)


        if (mousePressed) {
            val wind = PVector(0.2f, 0f)
            m.applyForce(wind)
        }

        m.update()
        m.checkEdges()
        m.display()

       }


    // identical use to setup in Processing IDE except for size()
    override fun setup() {


    }


    // method for setting the size of the window
    override fun settings() {
        size(640, 360)
        m = Mover(this)
    }

}

