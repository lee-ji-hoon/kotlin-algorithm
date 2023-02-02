import java.util.*

private var board = arrayOf<CharArray>()
private var dx = intArrayOf(0, 1, 0, -1)
private var dy = intArrayOf(1, 0, -1, 0)
private var visited = arrayOf<BooleanArray>()
private var list = arrayListOf<PuyoNode>()
private var n = 12
private var m = 6

/**
 * 입력받은 필드를 탐색
 * 뿌요가 있는 필드에 도달하면 근처에 같은 색 뿌요가 몇 개 있는지 BFS로 찾기!
 * 같은 색의 뿌요가 4개 이상이라면 해당 뿌요들을 연쇄가 일어났다고 표시 (boolean 값으로?)
 * false -> 연쇄가 일어났다면 아래로 떨어드리고 연속 연쇄 수 증가
 * true  -> 연쇄가 안일어났다면 탐색 종료
 */

fun main() = with(System.`in`.bufferedReader()) {
    board = Array(n) { readLine().toCharArray() }
    var count = 0
    // board를 탐색
    while (true) {
        var isFinished = true
        visited = Array(n) { BooleanArray(m) }
        for (i in 0 until n) {
            for (j in 0 until m) {
                if (board[i][j] != '.') {
                    list = ArrayList()
                    bfs(board[i][j], i, j)
                    // 4개 이상 뿌요 같이 있는지 확인
                    if (list.size >= 4) {
                        isFinished = false // 연쇄가 일어났으므로 더 탐색해야 한다.
                        for (k in list.indices) {
                            board[list[k].x][list[k].y] = '.' // 터트려서 없앰
                        }
                    }
                }
            }
        }
        if (isFinished) break
        dropBlocks()
        count++
    }
    println(count)
}

fun dropBlocks() {
    for (i in 0 until m) {
        for (j in n - 1 downTo 1) {
            if (board[j][i] == '.') {
                for (k in j - 1 downTo 0) {
                    if (board[k][i] != '.') {
                        board[j][i] = board[k][i]
                        board[k][i] = '.'
                        break
                    }
                }
            }
        }
    }
}

fun bfs(findChar: Char, x: Int, y: Int) {
    val q: Queue<PuyoNode> = LinkedList()
    list.add(PuyoNode(x, y))
    q.offer(PuyoNode(x, y))
    visited[x][y] = true
    while (q.isNotEmpty()) {
        val current = q.poll()
        for (i in 0..3) {
            val nx = current.x + dx[i]
            val ny = current.y + dy[i]
            if (nx >= 0 && ny >= 0 && nx < n && ny < m && visited[nx][ny].not() && board[nx][ny] == findChar) {
                visited[nx][ny] = true
                list.add(PuyoNode(nx, ny))
                q.offer(PuyoNode(nx, ny))
            }
        }
    }
}

class PuyoNode(var x: Int, var y: Int)
