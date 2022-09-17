import java.util.*

/**
 * DFS를 지훈이가 탈출 할 때 걸리는 시간과 불이 퍼지는 시간을 같이 구해준다.
 * 우선 불이 전파되는 시간을 구한 뒤 지훈이가 탈출할 때 해당 공간에 가는데 걸리는 시간이 불이 퍼지는 시간보다 낮으면 이동할 수 있게 한다.
 * 지훈[x][y] < 불[x][y] 인 경우에만 지나갈 수 있다는 의미이다!
 * 이동하면서 가장자리에 접하게 될 경우 탈출한 것으로 하고 빠져나간다
 */

private class Node_4(var x: Int, var y: Int)

private val dx = intArrayOf(1, 0, -1, 0)
private val dy = intArrayOf(0, 1, 0, -1)

fun main() = with(System.`in`.bufferedReader()) {
    val (R, C) = readLine().split(" ").map { it.toInt() }
    val miro = Array(R) {
        arrayOfNulls<String>(C)
    }
    val jihoon = Array(R) { IntArray(C) }
    val fire = Array(R) { IntArray(C) }
    val fireQ: Queue<Node_4> = LinkedList()
    val jihoonQ: Queue<Node_4> = LinkedList()
    for (i in 0 until R) {
        val str: String = readLine()
        for (j in 0 until C) {
            val input = str[j].toString()
            miro[i][j] = input
            when (input) {
                "J" -> {
                    jihoonQ.add(Node_4(i, j))
                    jihoon[i][j] = 1
                    fire[i][j] = -1
                }
                "F" -> {
                    fireQ.add(Node_4(i, j))
                    jihoon[i][j] = -1
                    fire[i][j] = 1
                }
                else -> {
                    jihoon[i][j] = -1
                    fire[i][j] = -1
                }
            }
        }
    }
    // 1. 불에 대한 dfs 먼저 구하기 -> 퍼지는 시간
    fireBFS(R, C, miro, fire, fireQ)
    // 2. 지훈이에 대한 dfs 구하기 -> 탈출 시간
    println(jihoonBFS(R, C, miro, fire, jihoon, jihoonQ))
}

private fun fireBFS(R: Int, C: Int, miro: Array<Array<String?>>, fire: Array<IntArray>, q: Queue<Node_4>) {
    while (!q.isEmpty()) {
        val cur = q.poll()
        for (dir in 0..3) {
            val nx = cur.x + dx[dir]
            val ny = cur.y + dy[dir]

            if (nx < 0 || nx >= R || ny < 0 || ny >= C) continue
            if (miro[nx][ny] == "#" || fire[nx][ny] != -1) continue

            fire[nx][ny] = fire[cur.x][cur.y] + 1
            q.offer(Node_4(nx, ny))
        }
    }
}

private fun jihoonBFS(R: Int, C: Int, miro: Array<Array<String?>>, fire: Array<IntArray>, jihoon: Array<IntArray>, q: Queue<Node_4>): String {
    while (!q.isEmpty()) {
        val cur = q.poll()
        // 3. 탈출 할 수 있는지 확인
        if (isPassable(cur.x, cur.y, R, C)) return jihoon[cur.x][cur.y].toString()

        // 4. 탈출을 못했으므로 계속 돌기
        for (dir in 0..3) {
            val nx = cur.x + dx[dir]
            val ny = cur.y + dy[dir]
            if (nx < 0 || nx >= R || ny < 0 || ny >= C) continue
            // 미로 벽인지 확인 + 이미 들린 노드인지 확인
            if (miro[nx][ny] == "#" || jihoon[nx][ny] != -1) continue
            // 불이 이미 퍼져있으면 안되고 + 아예 안퍼졌을 경우 전부다 -1이므로 -1인 경우도 제외 해야함
            if (fire[nx][ny] != -1 && fire[nx][ny] <= jihoon[cur.x][cur.y] + 1) continue
            jihoon[nx][ny] = jihoon[cur.x][cur.y] + 1
            q.offer(Node_4(nx, ny))
        }
    }
    return "IMPOSSIBLE"
}

private fun isPassable(nx: Int, ny: Int, r: Int, c: Int): Boolean {
    return nx + 1 == r || ny + 1 == c || nx == 0 || ny == 0
}