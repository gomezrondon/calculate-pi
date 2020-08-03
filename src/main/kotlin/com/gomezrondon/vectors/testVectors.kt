package com.gomezrondon.vectors


import processing.core.PApplet
import processing.core.PVector

fun main() {
    PApplet.main("com.gomezrondon.vectors.testVectors")
}


class testVectors: PApplet() {


    override fun draw() {
        background(255)
        strokeWeight(5f)
        stroke(0)
        noFill()

        translate((width/2).toFloat(), (height/2).toFloat())
        ellipse(mouseX.toFloat(), mouseY.toFloat(),4f, 4f)
        var mouse = PVector(mouseX.toFloat(), mouseY.toFloat())
        var center = PVector(100f, 0f)

      //  mouse.sub(center)
       mouse.add(center)


        //Black LIne
        line(0f,0f,mouse.x, mouse.y)

        //Red Line
        stroke(255f, 0f, 0f)
        line(mouseX.toFloat(), mouseY.toFloat(), mouseX.toFloat()+ center.x, mouseY.toFloat()+center.y)

        // Yellow line
        stroke(255f, 255f, 0f)
        line(0f,0f,mouseX.toFloat(), mouseY.toFloat())

    }


    override fun setup() {

    }


    // method for setting the size of the window
    override fun settings() {
        size(600, 600)
        //  background(COLOR_BLACK) // it can NOT be here

    }


}