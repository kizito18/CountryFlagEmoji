package com.binkes.countryflagemoji.shared

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.UIKitInteropProperties
import androidx.compose.ui.viewinterop.UIKitView
import com.binkes.countryflagemoji.utill.CountryAndCode
import com.binkes.countryflagemoji.utill.getEmojiFlag
import platform.UIKit.NSTextAlignmentCenter
import platform.UIKit.UIColor
import platform.UIKit.UIFont
import platform.UIKit.UILabel


/*
@Composable
actual fun EmojiFlag(
    countryType: CountryAndCode,
    fontSize: Float
) {

    UIKitView(
        factory = {
            UILabel().apply {
                text = getEmojiFlag(countryType)
                font = platform.UIKit.UIFont.systemFontOfSize(fontSize.toDouble())
            }
        },
        update = { it.text = getEmojiFlag(countryType) }
    )

}

 */



// iosMain
@Composable
actual fun EmojiFlag(countryType: CountryAndCode, fontSize: Float) {
    val boxSize = (fontSize * 1.4f).dp
    UIKitView(
        modifier = Modifier.size(boxSize),
        factory = {
            UILabel().apply {
                text = getEmojiFlag(countryType)
                font = UIFont.systemFontOfSize(fontSize.toDouble())
                textAlignment = NSTextAlignmentCenter
                clipsToBounds = true
                backgroundColor = UIColor.clearColor
            }
        },
        update = { it.text = getEmojiFlag(countryType) },
        properties = UIKitInteropProperties(
            isInteractive = false,
            isNativeAccessibilityEnabled = false
        )
    )
}
