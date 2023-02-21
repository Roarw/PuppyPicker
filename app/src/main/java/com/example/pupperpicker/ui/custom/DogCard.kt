package com.example.pupperpicker.ui.custom

import android.content.Context
import android.graphics.Bitmap
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.lifecycle.MutableLiveData
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

    var _deleted: MutableLiveData<Boolean> = MutableLiveData(false)

    private var _view: View? = null
    private var _url: String = ""

    init {
        _view = LayoutInflater.from(context).inflate(R.layout.card_pupper, this, true)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (_view != null) {
            _view!!.layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT
            _view!!.layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT
        }
    }

    fun setupButton(viewModelScope: CoroutineScope) {
        // Button click
        if (_view != null) {
            _view!!.findViewById<ImageView>(R.id.favoriteButton)?.setOnClickListener {
                val dbHelper = (context.applicationContext as PuppyPickerApplication).dbHelper
                if (_url.isNotEmpty() && dbHelper != null) {
                    viewModelScope.launch {
                        withContext(Dispatchers.IO) {
                            if (dbHelper.doesURLExist(_url)) {
                                _deleted.postValue(dbHelper.deleteURL(_url))
                            }
                            else {
                                _deleted.postValue(!dbHelper.createURL(_url))
                            }
                        }
                    }
                }
            }
        }

        refreshButton(viewModelScope)
    }

    fun setURL(url: String) {
        _url = url
    }

    fun setImage(bitmap: Bitmap) {
        if (_view != null) {
            _view!!.findViewById<ImageView>(R.id.pupperImage)?.setImageBitmap(bitmap)
        }
    }

    fun setImage(id: Int) {
        if (_view != null) {
            _view!!.findViewById<ImageView>(R.id.pupperImage)?.setImageResource(id)
        }
    }

    fun refreshButton(viewModelScope: CoroutineScope) {
        val dbHelper = (context.applicationContext as PuppyPickerApplication).dbHelper
        if (dbHelper != null) {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    _deleted.postValue(!dbHelper.doesURLExist(_url))
                }
            }
        }
    }

    fun toggleButton(deleted: Boolean) {
        if (_view != null) {
            if (deleted) {
                _view!!.findViewById<ImageButton>(R.id.favoriteButton)?.setImageResource(R.drawable.ic_unfavorite_42)
            }
            else {
                _view!!.findViewById<ImageButton>(R.id.favoriteButton)?.setImageResource(R.drawable.ic_favorite_42)
            }
        }
    }
}