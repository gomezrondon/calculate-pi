import processing.core.PApplet
import processing.core.PVector


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

        val f = PVector(0.1f, 0.1f)
        applyForce(f)
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
        acceleration = force
    }

    fun update() {
        velocity.add(acceleration)
        location.add(velocity)
        kotlin.io.println(location.toString())
        velocity.limit(5f)

    }

    fun display() {
        stroke(0)
        strokeWeight(2f)
        fill(127)
        ellipse(location.x, location.y, 48f, 48f)
    }

    fun checkEdges() {

        val bouncingForce:Float= -1f

        if (location.x > width) {
            velocity = PVector(-velocity.x, velocity.y)
            velocity.mult(-0.1f)
        } else if (location.x < 0) {
            location.x = width.toFloat()
        }
        if (location.y > height) {
            velocity = PVector(velocity.x, -velocity.y)

        } else if (location.y < 0) {
            location.y = height.toFloat()
        }
    }

}

