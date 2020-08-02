package com.gomezrondon.snake

import processing.core.PApplet
import processing.core.PConstants
import processing.core.PVector


fun main() {
    PApplet.main("com.gomezrondon.snake.SnakeProcessing")
}


class SnakeProcessing: PApplet() {

    val COLOR_BLACK = 50
    val COLOR_WHITE = 255
    val csl = 10f
    val constantSpeed = 0.4f
 //   val normalSpeed =  1f
 lateinit var food:PVector

    lateinit var snake:Snake

    override fun draw() {
        background(COLOR_BLACK)

        if (mousePressed) {
            snake.dir(0f, 0f)
            food = PVector(random(width.toFloat()), random(height.toFloat()))
        }

        keyPressed()
        snake.update()
        snake.show()

        fill(255f, 0f, 0f)
        rect(food.x, food.y, csl, csl)

     }

    override fun keyPressed() {

        if (keyCode == PConstants.UP) {
            snake.dir(0f, constantSpeed*-1f)
        }else if (keyCode == PConstants.DOWN) {
            snake.dir(0f, constantSpeed* 1f)
        }else if (keyCode == PConstants.RIGHT) {
            snake.dir(constantSpeed *1f, 0f)
        }else if (keyCode == PConstants.LEFT) {
            snake.dir(constantSpeed* -1f, 0f)
        }
    }


    // identical use to setup in Processing IDE except for size()
    override fun setup() {
        snake= Snake(this, csl, constantSpeed)
      //  frameRate(20f)
        food = PVector(random(width.toFloat()), random(height.toFloat()))
       // frameRate(10f);
    }


    // method for setting the size of the window
    override fun settings() {
        size(600, 600)
      //  background(COLOR_BLACK) // it can NOT be here

    }

}