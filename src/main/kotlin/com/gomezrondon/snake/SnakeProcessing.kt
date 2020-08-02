package com.gomezrondon.snake

import processing.core.PApplet
import processing.core.PConstants


fun main() {
    PApplet.main("com.gomezrondon.snake.SnakeProcessing")
}


class SnakeProcessing: PApplet() {

    val COLOR_BLACK = 50
    val COLOR_WHITE = 255
    val csl = 10f

    lateinit var snake:Snake

    override fun draw() {
        background(COLOR_BLACK)

        if (mousePressed) {
            snake.dir(0f, 0f)
        }

        keyPressed()
        snake.update()
        snake.show()


     }

    override fun keyPressed() {

        if (keyCode == PConstants.UP) {
            snake.dir(0f, -1f)
        }else if (keyCode == PConstants.DOWN) {
            snake.dir(0f, 1f)
        }else if (keyCode == PConstants.RIGHT) {
            snake.dir(1f, 0f)
        }else if (keyCode == PConstants.LEFT) {
            snake.dir(-1f, 0f)
        }
    }


    // identical use to setup in Processing IDE except for size()
    override fun setup() {
        snake= Snake(this, csl)
       // frameRate(10f);
    }


    // method for setting the size of the window
    override fun settings() {
        size(600, 600)
      //  background(COLOR_BLACK) // it can NOT be here

    }

}