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
                            else -> throw TODO()
                        }
                    )
                }
            }
        }

        return stack.last()
    }


    private fun MutableList<Double>.pop(): Double {
        return removeAt(this.lastIndex)
    }

    private fun Double.isNegative(): Boolean {
        return this < 0
    }

    private fun String.isNumber(): Boolean {
        return matches(regex = "-?\\d+(\\.\\d+)?".toRegex())
    }
}
