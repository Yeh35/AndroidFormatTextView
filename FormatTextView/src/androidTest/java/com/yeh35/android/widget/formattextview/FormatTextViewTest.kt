package com.yeh35.android.widget.formattextview

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FormatTextViewTest {

    @Test
    fun testSetText() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("com.yeh35.android.widget.formattextview.test", appContext.packageName)

        val formatTextView = FormatTextView(appContext)
        val text1 = FormatText("hi",10, R.color.design_default_color_on_primary, FormatText.BOLD)
        val text2 = FormatText("bye",20, style = FormatText.BOLD)

        formatTextView.setText("{}ABCD{}EFG", text1, text2)
    }
}