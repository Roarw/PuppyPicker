package com.example.pupperpicker.ui

import android.content.Context
import android.graphics.Bitmap
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.cardview.widget.CardView
import com.example.pupperpicker.PuppyPickerApplication
import com.example.pupperpicker.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DogCard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    private var _view: View? = null
    private var _url: String = ""

    init {
        _view = LayoutInflater.from(context).inflate(R.layout.card_pupper, this, true)
    }

    fun setupButton(viewModelScope: CoroutineScope) {
        if (_view != null) {
            _view!!.findViewById<ImageView>(R.id.favoriteButton).setOnClickListener {
                val dbHelper = (context.applicationContext as PuppyPickerApplication).dbHelper
                if (_url.isNotEmpty() && dbHelper != null) {
                    viewModelScope.launch {
                        withContext(Dispatchers.IO) {
                            if (dbHelper.doesURLExist(_url)) {
                                dbHelper.deleteURL(_url)
                            }
                            else {
                                dbHelper.createURL(_url)
                            }
                        }
                    }
                }
            }
        }
    }

    fun setURL(url: String) {
        _url = url
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