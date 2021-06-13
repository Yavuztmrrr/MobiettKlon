package com.h5190041.mobiett.util

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.widget.ImageView

class TextWatcherUtil (
    private val afterChanged: (Editable?) -> Unit,
    private val beforeChanged: (CharSequence?, Int, Int, Int) -> Unit,
    private val onChanged: (CharSequence?, Int, Int, Int) -> Unit
) : TextWatcher {
    override fun afterTextChanged(s: Editable?) {
            afterChanged(s)
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        beforeChanged(s, start, count, after)
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        onChanged(s, start, before, count)
    }

}