import java.util.*
private val dx = intArrayOf(1, 0, -1, 0)
private val dy = intArrayOf(0, 1, 0, -1)
internal class Node_1(var x: Int, var y: Int)

fun main() {
    val sc = Scanner(System.`in`)
    val N = sc.nextInt()
    val M = sc.nextInt()
    sc.nextLine()
    val miro = Array(N) { IntArray(M) }
    val dist = Array(N) { IntArray(M) }
    for (i in 0 until N) {
        val str = sc.nextLine()
        for (j in 0 until M) {
            miro[i][j] = str[j] - '0'
        }
    }
    bfs(N, M, miro, dist)
    println(dist[N - 1][M - 1])
}

private fun bfs(N: Int, M: Int, miro: Array<IntArray>, dist: Array<IntArray>) {
    val q: Queue<Node_1> = LinkedList()

    // 1. 시작 지점은 1으로 초기화하고 다음 Node들은 현재 Node의 dist[x][y] + 1 값으로 저장
    q.offer(Node_1(0, 0))
    dist[0][0] = 1
    while (!q.isEmpty()) {
        val cur = q.poll()
        for (dir in 0..3) {
            val nx = cur.x + dx[dir]
            val ny = cur.y + dy[dir]
            if (nx < 0 || nx >= N || ny < 0 || ny >= M || dist[nx][ny] != 0 || miro[nx][ny] != 1) continue

            // 2. 현재 칸에서 이동할 수 있는 칸의 값은 = 현재 dist[Node]의 값에 + 1
            dist[nx][ny] = dist[cur.x][cur.y] + 1
            q.offer(Node_1(nx, ny))
        }
    }
}
