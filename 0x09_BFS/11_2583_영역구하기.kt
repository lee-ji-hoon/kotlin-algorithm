import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (m, n, k) = readLine().split(" ").map { it.toInt() }
    val papers = Array(m) { IntArray(n) }

    repeat(k) {
        val (x1, y1, x2, y2) = readLine().split(" ").map { it.toInt() }
        for (i in x1 until x2) {
            for (j in y1 until y2) {
                papers[j][i] = 1
            }
        }
    }

    val areas = mutableListOf<Int>()

    for (i in 0 until m) {
        for (j in 0 until n) {
            if (papers[i][j] == 0) {
                areas.add(findArea(papers, i, j, m, n))
            }
        }
    }

    val sb = StringBuilder()
    areas.sort()
    areas.forEach { sb.append("$it ") }
    println(areas.size)
    println(sb)
}

private val dx = intArrayOf(1, 0, -1, 0)
private val dy = intArrayOf(0, 1, 0, -1)

fun findArea(paper: Array<IntArray>, startY: Int, startX: Int, m: Int, n: Int): Int {
    val queue = LinkedList<Pair<Int, Int>>()
    queue.add(Pair(startY, startX))

    paper[startY][startX] = 1
    var area = 1

    while (queue.isNotEmpty()) {
        val (y, x) = queue.poll()

        for (i in 0 until 4) {
            val ny = y + dy[i]
            val nx = x + dx[i]
            if (ny in 0 until m && nx in 0 until n && paper[ny][nx] == 0) {
                paper[ny][nx] = 1
                queue.add(Pair(ny, nx))
                area++
            }
        }
    }
    return area
}
