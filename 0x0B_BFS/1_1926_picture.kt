import java.util.*


internal class Node(var x: Int, var y: Int)

private val dx = intArrayOf(1, 0, -1, 0)
private val dy = intArrayOf(0, 1, 0, -1)

fun main() {
    val sc = Scanner(System.`in`)
    val n = sc.nextInt() // 세로 크기
    val m = sc.nextInt() // 가로 크기
    val board = Array(n) { IntArray(m) }
    val checked = Array(n) { BooleanArray(m) }
    for (i in 0 until n) {
        for (j in 0 until m) {
            board[i][j] = sc.nextInt()
        }
    }
    solution(n, m, board, checked)
}

fun solution(n: Int, m: Int, board: Array<IntArray>, checked: Array<BooleanArray>) {
    var max = 0
    var count = 0
    // 1. 시작 지점 찾기
    for (i in 0 until n) {
        for (j in 0 until m) {
            // 2. 시작지점 찾았으면 그림의 크기 찾기 (dfs)
            if (board[i][j] == 1 && !checked[i][j]) {
                count++
                // 3. 찾은 크기 중에서 가장 큰 것으로 저장
                max = Math.max(max, bfs(board, checked, i, j, n, m))
            }
        }
    }
    println(count)
    println(max)
}

fun bfs(board: Array<IntArray>, checked: Array<BooleanArray>, i: Int, j: Int, n: Int, m: Int): Int {
    var size = 0
    val q: Queue<Node> = LinkedList()
    q.offer(Node(i, j))
    checked[i][j] = true

    // 4. dfs시작
    while (!q.isEmpty()) {
        size++
        val cur = q.poll()
        for (dir in 0..3) {
            val nx = cur.x + dx[dir]
            val ny = cur.y + dy[dir]
            if (nx < 0 || nx >= n || ny < 0 || ny >= m)
                continue
            if (checked[nx][ny] || board[nx][ny] != 1)
                continue
            checked[nx][ny] = true
            q.offer(Node(nx, ny))
        }
    }
    return size
}
