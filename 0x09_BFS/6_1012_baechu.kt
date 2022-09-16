import java.util.*

internal class Node6(var x: Int, var y: Int)

private val dx = intArrayOf(0, 1, 0, -1)
private val dy = intArrayOf(1, 0, -1, 0)

fun main() = with(System.`in`.bufferedReader()) {
    val t = readLine().toInt() // 테스트 케이스 개수
    for (i in 0 until t) {
        var st = StringTokenizer(readLine(), " ")
        val m = st.nextToken().toInt() // 가로
        val n = st.nextToken().toInt() // 세로
        val k = st.nextToken().toInt() // k 배추가 심어져있는 위치의 개수

        // 1. 배추 땅 만들기
        val land = Array(m) { IntArray(n) }
        for (j in 0 until k) {
            st = StringTokenizer(readLine(), " ")
            val x = st.nextToken().toInt()
            val y = st.nextToken().toInt()
            land[x][y] = 1
        }

        // 2. 배추가 이어져있는 땅 수 확인
        findStart(land, m, n)
    }
}

private fun findStart(land: Array<IntArray>, m: Int, n: Int) {
    val visited = Array(m) { BooleanArray(n) }
    var count = 0
    // 3. 시작 지점 찾기
    for (i in 0 until m) {
        for (j in 0 until n) {
            if (land[i][j] == 1 && !visited[i][j]) {
                // 4. dfs 시작
                dfs(land, m, n, i, j, visited)
                count++
            }
        }
    }
    // 6. 시작지점들 합 출력
    println(count)
}

private fun dfs(land: Array<IntArray>, m: Int, n: Int, i: Int, j: Int, visited: Array<BooleanArray>) {
    // 5. 배추 이어져있는 부분 찾기
    val q: Queue<Node6> = LinkedList()
    q.offer(Node6(i, j))
    while (!q.isEmpty()) {
        val cur = q.poll()
        for (dir in 0..3) {
            val nx = cur.x + dx[dir]
            val ny = cur.y + dy[dir]

            // 4방향 확인
            if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue
            // 0인 곳만 찾아야 하고 이미 들린 곳이면 안됨
            if (land[nx][ny] != 1 || visited[nx][ny]) continue
            q.offer(Node6(nx, ny))
            visited[nx][ny] = true
        }
    }
}
