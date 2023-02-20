import java.io.StreamTokenizer
import java.util.*

/**
 * 이 코드에서는 위치 정보를 Pos 클래스로 관리하고, 스택을 이용하여 DFS를 구현합니다.
처음에는 현재 위치를 스택에 push한 뒤, 스택이 비어있을 때까지 다음 작업을 반복합니다.

1. 스택에서 위치 정보를 꺼낸다.
2. 현재 위치가 청소하지 않은 공간이면 청소한다.
3. 왼쪽 방향부터 청소할 수 있는 공간이 있는지 탐색한다. 청소할 수 있는 공간이 있으면 그 방향으로 이동하고 스택에 push하고, 이동한 방향으로 방향을 회전한다.
4. 모든 방향이 청소되어 있거나 벽이면 후진한다. 후진할 위치가 벽이 아니면 그 위치로 이동하고 스택에 push한다.
5. 스택이 빌 때까지 위 작업을 반복하며 청소한 영역의 개수를 센다.

이렇게 구현하면 로봇 청소기가 이동하면서 청소하는 영역의 개수를 구할 수 있습니다.
 */

private data class Pos2(val x: Int, val y: Int)

private val dx = listOf(-1, 0, 1, 0) // 북, 동, 남, 서
private val dy = listOf(0, 1, 0, -1)

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val m = input()
    val x = input()
    val y = input()
    var d = input()

    val map = Array(n) { IntArray(m) { input() } }

    var count = 0
    val stack = Stack<Pos2>()
    stack.push(Pos2(x, y))

    while (stack.isNotEmpty()) {
        val (cx, cy) = stack.pop()

        if (map[cx][cy] == 0) { // 현재 위치 청소
            map[cx][cy] = 2
            count++
        }

        var canMove = false
        for (i in 0 until 4) { // 왼쪽 방향부터 탐색
            val nd = (d + 3 - i) % 4
            val nx = cx + dx[nd]
            val ny = cy + dy[nd]
            if (nx in 0 until n && ny in 0 until m && map[nx][ny] == 0) { // 청소할 수 있는 공간이 있는 경우
                stack.push(Pos2(nx, ny))
                d = nd // 방향 회전
                canMove = true
                break
            }
        }

        if (canMove.not()) { // 모든 방향이 청소되어 있거나 벽인 경우 후진
            val nd = (d + 2) % 4
            val nx = cx + dx[nd]
            val ny = cy + dy[nd]
            if (nx in 0 until n && ny in 0 until m && map[nx][ny] != 1) { // 벽이 아닌 경우
                stack.push(Pos2(nx, ny))
            }
        }
    }
    println(count)
}
