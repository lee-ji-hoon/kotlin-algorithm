
import java.util.*

class Pos(var z: Int, var y: Int, var x: Int, var dist: Int)

private const val INF = 987654321
private const val N = 5
private var MIN = INF
private var visitFloor = BooleanArray(N)

private var floorList: MutableList<Int> = ArrayList()
private var dz = intArrayOf(-1, 1, 0, 0, 0, 0)
private var dy = intArrayOf(0, 0, -1, 1, 0, 0)
private var dx = intArrayOf(0, 0, 0, 0, 1, -1)

private var map = Array(N) {
    Array(N) {
        IntArray(N)
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    var st: StringTokenizer
    for (i in 0 until N) {
        for (j in 0 until N) {
            st = StringTokenizer(readLine())
            for (k in 0 until N) {
                map[i][j][k] = st.nextToken().toInt()
            }
        }
    }
    searchMinPath(0)
    if (MIN == INF) {
        println(-1)
    } else {
        println(MIN)
    }
}

// 각 판마다 몇 도 회전하는지 모든 경우의 수를 탐색한다.
// 현재 판을 90도 회전하고 다음판에 대해 재귀 호출
// 마지막판에 대한 결정이 끝나면 판을 쌓는 함수를 호출
private fun searchMinPath(cnt: Int) {
    if (cnt == 5) {
        stackFloor(0)
        return
    }
    for (i in 0..3) {
        rotate(cnt)
        searchMinPath(cnt + 1)
    }
}

private fun rotate(idx: Int) {
    val temp = Array(N) { IntArray(N) }
    for (y in 0 until N) {
        for (x in 0 until N) {
            temp[y][x] = map[idx][N - x - 1][y]
        }
    }
    System.arraycopy(temp, 0, map[idx], 0, temp.size)
}

// 각 판마다 몇 번째에 쌓을지 모든 경우의 수를 탐색
// 마지막판에 대해서만 거리탐색 -> bfs로 해결
private fun stackFloor(idx: Int) {
    if (idx == N) {
        bfs()
        return
    }
    for (i in 0 until N) {
        if (visitFloor[i]) continue
        visitFloor[i] = true
        floorList.add(i)
        stackFloor(idx + 1)
        floorList.removeAt(floorList.size - 1)
        visitFloor[i] = false
    }
}

// (0, 0, 0) -> (N-1, N-1, N-1) 좌표가 둘 다 1인지(사람이 갈 수 있는 값) 확인
// (0, 0, 0)에서 시작하여 (N-1, N-1, N-1) 까지 몇 번 이동 해야하는지 bfs 탐색
private fun bfs() {
    val updateMap = Array(N) {
        Array(N) {
            IntArray(N)
        }
    }
    val q: Queue<Pos> = LinkedList()
    val visit = Array(N) {
        Array(N) {
            BooleanArray(N)
        }
    }

    for (i in 0 until N) {
        System.arraycopy(map[floorList[i]], 0, updateMap[i], 0, map[floorList[i]].size)
    }

    if (updateMap[0][0][0] == 0 || updateMap[4][4][4] == 0) return

    visit[0][0][0] = true
    q.add(Pos(0, 0, 0, 0))
    while (q.isNotEmpty()) {
        val now = q.poll()
        val z = now.z
        val y = now.y
        val x = now.x
        val dist = now.dist
        if (z == 4 && y == 4 && x == 4) {
            if (MIN > dist) {
                MIN = dist
            }
            continue
        }
        for (d in 0..5) {
            val nz = z + dz[d]
            val ny = y + dy[d]
            val nx = x + dx[d]
            if (nz < 0 || nz >= N || ny < 0 || ny >= N || nx < 0 || nx >= N) continue
            if (visit[nz][ny][nx] || updateMap[nz][ny][nx] == 0) continue
            visit[nz][ny][nx] = true
            q.add(Pos(nz, ny, nx, dist + 1))
        }
    }
}
