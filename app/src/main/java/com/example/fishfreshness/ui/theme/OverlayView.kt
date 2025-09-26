package com.example.fishfreshness

import kotlin.math.max
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class OverlayView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private var boxes: List<RectF> = emptyList()
    private var labels: List<String> = emptyList()
    private var imageWidth: Int = 1
    private var imageHeight: Int = 1
    private var displayRect: RectF? = null // optional area where the image is shown inside the view

    private val boxPaint = Paint().apply {
        color = Color.GREEN
        style = Paint.Style.STROKE
        strokeWidth = 6f
    }

    private val textPaint = Paint().apply {
        color = Color.YELLOW
        textSize = 36f
        style = Paint.Style.FILL
    }

    /**
     * newBoxes: boxes in ORIGINAL image coordinates (0..imageWidth, 0..imageHeight)
     * imgW / imgH: original image size
     * display: optional RectF telling where the image is drawn inside this view (in view coordinates).
     * If display is null, overlay stretches boxes using viewSize/imageSize.
     */
    fun setResults(newBoxes: List<RectF>, newLabels: List<String>, imgW: Int, imgH: Int, display: RectF? = null) {
        boxes = newBoxes
        labels = newLabels
        imageWidth = maxOf(1, imgW)
        imageHeight = maxOf(1, imgH)
        displayRect = display
        postInvalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (boxes.isEmpty()) return

        // If a displayRect was supplied, use it; otherwise map image to full view size.
        val d = displayRect
        val offsetX: Float
        val offsetY: Float
        val scaleX: Float
        val scaleY: Float

        if (d != null) {
            offsetX = d.left
            offsetY = d.top
            scaleX = d.width() / imageWidth.toFloat()
            scaleY = d.height() / imageHeight.toFloat()
        } else {
            // map image to full view
            offsetX = 0f
            offsetY = 0f
            scaleX = width.toFloat() / imageWidth.toFloat()
            scaleY = height.toFloat() / imageHeight.toFloat()
        }

        for (i in boxes.indices) {
            val b = boxes[i]
            val left = offsetX + b.left * scaleX
            val top = offsetY + b.top * scaleY
            val right = offsetX + b.right * scaleX
            val bottom = offsetY + b.bottom * scaleY
            val r = RectF(left, top, right, bottom)
            canvas.drawRect(r, boxPaint)

            val label = labels.getOrNull(i) ?: "Object"
            // draw background for text for readability
            val textX = r.left
            val textY = max(0f, r.top - 8f)
            canvas.drawText(label, textX, textY.coerceAtLeast(textPaint.textSize), textPaint)
        }
    }
}
