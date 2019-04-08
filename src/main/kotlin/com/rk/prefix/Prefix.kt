package com.rk.prefix

import kotlin.math.max

fun main(args: Array<String>) {
    val input = args[0]
    val expression: List<String> = input.trim().split("\\s+".toRegex()).reversed()
    val stack = mutableListOf<Long>()

    expression.forEach {
        when {
            it.isNumber() -> stack.add(it.toLong())
            stack.size < 2 -> return@forEach
            else -> {

                val a = stack.pop()
                val b = stack.pop()

                stack.add(
                    when (it) {
                        ">" -> max(a, max(b, stack.pop()))
                        "@" -> {
                            when (a.isNegative()) {
                                false -> b
                                true -> stack.pop()
                            }
                        }
                        "+" -> a + b
                        "-" -> a - b
                        "*" -> a * b
                        else -> throw TODO()
                    }
                )
            }
        }
    }

    println("\n\tSolution: ${stack.last()}\n")
}


private fun MutableList<Long>.pop(): Long {
    return removeAt(this.lastIndex)
}

private fun Long.isNegative(): Boolean {
    return this < 0
}

private fun String.isNumber(): Boolean {
    return matches(regex = "-?\\d+(\\.\\d+)?".toRegex())
}
