import java.io.StreamTokenizer
import java.util.*


private val dx = intArrayOf(-1, 0, 1, 0)
private val dy = intArrayOf(0, 1, 0, -1)

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val m = input()
    val a = Array(n) { IntArray(m) { input() } }

    // 브루트 포스를 수행하여 모든 가능한 경우를 탐색합니다.
    // 브루트 포스에서는 벽을 3개 세우는 모든 경우를 찾아야 하므로 4중 for문을 사용합니다.
    // 이때, 벽을 세울 위치를 찾을 때는 벽이 아닌 위치에서만 찾도록 합니다. 벽을 세우면 `a` 배열의 값을 1로 변경하고 BFS 탐색을 수행합니다.
    // BFS 탐색이 끝나면 안전 영역 크기를 계산하여 `ans` 변수와 비교하여 최대값을 구합니다. 이후, 벽을 다시 제거하고 다음 경우를 탐색합니다.
    // 모든 경우를 탐색하면 최대 안전 영역 크기를 출력합니다.
    var answer = 0
    for (x1 in 0 until n) {
        for (y1 in 0 until m) {
            if (a[x1][y1] != 0) continue // 벽이 아닌 곳에서 시작
            for (x2 in 0 until n) {
                for (y2 in 0 until m) {
                    if (a[x2][y2] != 0) continue // 벽이 아닌 곳에서 시작
                    for (x3 in 0 until n) {
                        for (y3 in 0 until m) {
                            if (a[x3][y3] != 0) continue // 벽이 아닌 곳에서 시작
                            if (x1 == x2 && y1 == y2) continue
                            if (x1 == x3 && y1 == y3) continue
                            if (x2 == x3 && y2 == y3) continue
                            a[x1][y1] = 1
                            a[x2][y2] = 1
                            a[x3][y3] = 1
                            val cur = bfs(a, n, m)
                            if (cur > answer) {
                                answer = cur
                            }
                            a[x1][y1] = 0
                            a[x2][y2] = 0
                            a[x3][y3] = 0
                        }
                    }
                }
            }
        }
    }
    println(answer)
}

// `bfs()` 함수는 BFS 탐색을 수행하고 안전 영역 크기를 반환하는 함수입니다. `bfs()` 함수를 호출할 때는 현재 상태의 지도인 `a` 배열을 인자로 넘겨줍니다.
fun bfs(a: Array<IntArray>, n: Int, m: Int): Int {
    // `b` 배열은 바이러스가 퍼진 영역과 벽으로 막혀 있는 영역을 표시하는데 사용됩니다.
    val b = Array(n) { IntArray(m) } // 바이러스가 퍼진 영역과 벽으로 막혀 있는 영역을 표시할 배열
    val q: Queue<Pair<Int, Int>> = LinkedList() // BFS 탐색을 위한 큐
    for (i in 0 until n) {
        for (j in 0 until m) {
            // `a` 배열의 초기값을 `b` 배열에 대입하는 이유는 `a` 배열은 문제에서 주어진 지도이므로 BFS 탐색 중에 수정하면 안 되기 때문입니다.
            b[i][j] = a[i][j] // 초기값 설정
            if (b[i][j] == 2) {
                q.add(Pair(i, j)) // 바이러스가 있는 위치를 큐에 추가
            }
        }
    }
    while (q.isNotEmpty()) {
        val (x, y) = q.remove()
        for (k in 0 until 4) {
            val nx = x + dx[k]
            val ny = y + dy[k]
            if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue
            if (b[nx][ny] == 0) { // 이미 방문한 곳이거나 벽인지 확인
                b[nx][ny] = 2
                q.add(Pair(nx, ny))
            }
        }
    }
    var cnt = 0
    for (i in 0 until n) {
        for (j in 0 until m) {
            if (b[i][j] == 0) { // 바이러스가 퍼지지 않은 곳은 안전 영역
                cnt++
            }
        }
    }
    return cnt
}