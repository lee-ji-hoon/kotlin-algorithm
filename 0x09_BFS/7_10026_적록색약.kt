import java.util.*

class Node(
    val x: Int,
    val y: Int
)

private val dx = intArrayOf(1, 0, -1, 0)
private val dy = intArrayOf(0, 1, 0, -1)
private val sb = StringBuilder()

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()

    val colorSpace = List(n) {
        readLine().toCharArray()
    }

    // 적록 색약이 아닌 사람이 봤을 때의 조합 -> R G B
    findColor(n, colorSpace)
    // 적록 색약인 사람이 봤을 경우로 만들기 위해서 배열 값 변경 -> RG, B
    setColorInit(n, colorSpace)
    // 적록 색약인 사람이 봤을 때의 조합 구하기
    findColor(n, colorSpace)
    println(sb)
}

fun findColor(n: Int, colorSpace: List<CharArray>) {
    val visited = Array(n) { BooleanArray(n) }
    var count = 0
    for (i in 0 until n) {
        for (j in 0 until n) {
            if (visited[i][j].not()) {
                dfs(n, i, j, colorSpace, visited)
                count++
            }
        }
    }
    sb.append(count).append(" ")
}

private fun dfs(n: Int, i: Int, j: Int, colorSpace: List<CharArray>, visited: Array<BooleanArray>) {
    val q: Queue<Node> = LinkedList()
    visited[i][j] = true
    q.offer(Node(i, j))
    val color = colorSpace[i][j]

    while (!q.isEmpty()) {
        val cur = q.poll()
        for (dir in 0..3) {
            val nx = cur.x + dx[dir]
            val ny = cur.y + dy[dir]

            if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue
            if (visited[nx][ny] || colorSpace[nx][ny] != color) continue

            q.offer(Node(nx, ny))
            visited[nx][ny] = true
        }
    }
}

fun setColorInit(n: Int, colorSpace: List<CharArray>) {
    for (i in 0 until n) {
        for (j in 0 until n) {
            if (colorSpace[i][j] == 'R') {
                colorSpace[i][j] = 'G'
            }
        }
    }
}
