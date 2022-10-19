package com.pabloangeles.hackernewsapp.home.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.drawable.Drawable
import android.util.DisplayMetrics
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.pabloangeles.hackernewsapp.core.extension.dp
import com.pabloangeles.hackernewsapp.home.R

class ItemDragDecorator(val context: Context, val recyclerView: RecyclerView) {
  /* Declaring a variable that will be initialized later. */
  private lateinit var swipeHelper: ItemTouchHelper
  /* A function that takes an Int as a parameter and returns Unit. */
  var onSwipedLeft: ((Position: Int) -> Unit)? = null
  /* Getting the display metrics of the device. */
  private val displayMetrics: DisplayMetrics = context.resources.displayMetrics
  /* Getting the width of the device in dp. */
  private val width = (displayMetrics.widthPixels / displayMetrics.density).toInt().dp(context)
  /* Getting the delete icon from the resources. */
  @SuppressLint("UseCompatLoadingForDrawables")
  private val deleteIcon = context.resources.getDrawable(R.drawable.ic_outline_delete_24, null)
  /* Getting the color of the delete icon. */
  private val deleteColor = context.resources.getColor(android.R.color.holo_red_light)
  /* Getting the margin of the text in dp. */
  private val textMargin = 30.dp(context)
  /* A constant that is used to divide the width of the screen by 3. */
  private val sizeWidthDx = 3

  /** It sets up the swipe functionality for the recycler view. */
  fun setup() {
    swipeHelper =
        ItemTouchHelper(
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
              override fun onMove(
                  recyclerView: RecyclerView,
                  viewHolder: RecyclerView.ViewHolder,
                  target: RecyclerView.ViewHolder
              ) = true

              override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                onSwipedLeft?.invoke(viewHolder.adapterPosition)
                recyclerView.adapter?.notifyItemChanged(viewHolder.adapterPosition)
                // swipeHelper.startSwipe(viewHolder)
              }

              override fun onChildDraw(
                  canvas: Canvas,
                  recyclerView: RecyclerView,
                  viewHolder: RecyclerView.ViewHolder,
                  dX: Float,
                  dY: Float,
                  actionState: Int,
                  isCurrentlyActive: Boolean
              ) {

                when {
                  dX < (width / sizeWidthDx) -> {
                    leftDrawBackgroundAndIcon(canvas, viewHolder, deleteIcon)
                  }
                }
                super.onChildDraw(
                    canvas, recyclerView, viewHolder, dX / 2, dY, actionState, isCurrentlyActive)
              }
            })
    swipeHelper.attachToRecyclerView(recyclerView)
  }

  /**
   * Draw a rectangle on the right side of the view, then draw the icon on top of it
   *
   * @param canvas The canvas on which the background will be drawn.
   * @param viewHolder RecyclerView.ViewHolder,
   * @param drawableIcon The icon to be drawn on the left side of the item.
   */
  private fun leftDrawBackgroundAndIcon(
      canvas: Canvas,
      viewHolder: RecyclerView.ViewHolder,
      drawableIcon: Drawable,
  ) {

    val rectF =
        RectF(
            viewHolder.itemView.width.toFloat() / 2,
            viewHolder.itemView.top.toFloat(),
            viewHolder.itemView.right.toFloat(),
            viewHolder.itemView.bottom.toFloat())

    val paint = Paint().apply { color = deleteColor }

    canvas.drawRect(rectF, paint)
    drawableIcon.bounds =
        Rect(
            viewHolder.itemView.width - textMargin - drawableIcon.intrinsicWidth,
            viewHolder.itemView.top + textMargin + 8.dp(context),
            viewHolder.itemView.width - textMargin,
            viewHolder.itemView.top + drawableIcon.intrinsicHeight + textMargin + 8.dp(context))

    drawableIcon.draw(canvas)
  }
}
