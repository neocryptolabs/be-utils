package com.neocryptolabs.core.util


fun makeTimeLinkMsg(url: String, content: String): String =
    "[${currentTime}][<${url}|Link>] $content"

fun makeTimeMsg(content: String): String =
    "[${currentTime}] $content"