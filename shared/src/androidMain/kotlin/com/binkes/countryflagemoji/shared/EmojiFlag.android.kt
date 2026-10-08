package com.binkes.countryflagemoji.shared

import androidx.compose.runtime.Composable
import com.binkes.countryflagemoji.utill.CountryAndCode
import androidx.compose.material3.Text
import androidx.compose.ui.unit.sp
import com.binkes.countryflagemoji.utill.getEmojiFlag


@Composable
actual fun EmojiFlag(
    countryType: CountryAndCode,
    fontSize: Float
) {


    Text(
        text = getEmojiFlag(countryType),
        fontSize = fontSize.sp
    )

}