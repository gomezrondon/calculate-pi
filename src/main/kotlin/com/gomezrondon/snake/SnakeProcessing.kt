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
    val csl = 50f
    val constantSpeed = 1f
 //   val normalSpeed =  1f
 lateinit var food:PVector

    lateinit var snake:Snake

    override fun draw() {
        background(COLOR_BLACK)


        if (mousePressed) {
            snake.dir(0f, 0f)
            setFoodOnBoard()
        }

        keyPressed()
        snake.update()
        snake.show()

        fill(255f, 0f, 0f)
        rect(food.x, food.y, csl, csl)

        stroke(255f, 255f, 255f)
        strokeWeight(1f)
        val rowSize = height / csl
        val columnSize = width / csl
        for (i in 0..rowSize.toInt()) {
            line(0f, 0f+csl*i, width.toFloat(), 0f+csl*i)
        }

        for (i in 0..columnSize.toInt()) {
            line(0f+csl*i,0f , 0f+csl*i, height.toFloat())
        }

        noStroke()

     }

    private fun setFoodOnBoard() {
        val randX = random(width.toFloat())
        val randY = random(height.toFloat())
        val x = constrain(randX, 0f, width - csl)
        val y = constrain(randY, 0f, height - csl)

        food = PVector(x, y)
    }

    override fun keyPressed() {

        if (keyCode == PConstants.UP) {
          //  snake.dir(0f, constantSpeed*-1f)
          //  snake.dir(0f, constantSpeed+csl)
        }else if (keyCode == PConstants.DOWN) {
            snake.dir(0f, constantSpeed)
        }else if (keyCode == PConstants.RIGHT) {
           // snake.dir(constantSpeed , 0f)
            snake.dir(constantSpeed , 0f)
        }else if (keyCode == PConstants.LEFT) {
            snake.dir(constantSpeed* -1f, 0f)
        }
    }


    // identical use to setup in Processing IDE except for size()
    override fun setup() {
        snake= Snake(this, csl, constantSpeed)
        setFoodOnBoard()
    }


    // method for setting the size of the window
    override fun settings() {
        size(300, 300)
      //  background(COLOR_BLACK) // it can NOT be here

    }

}