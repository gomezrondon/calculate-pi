package com.gomezrondon.snake

import processing.core.PApplet


class Snake {

    private var sketch: PApplet
    private var x = 0f
    private var y = 0f
    private var xspeed = 0f
    private var yspeed = 0f
    private var csl = 0f


    constructor(sketch: PApplet, csl:Float, xspeed:Float) {
        this.sketch = sketch
        this.csl = csl
        this.xspeed = xspeed
    }

    fun dir(x: Float, y: Float) {
        xspeed = x
        yspeed = y
    }

    fun update() {
        x += xspeed * csl
        y += yspeed * csl

        x = PApplet.constrain(x, 0f, sketch.width-csl)
        y = PApplet.constrain(y, 0f, sketch.height-csl)
    }

    fun show() {
        sketch.fill(255)
        sketch.rect(x, y, csl, csl)
    }


}