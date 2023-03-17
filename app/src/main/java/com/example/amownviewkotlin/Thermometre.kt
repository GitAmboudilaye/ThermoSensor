package com.example.amownviewkotlin

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View

class Thermometre(context: Context?, var tempAfficher: Float) : View(context) {

    private var pinceau: Paint

    init {

        this.pinceau = Paint(10)

    }

    fun setDynamicColor(tempAffiche: Float) {

        if (tempAffiche >= 0 && tempAffiche < 30) {
            pinceau.color = Color.BLUE
        } else if (tempAffiche >= 30 && tempAffiche < 60) {
            pinceau.color = Color.GREEN + Color.RED
        }
        if (tempAffiche >= 60) {
            pinceau.color = Color.RED
        }
        invalidate()
    }

    fun setTempAffiche(tempAffiche: Float) {
        this.tempAfficher = tempAffiche * 15
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val height = height
        val width = width

        var xs = width / 2.0f
        var ys = (height - 200f)
        var xf = width / 2.0f
        var yf = 280.0f
        pinceau.strokeWidth = 10f

        val paint = Paint()
        //small tube
        canvas.drawLine(xs + 10, ys, xf + 10, yf, paint)
        canvas.drawLine(xs - 10, ys, xf - 10, yf, paint)

        //big
        canvas.drawLine(xs + 80, ys + 100f, xf + 80, yf, paint)
        canvas.drawLine(xs - 80, ys + 100f, xf - 80, yf, paint)

        canvas.drawLine(xs, ys, xf, (ys - tempAfficher), pinceau)

        //horizontale line
        canvas.drawLine(xs + 80, ys + 100, xs - 80, ys + 100, paint)
        canvas.drawLine(xs + 80, ys, xs - 80, ys, paint)

        val p = Paint()
        p.strokeWidth = 2f

        for (i in 0..10) {
            //-------------right(°C)----------------
            canvas.drawLine(xs + 10, ys - 10 - i * 150, xs + 30, ys - 10 - i * 150, p)
            canvas.drawText((i * 10).toString(), xs + 35, ys - 10 - i * 150, p)

            if (i == 10) {
                canvas.drawText("°C", xs + 35, ys - 40 - i * 150, p)
            }
            for (j in 0 until 10) {
                if (i < 10) {
                    canvas.drawLine(
                        xs + 10, ys - 10 - i * 150 - j * 15,
                        xs + 20, ys - 10 - i * 150 - j * 15, p
                    )
                }
            }
        }
        //-------------------------------------
        canvas.drawLine(xs + 80, yf, xs, yf - 200, paint)
        canvas.drawLine(xs - 80, yf, xs, yf - 200, paint)
        //----------------cercle--------------
        canvas.drawCircle(xs, ys, 20f, pinceau)

    }
}