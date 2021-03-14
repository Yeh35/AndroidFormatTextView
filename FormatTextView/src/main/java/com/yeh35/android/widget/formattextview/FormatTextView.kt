package com.yeh35.android.widget.formattextview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.AbsoluteSizeSpan
import android.text.style.StyleSpan
import android.util.AttributeSet
import android.view.View
import android.widget.TextView

/**
 * Interface definition for a callback to be invoked when a FormatText is clicked.
 */
typealias OnFormatTextClickListener = (v: View, text: FormatText) -> Unit

@SuppressLint("AppCompatCustomView")
class FormatTextView : TextView {

    private var onFormatTextClickListener: OnFormatTextClickListener? = null

    private var formatTextColor: Int
    private var formatTextSize: Float
    private var formatTextStyle: Int
    private var pressedFormatTextColor: Int
    private var pressedFormatTextBackgroundColor: Int

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?)
            : this(context, attrs, R.style.FormatTextView)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr) {
        movementMethod = LinkTouchMovementMethod()

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.FormatTextView,
            defStyleAttr,
            0
        ).apply {

            try {
                formatTextColor =
                    getInt(R.styleable.FormatTextView_formatTextColor, currentTextColor)
                formatTextSize = getDimension(R.styleable.FormatTextView_formatTextSize, textSize)
                formatTextStyle = getInteger(R.styleable.FormatTextView_formatTextStyle, 0)

                pressedFormatTextColor =
                    getInteger(R.styleable.FormatTextView_pressedFormatTextColor, formatTextColor)

                val defaultColor = if (background is ColorDrawable) {
                    (background as ColorDrawable).color
                } else {
                    Color.WHITE
                }
                pressedFormatTextBackgroundColor =
                    getInteger(
                        R.styleable.FormatTextView_pressedFormatTextBackgroundColor,
                        defaultColor
                    )
            } finally {
                recycle()
            }
        }
    }

    /**
     * It works like Java's {# String.format()} or C's {# printf()}.
     */
    @SuppressLint("ResourceType")
    fun setText(format: CharSequence, vararg args: FormatText) {
        val spannable = SpannableStringBuilder()
        var spannableSize = 0
        var argIndex = 0
        val s = format.toString()

        var i = 0
        while (i < s.length) {
            val nextIndex = format.indexOf("{}", i)

            if (argIndex >= args.size || nextIndex == -1) {
                val substring = s.substring(i, s.length - 1)
                spannable.insert(spannableSize, substring)
                spannableSize += substring.length
                break
            }

            // Insert the previous sentence
            val substring = s.substring(i, nextIndex)
            spannable.insert(spannableSize, substring)
            spannableSize += substring.length

            // Insert format statement
            val arg = args[argIndex++]
            spannable.insert(spannableSize, arg.text)

            // Setting style
            if (arg.isSetStyle) {
                spannable.setSpan(
                    StyleSpan(arg.style),
                    spannableSize,
                    spannableSize + arg.text.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            } else {
                spannable.setSpan(
                    StyleSpan(formatTextStyle),
                    spannableSize,
                    spannableSize + arg.text.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }

            // Setting size
            if (arg.isSetSize) {
                spannable.setSpan(
                    AbsoluteSizeSpan(arg.size, true),
                    spannableSize,
                    spannableSize + arg.text.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            } else {
                spannable.setSpan(
                    AbsoluteSizeSpan(formatTextSize.toInt(), false),
                    spannableSize,
                    spannableSize + arg.text.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }

            // set a click event
            val touchableSpan = object : TouchableSpan(
                this.formatTextColor,
                this.pressedFormatTextColor,
                this.pressedFormatTextBackgroundColor
            ) {
                override fun onClick(widget: View) {
                    onFormatTextClickListener?.invoke(widget, arg)
                }
            }

            spannable.setSpan(
                touchableSpan,
                spannableSize,
                spannableSize + arg.text.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            // Setting spannable position
            spannableSize += arg.text.length
            i = nextIndex + 2
        }

        text = spannable
    }

    fun setOnFormatTextClickListener(l: OnFormatTextClickListener) {
        this.onFormatTextClickListener = l
    }

    fun setFormatTextColor(color: Int) {
        this.formatTextColor = color
    }

    fun setFormatTextSize(size: Float) {
        this.formatTextSize = size
    }

    fun setFormatTextStyle(style: Int) {
        this.formatTextStyle = style
    }

    /**
     * We recommend setting the transparency to 0.
     * Because when clicked, the highlight automatically enters, but there is no way to hide it.
     */
    fun setPressedFormatTextColor(color: Int) {
        this.pressedFormatTextColor = color
    }
}