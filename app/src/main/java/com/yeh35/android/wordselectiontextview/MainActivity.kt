package com.yeh35.android.wordselectiontextview

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.yeh35.android.widget.formattextview.FormatText
import com.yeh35.android.widget.formattextview.FormatTextView


class MainActivity : AppCompatActivity() {

    @SuppressLint("ClickableViewAccessibility", "ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val format = "{}....{}....ABC"
        val text1 = FormatText("text1",20, R.color.teal_200, FormatText.BOLD_ITALIC)
        val text2 = FormatText("text2",40)
        val text3 = FormatText("text3")
        val text4 = FormatText("text4")

        val formatText: FormatTextView = findViewById(R.id.text)
        val formatText2: FormatTextView = findViewById(R.id.text2)
        val formatText3: FormatTextView = findViewById(R.id.text3)
        val formatText4: FormatTextView = findViewById(R.id.text4)

        formatText.setText(format, text1, text2)
        formatText.setOnFormatTextClickListener { _, text ->
            Log.d(TAG, text.text)
        }

        formatText2.setText(format, text3, text4)
        formatText2.setOnFormatTextClickListener { _, text ->
            Log.d(TAG, text.text)
        }

        formatText3.setText(format, text3, text4)
        formatText3.setOnFormatTextClickListener { _, text ->
            Log.d(TAG, text.text)
        }

        formatText4.setText(format, text3, text4)
        formatText4.setOnFormatTextClickListener { _, text ->
            Log.d(TAG, text.text)
        }
    }

    companion object {
        private const val TAG = "TOUCH WORD"
    }
}