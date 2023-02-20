package com.example.pupperpicker.ui

import android.content.Context
import android.graphics.Bitmap
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.cardview.widget.CardView
import com.example.pupperpicker.R

class DogCard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    private var _view: View? = null

    init {
        setupView()
    }

    private fun setupView() {
        _view = LayoutInflater.from(context).inflate(R.layout.card_pupper, this, true)
    }

    fun setImage(bitmap: Bitmap) {
        if (_view != null) {
            _view!!.findViewById<ImageView>(R.id.pupperImage).setImageBitmap(bitmap)
        }
    }

    fun setImage(id: Int) {
        if (_view != null) {
            _view!!.findViewById<ImageView>(R.id.pupperImage).setImageResource(id)
        }
    }
}