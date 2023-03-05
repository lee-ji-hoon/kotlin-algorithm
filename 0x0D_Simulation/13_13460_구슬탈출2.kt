import java.util.*

// https://velog.io/@blucky8649/%EC%BD%94%ED%8B%80%EB%A6%B0-%EB%B0%B1%EC%A4%80-13460%EB%B2%88-%EA%B5%AC%EC%8A%AC-%ED%83%88%EC%B6%9C-2-%EC%82%BC%EC%84%B1-SW-%EC%97%AD%EB%9F%89-%ED%85%8C%EC%8A%A4%ED%8A%B8-%EA%B8%B0%EC%B6%9C-%EB%AC%B8%EC%A0%9C-%ED%92%80%EC%9D%B4
// 참고 블로그

data class Position13460(var x: Int, var y: Int)
data class Marble(
    val R: Position13460,
    val B: Position13460,
    val cnt: Int
)

private lateinit var board: Array<CharArray>
private val dy = arrayOf(-1, 0, 1, 0)
private val dx = arrayOf(0, 1, 0, -1)
private var isHole = false

var R = 0
var C = 0
fun main() = with(System.`in`.bufferedReader()) {
    val (r, c) = readLine().split(" ").map { it.toInt() }
    R = r; C = c

    var posR = Position13460(0, 0)
    var posB = Position13460(0, 0)
    board = Array(R) { CharArray(C) }
    for (i in 0 until R) {
        val part = readLine()
        for (j in 0 until C) {
            board[i][j] = part[j]
            if (board[i][j] == 'R') {
                posR = Position13460(j, i)
                board[i][j] = '.'
            }
            if (board[i][j] == 'B') {
                posB = Position13460(j, i)
                board[i][j] = '.'
            }
        }
    }
    println(rollingInTheHole(posR, posB))
}

fun rollingInTheHole(posR: Position13460, posB: Position13460): Int {
    val isVisited = Array(R) { Array(C) { Array(R) { BooleanArray(C) } } }
    val q: Queue<Marble> = LinkedList()
    q.offer(Marble(posR, posB, 0))
    isVisited[posR.y][posR.x][posB.y][posB.x] = true

    while (q.isNotEmpty()) {
        val cur = q.poll()
        if (cur.cnt == 10) return -1
        for (i in 0 until 4) {
            // 파란 구슬 이동
            val nextB = moveMarble(cur.B.x, cur.B.y, i)
            // 파란 구슬이 구멍에 들어가면 게임은 끝나지만, 뒤에 다른 경우도 봐야하기 때문에 continue.
            if (isHole) continue
            // 빨간 구슬 이동
            val nextR = moveMarble(cur.R.x, cur.R.y, i)
            if (isHole) return cur.cnt + 1
            // 방향에 따른 구슬 위치 조정
            if (nextR == nextB) {
                when (i) {
                    0 -> {
                        if (cur.R.y > cur.B.y) {
                            nextR.y++
                        } else {
                            nextB.y++
                        }
                    }

                    1 -> {
                        if (cur.R.x < cur.B.x) {
                            nextR.x--
                        } else {
                            nextB.x--
                        }
                    }

                    2 -> {
                        if (cur.R.y < cur.B.y) {
                            nextR.y--
                        } else {
                            nextB.y--
                        }
                    }

                    3 -> {
                        if (cur.R.x > cur.B.x) {
                            nextR.x++
                        } else {
                            nextB.x++
                        }
                    }
                }
            }
            if (isVisited[nextR.y][nextR.x][nextB.y][nextB.x]) continue
            isVisited[nextR.y][nextR.x][nextB.y][nextB.x] = true
            q.offer(Marble(nextR, nextB, cur.cnt + 1))
        }
    }
    return -1
}

fun moveMarble(x: Int, y: Int, dir: Int): Position13460 {
    isHole = false
    val pos = Position13460(x, y)
    while (board[pos.y + dy[dir]][pos.x + dx[dir]] != '#') {
        pos.x += dx[dir]
        pos.y += dy[dir]

        if (board[pos.y][pos.x] == 'O') {
            isHole = true

            return pos
        }
    }
    return pos
}