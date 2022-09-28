package com.ourbalance.data.ext

fun Long.getRatio(other: Long): Int {
    return (this.toDouble().div(other) * 100).toInt()
}
