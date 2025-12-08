
fun main() {

    fun part1(operations: List<String>, values: List<String>) {
        val valueList: List<List<Long>> = values.map { line ->
            line.split(" ").mapNotNull { it -> it.trim().takeIf { it.isNotBlank() }?.toLong() }
        }

        operations.foldIndexed(0L) { index: Int, acc: Long, operation: String ->
            acc + when (operation) {
                "+" -> {
                    valueList.sumOf { line -> line[index] }
                }

                "*" -> {
                    valueList.map { line -> line[index] }.fold(1L) { acc, e -> acc * e }
                }

                else -> error("unknown op $operation")
            }
        }.println()
    }

    fun part2(operations: List<String>, values: List<String>) {
        val rows = values.size
        val columns = values.maxBy { it.length }.length

        val problems = mutableListOf<List<Long>>()
        val problem = mutableListOf<Long>()
        for (c in columns - 1 downTo 0) {
            var colStr = ""

            for (r in 0..<rows) {
                if (values[r].length-1 < c)
                    continue

                colStr += values[r][c]
            }
            if (colStr.isNotBlank()) {
                problem.add(colStr.trim().toLong())
            } else {
                problems.add(problem.map { it })
                problem.clear()
            }
        }
        problems.add(problem)
        problems.println()

        operations.reversed().foldIndexed(0L) { index: Int, acc: Long, operation: String ->
            acc + when (operation) {
                "+" -> {
                    problems[index].sumOf { it }
                }

                "*" -> {
                    problems[index].fold(1L) { acc, e -> acc * e }
                }

                else -> error("unknown op $operation")
            }
        }.println()
    }

    val input = readInput("Day06")
    val operations = input.last().split(" ").mapNotNull { it -> it.trim().takeIf { it.isNotBlank() } }
    val values = input.dropLast(1)
    part1(operations, values)
    part2(operations, values)
}


