import kotlin.math.max

fun part1(ids:List<Long>, ranges:List<ClosedRange<Long>>) {
    ids.filter { id -> ranges.firstOrNull { it.contains(id)} !=null }.size.println()
}

fun part2(ranges:List<ClosedRange<Long>>) {
    val processed = mutableSetOf<ClosedRange<Long>>()
    val original = ranges.sortedBy { it.start }
    var current: ClosedRange<Long> = original.first()

    original.forEach { range ->
        if (range.start <= current.endInclusive) {
            current = current.start..max(current.endInclusive, range.endInclusive)
        } else {
            processed.add(current)
            current = range
        }
    }
    processed.add(current)
    processed.sumOf { it.endInclusive - it.start + 1 }.println()
}

fun main() {
    val input = readInput("Day05")
    val ranges = mutableListOf<ClosedRange<Long>>()
    val ids = mutableListOf<Long>()
    var parsingRanges = true

    input.forEach { line ->
        if (parsingRanges) {
            if (line.isNotBlank()) {
                val rangeBeginEnd = line.split('-').map { it.toLong() }
                ranges.add(rangeBeginEnd.first()..rangeBeginEnd.last())
            } else {
                parsingRanges = false
                return@forEach
            }
        } else {
            ids.add(line.toLong())
        }
    }
    part1(ids, ranges)
    part2(ranges)
}


