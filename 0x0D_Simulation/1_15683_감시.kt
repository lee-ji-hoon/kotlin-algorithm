import java.io.StreamTokenizer
import java.util.*

/**
 * 90도로 돌렸을 때 각 CCTV는 아래와 같은 경우의 수가 생긴다.
 * 1. 상/하/좌/우                 -> 4개
 * 2. 상하/좌우                   -> 2개
 * 3. 상우/하우/하좌/좌상         -> 4개
 * 4. 상좌우/상하우/하좌우/상하좌 -> 4개
 * 5. 상하좌우                    -> 1개
 *
 * 1번과 2번 CCTV를 갖고 있다면
 * 1번(4) * 2번(2) = 8개의 경우의 수가 나온다.
 * 이 8가지를 바탕으로 감시를 하고 최소 사각지대를 찾으면 된다.
 */

class CCTV(
    val num: Int,
    val x: Int,
    val y: Int
)

private val dx = intArrayOf(-1, 0, 1, 0) // 상 우 하 좌 시계방향 순서
private val dy = intArrayOf(0, 1, 0, -1)
private var answer = Int.MAX_VALUE

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = input()
    val M = input()

    val map = Array(N) { IntArray(M) }
    val cctvList = ArrayList<CCTV>()

    for (i in 0 until N) {
        for (j in 0 until M) {
            map[i][j] = input()
            if (map[i][j] != 0 && map[i][j] != 6) {
                cctvList.add(CCTV(map[i][j], i, j))
            }
        }
    }

    val permutationArray = IntArray(cctvList.size) // 순열을 담을 배열

    permutation(0, cctvList.size, N, M, map, cctvList, permutationArray)

    System.out.println(answer)
}

fun permutation(depth: Int, r: Int, N: Int, M: Int, map: Array<IntArray>, cctvList: ArrayList<CCTV>, output: IntArray) {
    if (depth == r) {
        // map 배열 copy하기
        val copyMap = Array(N) { IntArray(M) }

        for (i in 0 until map.size) {
            System.arraycopy(map[i], 0, copyMap[i], 0, map[i].size)
        }

        // cctv번호와 순열로 뽑혀진 방향에 맞는 상하좌우 방향 설정
        for (i in 0 until cctvList.size) {
            findDirection(cctvList[i], output[i], N, M, copyMap)
        }

        // 사각 지대 구하기
        findBlind(copyMap, N, M)
        return
    }

    for (i in 0..3) {
        output[depth] = i
        permutation(depth + 1, r, N, M, map, cctvList, output)
    }
}

// 각 cctv 번호와 순열로 뽑혀진 방향에 맞게 감시
fun findDirection(cctv: CCTV, d: Int, N: Int, M: Int, copyMap: Array<IntArray>) {
    val cctvNum = cctv.num
    if (cctvNum == 1) {
        if (d == 0) { // 상
            watch(cctv, 0, N, M, copyMap)
        } else if (d == 1) { // 우
            watch(cctv, 1, N, M, copyMap)
        } else if (d == 2) { // 하
            watch(cctv, 2, N, M, copyMap)
        } else if (d == 3) { // 좌
            watch(cctv, 3, N, M, copyMap)
        }
    } else if (cctvNum == 2) {
        if (d == 0 || d == 2) {
            // 상하
            watch(cctv, 0, N, M, copyMap)
            watch(cctv, 2, N, M, copyMap)
        } else {
            // 좌우
            watch(cctv, 1, N, M, copyMap)
            watch(cctv, 3, N, M, copyMap)
        }
    } else if (cctvNum == 3) {
        if (d == 0) {
            // 상우
            watch(cctv, 0, N, M, copyMap)
            watch(cctv, 1, N, M, copyMap)
        } else if (d == 1) {
            // 우하
            watch(cctv, 1, N, M, copyMap)
            watch(cctv, 2, N, M, copyMap)
        } else if (d == 2) {
            // 하좌
            watch(cctv, 2, N, M, copyMap)
            watch(cctv, 3, N, M, copyMap)
        } else if (d == 3) {
            // 좌상
            watch(cctv, 0, N, M, copyMap)
            watch(cctv, 3, N, M, copyMap)
        }
    } else if (cctvNum == 4) {
        if (d == 0) {
            // 좌상우
            watch(cctv, 0, N, M, copyMap)
            watch(cctv, 1, N, M, copyMap)
            watch(cctv, 3, N, M, copyMap)
        } else if (d == 1) {
            // 상우하
            watch(cctv, 0, N, M, copyMap)
            watch(cctv, 1, N, M, copyMap)
            watch(cctv, 2, N, M, copyMap)
        } else if (d == 2) {
            // 좌하우
            watch(cctv, 1, N, M, copyMap)
            watch(cctv, 2, N, M, copyMap)
            watch(cctv, 3, N, M, copyMap)
        } else if (d == 3) {
            // 상좌하
            watch(cctv, 0, N, M, copyMap)
            watch(cctv, 2, N, M, copyMap)
            watch(cctv, 3, N, M, copyMap)
        }
    } else if (cctvNum == 5) { // 상우하좌
        watch(cctv, 0, N, M, copyMap)
        watch(cctv, 1, N, M, copyMap)
        watch(cctv, 2, N, M, copyMap)
        watch(cctv, 3, N, M, copyMap)
    }
}

// BFS로 방향에 맞게 감시
fun watch(cctv: CCTV, d: Int, N: Int, M: Int, copyMap: Array<IntArray>) {
    val queue: Queue<CCTV> = LinkedList<CCTV>()

    val visited = Array(N) { BooleanArray(M) }

    queue.add(cctv)
    visited[cctv.x][cctv.y] = true

    while (queue.isNotEmpty()) {
        val prevCCTV = queue.poll()
        val nx = prevCCTV.x + dx[d]
        val ny = prevCCTV.y + dy[d]

        // 범위를 벗어나거나 벽을 만났는지 확인
        if (nx < 0 || nx >= N || ny < 0 || ny >= M || copyMap[nx][ny] == 6) {
            break
        }
        // 빈칸이면 -1 : 감시할 수 있다
        // 다른CCTV가 있거나 이미 감시된 칸이면 패스
        if (copyMap[nx][ny] == 0) {
            copyMap[nx][ny] = -1
        }
        queue.add(CCTV(cctv.num, nx, ny))
    }
}

fun findBlind(copyMap: Array<IntArray>, N: Int, M: Int) {
    var cnt = 0
    for (i in 0 until N) {
        for (j in 0 until M) {
            // 사각지대 찾기
            if (copyMap[i][j] == 0) {
                cnt++
            }
        }
    }
    answer = Math.min(answer, cnt)
}
