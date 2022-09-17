import java.util.*

private val dx = intArrayOf(1, 0, -1, 0)
private val dy = intArrayOf(0, 1, 0, -1)

private data class Node3(var x: Int, var y: Int)

fun main() = with(System.`in`.bufferedReader()) {
    val (M, N) = readLine().split(" ").map { it.toInt() }

    val tomatoList = Array(N) { IntArray(M) } // 입력 받은 토마토
    val dist = Array(N) { IntArray(M) {-1} } // 익었는지 확인하기 위한 배열
    val queue: Queue<Node3> = LinkedList()

    for (i in 0 until N) {
        val token = StringTokenizer(readLine())
        for (j in 0 until M) {
            tomatoList[i][j] = token.nextToken().toInt()
            if (tomatoList[i][j] == 1) {
                queue.offer(Node3(i, j))
                dist[i][j] = 0 // 이미 익었다는 의미
            }
        }
    }
    close()
    solution(N, M, tomatoList, dist, queue)
}


private fun solution(N: Int, M: Int, tomato: Array<IntArray>, dist: Array<IntArray>, q: Queue<Node3>) {
    var max = 0
    while (q.isNotEmpty()) {
        val cur = q.poll()
        for (i in 0..3) {
            val nx = cur.x + dx[i]
            val ny = cur.y + dy[i]

            if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue
            // 익지 않은 토마토를 찾아야 하므로 tomato 배열의 값이 0이 아니거나 dist 배열의 값이 -1(익지 않은 토마토)이 아닌 경우 패스
            if (dist[nx][ny] != -1 || tomato[nx][ny] != 0) continue
            dist[nx][ny] = dist[cur.x][cur.y] + 1
            q.offer(Node3(nx, ny))
            max = max.coerceAtLeast(dist[nx][ny])
        }
    }
    // 3. 익지 않은 토마토가 있는지 확인
    if (isCheckedTomato(N, M, dist, tomato).not()) {
        println(-1)
    } else {
        println(max)
    }
}

private fun isCheckedTomato(N: Int, M: Int, dist: Array<IntArray>, tomato: Array<IntArray>): Boolean {
    for (i in 0 until N) {
        for (j in 0 until M) {  // dist 배열(익는데 걸리는 시간 값이 -1(초기화 상태) 면서
            if (dist[i][j] == -1 && tomato[i][j] == 0) { // tomato 배열에서 == 0(익지 않은) 경우 익지 않은 토마토 존재
                return false
            }
        }
    }
    return true
}
