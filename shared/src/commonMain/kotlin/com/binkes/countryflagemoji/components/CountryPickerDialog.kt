package com.binkes.countryflagemoji.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.binkes.countryflagemoji.shared.EmojiFlag
import com.binkes.countryflagemoji.ui.EmojiType
import com.binkes.countryflagemoji.utill.CountryAndCode
import com.binkes.countryflagemoji.utill.getEmojiFlag
import kotlinx.coroutines.launch



@Composable
fun CountryPickerDialog(
    listOfCountry: List<CountryAndCode>,
    title: String,
    emojiType: EmojiType,
    onClose: () -> Unit,
) {


    val listOfCountryToShow by rememberUpdatedState(listOfCountry)

    val scope = rememberCoroutineScope()


    val listState = rememberLazyListState()
    var searchQuery by remember { mutableStateOf("") }

    val filteredCountries = remember(searchQuery) {
        if (searchQuery.isBlank()) {
            listOfCountryToShow
        } else {
            listOfCountryToShow.filter { country ->
                country.countryName.contains(searchQuery.trim(), ignoreCase = true) ||
                        country.countryCode.contains(searchQuery.trim(), ignoreCase = true)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(8.dp)
    ) {

        //  header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Select Country",
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = title,
                    color = Color.Black,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                )
            }

            Box(
                modifier = Modifier
                    .background(
                        color = Color.Black,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .clip(shape = RoundedCornerShape(10.dp))
                    .clickable {
                        scope.launch {
                            onClose()
                        }
                    }
                    .padding(vertical = 6.dp, horizontal = 10.dp),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = "Close",
                    color = Color.White,
                )

            }
        }


        Spacer(modifier = Modifier.height(8.dp))

        // country list
        if (filteredCountries.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No country found",
                    color = Color.Black.copy(alpha = 0.7f),
                    fontSize = 14.sp
                )
            }
        } else {
            LazyColumn(state = listState) {
                itemsIndexed(
                    items = filteredCountries,
                    key = { _, country -> country.countryIso }
                ) { index, country ->
                    ItemContent(
                        country = country,
                        emojiType = emojiType,
                        onCountrySelected = { selectedCountry ->
                            scope.launch {

                            }
                        }
                    )
                    if (index < filteredCountries.lastIndex) {
                        HorizontalDivider(
                            modifier = Modifier.padding(horizontal = 12.dp),
                            color = Color.LightGray.copy(alpha = 0.6f),
                            thickness = 0.5.dp
                        )
                    }
                }
            }
        }
    }
}


@Composable
private fun ItemContent(
    country: CountryAndCode,
    emojiType: EmojiType,
    onCountrySelected: (country: CountryAndCode) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(bounded = true)
            ) {
                onCountrySelected(country)
            }
            .padding(horizontal = 12.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        val emoji = getEmojiFlag(country)


        if (emojiType == EmojiType.NativeText){

            EmojiFlag(countryType = country, fontSize = 20f)

        }else {
            Text(
                text = emoji,
                fontSize = 20.sp
            )
        }

        Text(
            text = country.countryName,
            color = Color.Blue,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.weight(1f)
        )

        Text(
            text = country.countryCode,
            color = Color.Blue,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

