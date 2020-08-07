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
    val constantSpeed = 50f
 //   val normalSpeed =  1f
    var f_rate =0
 lateinit var food:PVector

    lateinit var snake:Snake

    override fun draw() {
        background(COLOR_BLACK)

        drawBoard()
        noStroke()

        if (mousePressed) {
            setFoodOnBoard()
        }


        if (f_rate % 12 == 0) {
            snake.update()
            //    snake.stop()
        }
        snake.show()


        fill(255f, 0f, 0f)
        rect(food.x, food.y, csl, csl)




        f_rate++
     }

    override fun keyPressed() {

        if (keyCode == PConstants.UP) {
              snake.dir(0f, constantSpeed*-1f)
        }else if (keyCode == PConstants.DOWN) {
            snake.dir(0f, constantSpeed)
        }else if (keyCode == PConstants.RIGHT) {
            snake.dir(constantSpeed , 0f)
        }else if (keyCode == PConstants.LEFT) {
            snake.dir(constantSpeed* -1f, 0f)
        }
    }

    private fun setFoodOnBoard() {
        val rowSize = height / csl
        val columnSize = width / csl

        var randX = random(width.toFloat())
        randX = findBox(columnSize, randX)
        var randY = random(height.toFloat())
        randY = findBox(rowSize, randY)

        val x = constrain(randX, 0f, width - csl)
        val y = constrain(randY, 0f, height - csl)

        food = PVector(x, y)
    }

    private fun findBox(columnSize: Float, randX: Float): Float {
        var randX1 = randX
        for (i in 0..columnSize.toInt()) {
            val line = csl * i
            if (randX1 >= line && randX1 < line + csl) {
                randX1 = line
                break
            }
        }
        return randX1
    }

    private fun drawBoard() {
        stroke(255f, 255f, 255f)
        strokeWeight(1f)
        val rowSize = height / csl
        val columnSize = width / csl
        for (i in 0..rowSize.toInt()) {
            line(0f, 0f + csl * i, width.toFloat(), 0f + csl * i)
        }

        for (i in 0..columnSize.toInt()) {
            line(0f + csl * i, 0f, 0f + csl * i, height.toFloat())
        }
    }






    // identical use to setup in Processing IDE except for size()
    override fun setup() {
        snake= Snake(this, csl, constantSpeed)
        setFoodOnBoard()
       // frameRate(4f);
    }


    // method for setting the size of the window
    override fun settings() {
        size(300, 300)
      //  background(COLOR_BLACK) // it can NOT be here

    }

}