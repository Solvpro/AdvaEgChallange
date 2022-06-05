package com.se7sopro.advaegchallange.presenter.view.ui.fullView

import android.os.Bundle
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.se7sopro.advaegchallange.databinding.ActivityFullImageBinding
import com.se7sopro.advaegchallange.utils.loadImageFromCache
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.max
import kotlin.math.min

@AndroidEntryPoint
class FullImageActivity : AppCompatActivity() {

    private lateinit var scaleGestureDetector: ScaleGestureDetector
    private lateinit var binding: ActivityFullImageBinding
    private lateinit var ivFullImage: ImageView
    private var sacleFactor = 1.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullImageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        ivFullImage = binding.ivFull
        ivFullImage.loadImageFromCache(
            intent.getStringExtra("imageUrl")!!
        )
        scaleGestureDetector = ScaleGestureDetector(this, ScaleListener())
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        scaleGestureDetector.onTouchEvent(event)
        return true
    }

    private inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(detector: ScaleGestureDetector?): Boolean {
            sacleFactor *= detector?.scaleFactor!!
            sacleFactor = max(0.1f, min(sacleFactor, 10.0f))
            ivFullImage.scaleX = sacleFactor
            ivFullImage.scaleY = sacleFactor
            return true
        }
    }

}