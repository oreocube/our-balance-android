package com.ourbalance.data.ext

fun Long.getRatio(other: Long): Int {
    return (this.toDouble().div(other) * 100).toInt()
}

fun String.toDateString(): String {
    return "${substring(0, 4)}.${substring(4, 6)}.${substring(6, 8)}"
}
