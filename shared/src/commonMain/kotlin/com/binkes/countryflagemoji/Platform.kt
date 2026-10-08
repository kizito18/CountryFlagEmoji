package com.binkes.countryflagemoji

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform