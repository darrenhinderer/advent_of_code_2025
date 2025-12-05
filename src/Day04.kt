
fun part1(input:List<List<Int>>) {
    val rolls = findPaperRolls(input)
    rolls.map { countAdjacent(rolls, it) }.count { it < 4 }.println()
}

fun part2(input:List<List<Int>>) {
    var current = findPaperRolls(input)
    var totalRemoved = 0
    while (true) {
        val next = current.filter { countAdjacent(current, it) >= 4 }.toSet()
        if (next.size == current.size) break

        totalRemoved += current.size - next.size
        current = next
    }
    totalRemoved.println()
}


fun main() {

    val input = readInput("Day04").map { it.toList().map { c ->
        when (c) {
            '@' -> 1
            '.' -> 0
            else -> -1
        }
    } }

    part1(input)
    part2(input)
}


data class Coords(val x: Int, val y: Int)

fun countAdjacent(rolls:Set<Coords>, roll:Coords ): Int {
    var count= 0
    count += if (rolls.contains(Coords(roll.x, roll.y-1))) 1 else 0
    count += if (rolls.contains(Coords(roll.x+1, roll.y-1))) 1 else 0
    count += if (rolls.contains(Coords(roll.x+1, roll.y))) 1 else 0
    count += if (rolls.contains(Coords(roll.x+1, roll.y+1))) 1 else 0
    count += if (rolls.contains(Coords(roll.x, roll.y+1))) 1 else 0
    count += if (rolls.contains(Coords(roll.x-1, roll.y+1))) 1 else 0
    count += if (rolls.contains(Coords(roll.x-1, roll.y))) 1 else 0
    count += if (rolls.contains(Coords(roll.x-1, roll.y-1))) 1 else 0
    return count
}

fun findPaperRolls(input: List<List<Int>>): Set<Coords> {
    return input.flatMapIndexed { row, rowValues ->
        rowValues.mapIndexedNotNull { column, columnValue ->
            if (columnValue == 1) Coords(row,column) else null
        }
    }.toSet()
}

