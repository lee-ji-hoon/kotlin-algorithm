import java.util.*

/**
 * 1인 값 탐색
 * 방문하지 않았고 1이면 DFS 실행
 * 현재 방문한 곳 체크하고 4방향 + 값이 1이라면 DFS 계속 실행
 * DFS의 반환 값은 방문한 개수이다.
 */
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val maps = Array(n) { IntArray(n) }
    val visitied = Array(n) { BooleanArray(n) }

    for (i in 0 until n) {
        val temp = readLine()
        for (j in 0 until n) {
            maps[i][j] = temp[j] - '0'
        }
    }

    val answer = PriorityQueue<Int>()

    for (i in 0 until n) {
        for (j in 0 until n) {
            if (maps[i][j] == 1 && visitied[i][j].not()) {
                answer.add(findApart(i, j, maps, visitied, n))
            }
        }
    }
    val sb = StringBuilder().appendLine(answer.size)
    while (answer.isNotEmpty()) {
        sb.appendLine(answer.poll())
    }
    println(sb)
}

private val dx = intArrayOf(1, 0, -1, 0)
private val dy = intArrayOf(0, 1, 0, -1)

fun findApart(x: Int, y: Int, maps: Array<IntArray>, visitied: Array<BooleanArray>, n: Int): Int {
    val queue = LinkedList<Pair<Int, Int>>()
    queue.add(Pair(x, y))
    var count = 1
    visitied[x][y] = true

    while (queue.isNotEmpty()) {
        val (x, y) = queue.poll()
        for (i in 0 until 4) {
            val nx = x + dx[i]
            val ny = y + dy[i]

            if (nx >= 0 && ny >= 0 && nx < n && ny < n && maps[nx][ny] == 1 && visitied[nx][ny].not()) {
                queue.add(Pair(nx, ny))
                count++
                visitied[nx][ny] = true
            }
        }
    }
    return count
}
