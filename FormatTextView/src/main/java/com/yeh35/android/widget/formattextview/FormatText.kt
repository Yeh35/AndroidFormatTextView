package com.yeh35.android.widget.formattextview

import android.graphics.Typeface
import androidx.annotation.ColorInt
import androidx.annotation.DimenRes
import androidx.annotation.IntDef


/**
 * It is taken from {# android.graphics.Typeface.Style}
 */
@IntDef(value = [Typeface.NORMAL, Typeface.BOLD, Typeface.ITALIC, Typeface.BOLD_ITALIC])
@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
annotation class Style

class FormatText(
    val text: String,
    val size: Int = Int.MAX_VALUE,
    @ColorInt
    val color: Int = Int.MAX_VALUE,
    @Style
    val style: Int = Int.MAX_VALUE
) {

    val isSetStyle
        get() = style != Int.MAX_VALUE

    val isStyleNormal
        get() = style == NORMAL

    val isSetColor
        get() = color != Int.MAX_VALUE

    val isSetSize
        get() = size != Int.MAX_VALUE

    companion object {
        // Style
        const val NORMAL: Int = 0
        const val BOLD: Int = 1
        const val ITALIC: Int = 2
        const val BOLD_ITALIC: Int = 3
    }
}