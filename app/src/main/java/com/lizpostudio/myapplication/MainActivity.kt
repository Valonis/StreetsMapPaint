package com.lizpostudio.myapplication

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.PointF
import android.os.Bundle
import android.view.MotionEvent
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

private const val zoomStep = 1.2

class MainActivity : AppCompatActivity() {

    var fillMask = mutableListOf<PointF>()
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        var startPT = PointF()
        var downPT = PointF()


        var moveMap = false
       val buttonZoomIn:ImageView = findViewById(R.id.zoom_in)
        val buttonZoomOut:ImageView = findViewById(R.id.zoom_out)
        val buttonToggle:ImageView = findViewById(R.id.toggleDrag)

        val mapView: ImageView = findViewById(R.id.map_sample)

        mapView.setImageResource(R.drawable.map_sample)
        val maskView:com.lizpostudio.myapplication.MaskView = findViewById(R.id.mask_view_to_draw)

        val screenWidth = Resources.getSystem().displayMetrics.widthPixels

        val params =
            mapView.layoutParams
            val currentW = params.width.toFloat()
            val ratio = screenWidth/currentW

        params.width = (params.width* ratio).toInt()
        params.height = (params.height* ratio).toInt()
        mapView.layoutParams = params

        buttonToggle.setOnClickListener {
            moveMap = !moveMap
        }

        mapView.setOnTouchListener { v, m ->

            if (m.action == MotionEvent.ACTION_DOWN) {
               downPT = PointF(m.x, m.y)
                startPT = PointF(v.x, v.y)
             // moveMap = true
            }
            if (m.action == MotionEvent.ACTION_MOVE) {

                if (moveMap) {
                    // movemap
                    mapView.translationX =  startPT.x + m.x - downPT.x
                    mapView.translationY =  startPT.y + m.y - downPT.y
                    startPT = PointF(v.x, v.y)
                } else {
                    // print mask
                    fillMask.add(PointF(m.x+startPT.x,m.y+startPT.y))
                    maskView.fillMask = fillMask
                    maskView.invalidate()

                }
            }
   /*         if (m.action == MotionEvent.ACTION_UP) {
              //  moveMap=false
            }*/

            true
        }

        buttonZoomIn.setOnClickListener {

            val params =
                mapView.layoutParams

            params.width = (params.width* zoomStep).toInt()
            params.height = (params.height* zoomStep).toInt()
            mapView.layoutParams = params

        }

        buttonZoomOut.setOnClickListener {
            val params =
                mapView.layoutParams
            params.width = (params.width/zoomStep).toInt()
            params.height = (params.height/ zoomStep).toInt()
            mapView.layoutParams = params
        }
    }


}