package com.smu.sangmyung.quiz.worng

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.view.View


class CircleGraphView(context: Context?,result:Int) : View(context) {


    val ZERO = -90f
    private var mPaints: Paint? = Paint()
    private var mPaintsBack: Paint? = Paint()
    private var mUseCenters: BooleanArray = BooleanArray(4)
    //    private var mOvals: Array<RectF?> = arrayOfNulls(4)
    private var mBigOval: RectF = RectF(40f, 40f, 260f, 260f)
    private var mStart: Float = 0.toFloat()
    private var mSweep: Float = (result * 3.6).toFloat()
//    private var mBigIndex: Int = 0

    private val SWEEP_INC = 1f
    private val START_INC = 0f


    private fun drawArcs(canvas: Canvas, oval: RectF, useCenter: Boolean, paint: Paint?, mSweep: Float) {
        canvas.drawArc(oval, ZERO, mSweep, useCenter, paint!!)
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        mPaints = Paint(mPaints)
        mPaints!!.style = Paint.Style.STROKE
        mPaints!!.strokeWidth = 20f
        mPaints!!.color = Color.RED
        mPaints!!.isAntiAlias = true
        mPaints!!.strokeCap = Paint.Cap.BUTT
        mPaintsBack = Paint(mPaints)
        mPaintsBack!!.style = Paint.Style.STROKE
        mPaintsBack!!.strokeWidth = 15f
        mPaintsBack!!.color = Color.GRAY
        mPaintsBack!!.isAntiAlias = true
        mPaintsBack!!.strokeCap = Paint.Cap.BUTT
//        canvas.drawColor(Color.GRAY)
        drawArcs(canvas, mBigOval, mUseCenters[2], mPaintsBack, 360f)
        drawArcs(canvas, mBigOval, mUseCenters[2], mPaints, mSweep)


//        mSweep += SWEEP_INC
        if (mSweep > 360) {
            mSweep -= 360f
            mStart += START_INC
            if (mStart >= 360) {
                mStart -= 360f
            }
//            mBigIndex = mBigIndex % mOvals.size
        }
        invalidate()
    }

    fun getSweepInc(): Int {
        return java.lang.Float.floatToIntBits(SWEEP_INC)
    }

}

