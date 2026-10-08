package com.binkes.countryflagemoji.navigation


sealed class NavScreens(val route: String) {

    object Main : NavScreens("main_screen")
    /*
    object NativeTextEmoji : NavScreens("native_text_emoji_screens")
    object SharedTextEmoji : NavScreens("share_text_emoji_screens")

     */
}

