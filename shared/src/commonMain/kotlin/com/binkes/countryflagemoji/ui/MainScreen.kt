package com.binkes.countryflagemoji.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.retain.retain
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.binkes.countryflagemoji.components.CountryPickerDialog
import com.binkes.countryflagemoji.utill.CountryAndCode
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable


@Serializable
enum class EmojiType{
    NativeText,
    SharedText
}

@Serializable
data class DialogInfo(
    val title: String,
    val emojiType: EmojiType
)


@Composable
fun MainScreen(
    onGoToNativeTextEmojiScreen: () -> Unit,
    onGoToSharedTextEmojiScreen: () -> Unit
) {

    val scope = rememberCoroutineScope()

    var dialogState by retain { mutableStateOf<DialogInfo?>(null) }

    val listOfCountry = remember { CountryAndCode.getAllCountries() }
    //val listOfCountry = remember { CountryAndCode.entries }


    // Dialogs
    if (dialogState != null) {

        dialogState?.let { itDialogState ->

            Dialog(
                onDismissRequest = {
                    scope.launch {
                        dialogState = null

                    }
                },
            ) {
                CountryPickerDialog(
                    listOfCountry = listOfCountry,
                    title = itDialogState.title,
                    emojiType = itDialogState.emojiType,
                    onClose = {
                        scope.launch {
                            dialogState = null
                        }
                    }
                )
            }
        }

    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        Button(
            onClick = {
                dialogState = DialogInfo(
                    title = "Native Text Emoji",
                    emojiType = EmojiType.NativeText
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Go To Native")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                dialogState = DialogInfo(
                    title = "Shared Text Emoji",
                    emojiType = EmojiType.SharedText
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Go To Shared")
        }


    }
}
