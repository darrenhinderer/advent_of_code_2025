fun main() {
    fun part1(input: List<String>): Int {
        var zeros = 0
        val safe = Safe(
            fullDialListener = { position -> if (position == 0) zeros++ },
            dialListener = { }
        )

        input.forEach {
            val turnLeft = it[0] == 'L'
            val number = it.substring(1).trim().toInt()
            safe.turnDial(left = turnLeft, amount = number)
        }

        return zeros
    }

    fun part2(input: List<String>): Int {
        var zeros = 0
        val safe = Safe(
            fullDialListener = { },
            dialListener = { position -> if (position == 0) zeros++ }
        )

        input.forEach {
            val turnLeft = it[0] == 'L'
            val number = it.substring(1).trim().toInt()
            safe.turnDial(left = turnLeft, amount = number)
        }

        return zeros
    }

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}

fun interface DialListener {
    fun onDialChanged(position:Int)
}

class Safe(
    val fullDialListener: DialListener,
    val dialListener: DialListener,
) {
    var position = 50
    val maxPosition = 99
    val minPosition = 0

    fun turnDial(left:Boolean, amount:Int) {
        if (left) {
            repeat(amount) { left() }
        } else {
            repeat(amount) { right() }
        }
        fullDialListener.onDialChanged(position)
    }

    fun left() {
        if (position == minPosition) position = maxPosition else position--
        dialListener.onDialChanged(position)
    }
    fun right() {
        if (position == maxPosition) position = minPosition else position++
        dialListener.onDialChanged(position)
    }
}
