package com.rk

import com.rk.prefix.Prefix

fun main(args: Array<String>) {
    val prefix = Prefix()

    println(when(args[0]) {
        "-n" -> prefix.evaluate(args.drop(1))
        "-r" -> TODO("Postfix not implemented")
        else -> "Invalid option"
    })
}
