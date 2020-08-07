package com.gomezrondon.snake

import processing.core.PApplet
import processing.core.PVector


class Snake {

    private var sketch: PApplet
    private var location = PVector(0f, 0f)
    private var speed = PVector(0f, 0f)
    private var csl = 0f
    var snakeTail:MutableList<PVector> = mutableListOf()
    var tailSize = 1


    constructor(sketch: PApplet, csl:Float, xspeed:Float) {
        this.sketch = sketch
        this.csl = csl
        speed = PVector(xspeed, 0f)
    }

    fun dir(x: Float, y: Float) {
        speed = PVector(x, y)
    }

    fun stop() {
        speed = PVector(0f, 0f)
    }



    fun update() {
        location.add(speed)

        location.x = PApplet.constrain(location.x, 0f, sketch.width-csl)
        location.y = PApplet.constrain(location.y, 0f, sketch.height-csl)

        val contains = snakeTail.contains(PVector(location.x, location.y))
        if (!contains) {
            snakeTail.add(PVector(location.x, location.y))
        }

        if (snakeTail.size > tailSize) {
            snakeTail.removeAt(0)

        }

/*        snakeTail.forEach { print(" X= ${it.x}, Y= ${it.y} > ") }
        println(snakeTail.size)*/

    }

    fun show() {
        sketch.fill(255)
        sketch.rect(location.x, location.y, csl, csl)

        snakeTail.forEach {
            sketch.rect(it.x, it.y, csl, csl)
        }
    }

    fun collision(food: PVector): Boolean {

        snakeTail.forEach {
            if (it.x == food.x && it.y == food.y) {
                tailSize++

                return true
            }
        }

        println("tailSize: $tailSize")

        return false
    }


}


