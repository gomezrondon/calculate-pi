import processing.core.PApplet
import processing.core.PVector

//verctors
//https://www.youtube.com/watch?v=mWJkvxQXIa8
fun main() {
    PApplet.main("UsingProcessing")
}

class UsingProcessing: PApplet() {

    val COLOR_BLACK = 50
    val COLOR_WHITE = 255

    lateinit var location: PVector
    lateinit var velocity: PVector
    lateinit var acceleration: PVector


    override fun draw() {
        background(COLOR_WHITE)

        val gravity = PVector(0.0f, 0.3f)
        applyForce(gravity)


        if (mousePressed) {
            val wind = PVector(0.2f, 0f)
            applyForce(wind)
        }

        update()
        checkEdges()
        display()

       }


    // identical use to setup in Processing IDE except for size()
    override fun setup() {


    }


    // method for setting the size of the window
    override fun settings() {
        size(640, 360)
        location = PVector((width / 2).toFloat(), (height / 2).toFloat())
        velocity = PVector(0f, 0f)
        acceleration = PVector(0f, 0f)


    }

//------------------
    fun applyForce(force: PVector) {
        acceleration.add(force)
    }

    fun update() {
        velocity.add(acceleration)
        location.add(velocity)
        acceleration.mult(0f)
        kotlin.io.println(location.toString())
     //   velocity.limit(5f)

    }

    fun display() {
        stroke(0)
        strokeWeight(2f)
        fill(127)
        ellipse(location.x, location.y, 48f, 48f)
    }

    fun checkEdges() {

        if ((location.x >= width) || (location.x < 0)) {
            velocity.x = velocity.x * -1;
        }
        if ((location.y >= height) || (location.y < 0)) {
            velocity.y = velocity.y * -1f;
        }
    }

}

