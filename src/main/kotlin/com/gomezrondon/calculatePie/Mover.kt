package com.gomezrondon.calculatePie

import processing.core.PApplet
import processing.core.PVector

class Mover {

    lateinit var sketch: PApplet
    lateinit var location: PVector
    lateinit var velocity: PVector
    lateinit var acceleration: PVector

    constructor(sketch: PApplet) {
        this.sketch = sketch
        location = PVector(sketch.random(sketch.width.toFloat()), (sketch.height / 2).toFloat())
        velocity = PVector(0f, 0f)
        acceleration = PVector(0f, 0f)
    }

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
        sketch.stroke(0)
        sketch.strokeWeight(2f)
        sketch.fill(127)
        sketch.ellipse(location.x, location.y, 48f, 48f)
    }

    fun checkEdges() {

        if ((location.x >= sketch.width) || (location.x < 0)) {
            velocity.x = velocity.x * -1;
        }
        if ((location.y >= sketch.height) || (location.y < 0)) {
            velocity.y = velocity.y * -1f;
        }
    }

}