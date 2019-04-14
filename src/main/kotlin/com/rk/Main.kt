package com.rk

import com.rk.postfix.Postfix
import com.rk.prefix.Prefix

fun main(args: Array<String>) {
    val prefix = Prefix()
    val postfix = Postfix()

    println(
        when (args[0]) {
            "-n" -> prefix.evaluate(args.drop(1))
            "-r" -> postfix.evaluate(args.drop(1).toString())
            else -> "Invalid option"
        }
    )
}
