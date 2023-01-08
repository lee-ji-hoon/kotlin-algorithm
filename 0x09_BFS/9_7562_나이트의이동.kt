import java.util.*

private class NightNode(var x: Int, var y: Int)

// 나이트가 이동할 수 있는 경ㄹ는 체스의 나이트와 같다.
// 가로 2칸 이동 후 세로1칸 혹은 가로1칸 이동 후 세로1칸이기 때문에 8가지 경우 발생
private val dx = intArrayOf(-1, 1, -1, 1, -2, 2, 2, -2)
private val dy = intArrayOf(-2, -2, 2, 2, -1, -1, 1, 1)

fun main() = with(System.`in`.bufferedReader()) {
    val t = readLine().toInt() // 테스트 케이스의 개수

    for (i in 0 until t) {
        val l = readLine().toInt() // 체스판 한 변의 길이
        val chess = Array(l) { IntArray(l) }

        // 1. 시작 지점 과 도착지점 찾기
        var st = StringTokenizer(readLine(), " ")
        val x = st.nextToken().toInt()
        val y = st.nextToken().toInt()
        chess[x][y] = 1

        val q: Queue<NightNode> = LinkedList()
        q.offer(NightNode(x, y))

        st = StringTokenizer(readLine(), " ")
        val endNode = NightNode(st.nextToken().toInt(), st.nextToken().toInt())
        // 2. dfs 시작
        println(dfs(l, chess, endNode, q) - 1)
    }
}

private fun dfs(l: Int, chess: Array<IntArray>, endNode: NightNode, q: Queue<NightNode>): Int {
    while (q.isEmpty().not()) {
        val cur = q.poll()
        if (cur.x == endNode.x && cur.y == endNode.y) {
            return chess[cur.x][cur.y]
        }

        val x = cur.x
        val y = cur.y

        for (dir in 0..7) {
            val nx = x + dx[dir]
            val ny = y + dy[dir]

            if (nx < 0 || nx >= l || ny < 0 || ny >= l) continue
            if (chess[nx][ny] != 0) continue

            q.offer(NightNode(nx, ny))
            chess[nx][ny] = chess[x][y] + 1
        }
    }
    return 1
}
