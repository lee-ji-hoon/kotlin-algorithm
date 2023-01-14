import java.util.*

private val dx = intArrayOf(1, 0, -1, 0)
private val dy = intArrayOf(0, 1, 0, -1)
private val dh = intArrayOf(1, -1)

private class TomatoNode(var h: Int, var x: Int, var y: Int)

fun main() = with(System.`in`.bufferedReader()) {
    var st = StringTokenizer(readLine(), " ")
    val M = st.nextToken().toInt() // 상자의 가로 칸
    val N = st.nextToken().toInt() // 상자의 세로 칸
    val H = st.nextToken().toInt() // H번 반복
    val box = Array(H) {
        Array(N) {
            IntArray(M)
        }
    }
    val visited = Array(H) {
        Array(N) {
            IntArray(M)
        }
    }
    val q: Queue<TomatoNode> = LinkedList()
    var count = 0
    for (i in 0 until H) {
        for (j in 0 until N) {
            st = StringTokenizer(readLine())
            for (k in 0 until M) {
                val num = st.nextToken().toInt()
                box[i][j][k] = num
                // 1. 토마토 시작 지점들 미리 큐에 넣기
                if (num == 1) {
                    q.offer(TomatoNode(i, j, k))
                    visited[i][j][k] = 1
                } else if (num == 0) {
                    count++
                }
            }
        }
    }
    // 2. 토마토가 이미 다 익어있는 경우
    if (count == 0) {
        println("0")
        return
    }
    // 3. 토마토들 익는 경우 확인
    bfs(M, N, H, q, visited, box, count)
}

private fun bfs(
    m: Int,
    n: Int,
    h: Int,
    q: Queue<TomatoNode>,
    visited: Array<Array<IntArray>>,
    box: Array<Array<IntArray>>,
    count: Int
) {
    var answer = 0
    var tomato = 0
    while (!q.isEmpty()) {
        val cur = q.poll()
        // 상하좌우 확인
        for (dis in 0..3) {
            val nx = cur.x + dx[dis]
            val ny = cur.y + dy[dis]
            val nh = cur.h
            if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue
            if (visited[nh][nx][ny] != 0 || box[nh][nx][ny] != 0) continue
            q.offer(TomatoNode(nh, nx, ny))
            visited[nh][nx][ny] = visited[nh][cur.x][cur.y] + 1
            answer = visited[nh][nx][ny]
            tomato++
        }
        // 위아래 확인
        for (dis in 0..1) {
            val nh = cur.h + dh[dis]
            val x = cur.x
            val y = cur.y
            if (nh < 0 || nh >= h) continue
            if (visited[nh][x][y] != 0 || box[nh][x][y] != 0) continue
            q.offer(TomatoNode(nh, x, y))
            visited[nh][x][y] = visited[cur.h][x][y] + 1
            answer = visited[nh][x][y]
            tomato++
        }
    }
    // 4. 토마토들 중에서 안익은게 있는지 확인
    if (count == tomato) {
        println(answer - 1)
    } else {
        println(-1)
    }
}
