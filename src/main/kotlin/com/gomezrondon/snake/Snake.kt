package com.gomezrondon.snake

import processing.core.PApplet
import processing.core.PVector


class Snake {

    private var sketch: PApplet
    private var location = PVector(0f, 0f)
    private var speed = PVector(0f, 0f)
    private var csl = 0f


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
    }

    fun show() {
        sketch.fill(255)
        sketch.rect(location.x, location.y, csl, csl)
    }

    fun collision(food: PVector): Boolean {

        if (location.x == food.x && location.y == food.y) {
            return true
        }

        return false
    }


}