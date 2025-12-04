fun main() {

    fun digitsToInt(input: List<Int>) = input.joinToString(separator = "").toLong()

    fun processRow(row: List<Int>):Long {
        val firstDigit = row.dropLast(1).maxOf { it }
        val firstDigitPosition = row.indexOf(firstDigit)
        val secondDigit = row.drop(firstDigitPosition + 1).maxOf { it }
        return digitsToInt(listOf(firstDigit, secondDigit))
    }

    fun processRowPart2(row: List<Int>, numberOfBatteries: Int): Long {
        val digits = mutableListOf<Int>()
        var currentPosition = 0
        repeat(numberOfBatteries) {
            val remainingBatteries = numberOfBatteries - digits.size - 1
            val shorterRow = row.drop(currentPosition).dropLast(remainingBatteries.coerceAtLeast(0))
            val digit = shorterRow.maxOf { it }
            currentPosition += shorterRow.indexOf(digit) + 1
            digits.add(digit)
        }

        return digitsToInt(digits)
    }

    val input = readInput("Day03")
    val rows = input.map { it.toList().map { c -> c.digitToInt() } }
    val result1 = rows.sumOf { processRow(it) }
    result1.println()
    val result2 = rows.sumOf { processRowPart2(it, 12) }
    result2.println()
}


