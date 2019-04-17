package com.rk.prefix

import kotlin.math.max

class Prefix {
    fun evaluate(args: List<String>): Double {
        if (args.isEmpty()) {
            throw IllegalArgumentException("Invalid input, no arguments provided")
        }

        val input = args[0]

        if (input.isEmpty()) {
            throw IllegalArgumentException("Invalid expression, no arguments provided")
        }

        val expression: List<String> = input.trim().split("\\s+".toRegex()).reversed()
        val stack = mutableListOf<Double>()

        expression.forEach {
            when {
                it.isNumber() -> stack.add(it.toDouble())
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
                            "/" -> a / b
                            else -> throw TODO("Unsupported operation")
                        }
                    )
                }
            }
        }

        return stack.checkLast()
    }

    private fun MutableList<Double>.checkLast(): Double {
        return if (size == 1) {
            last()
        } else {
            throw IllegalArgumentException("No operator provided")
        }
    }

    private fun MutableList<Double>.pop(): Double = removeAt(lastIndex)

    private fun Double.isNegative(): Boolean = this < 0

    private fun String.isNumber(): Boolean = matches(regex = "-?\\d+(\\.\\d+)?".toRegex())
}
