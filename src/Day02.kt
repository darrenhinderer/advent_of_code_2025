fun main() {
    fun part1(input: List<String>): Long =
        input.first()
            .split(",")
            .flatMap { range ->
                val (start, end) = range.split("-").map { it.toLong() }
                (start..end).mapNotNull { number ->
                    val numberStr = number.toString()
                    val halfLength = numberStr.length / 2
                    number.toString().takeIf {
                        numberStr.length % 2 == 0 && numberStr.take(halfLength) == numberStr.drop(halfLength)
                    }
                }
            }
            .sumOf { it.toLong() }

    fun part2(input: List<String>): Long =
        input.first()
            .split(",")
            .flatMap { range ->
                val (start, end) = range.split("-").map { it.toLong() }
                (start..end).mapNotNull { number ->
                    val numberStr = number.toString()
                    if (doesDigitStringHaveRepeatingPattern(numberStr)) {
                        numberStr
                    } else {
                        null
                    }
                }
            }
            .sumOf { it.toLong() }

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}

fun doesDigitStringHaveRepeatingPattern(input:String): Boolean {
    if (input.length == 1) return false
    val inputLength = input.length
    return (1..< inputLength).any { patternLength ->
        val pattern = input.take(patternLength)
        inputLength % patternLength == 0 && input == pattern.repeat(inputLength / pattern.length)
    }
}
