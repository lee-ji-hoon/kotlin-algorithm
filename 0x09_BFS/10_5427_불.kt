import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

class FireNode(var x: Int, var y: Int)

private val dx = intArrayOf(1, 0, -1, 0)
private val dy = intArrayOf(0, 1, 0, -1)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val t = br.readLine().toInt()

    for (i in 0 until t) {
        val st = StringTokenizer(br.readLine(), " ")
        val w = st.nextToken().toInt()
        val h = st.nextToken().toInt()

        // 1. 새로운 빌딩 만들기
        val building = Array(h) {
            arrayOfNulls<String>(w)
        }

        val fire_dist = Array(h) { IntArray(w) }
        val dog_dist = Array(h) { IntArray(w) }

        val fire: Queue<FireNode> = LinkedList()
        val dog: Queue<FireNode> = LinkedList()

        for (j in 0 until h) {
            val floor = br.readLine()
            for (k in 0 until w) {
                val type = floor[k].toString()
                building[j][k] = type
                if (type == "*") {
                    fire.offer(FireNode(j, k))
                    fire_dist[j][k] = 1
                }
                if (type == "@") {
                    dog.offer(FireNode(j, k))
                    dog_dist[j][k] = 1
                }
            }
        }

        // 2. 불이 퍼지는 시간 구하기
        findFire(building, fire_dist, fire, h, w)
        // 3. 상근이가 탈출하는 시간 구하기
        val answer = dogDFS(building, dog_dist, fire_dist, dog, h, w)

        if (answer == Int.MAX_VALUE) {
            println("IMPOSSIBLE")
        } else {
            println(answer)
        }
    }
}

private fun findFire(building: Array<Array<String?>>, dist: Array<IntArray>, q: Queue<FireNode>, h: Int, w: Int) {
    while (q.isEmpty().not()) {
        val cur = q.poll()
        val x = cur.x
        val y = cur.y
        for (dir in 0..3) {
            val nx = x + dx[dir]
            val ny = y + dy[dir]
            if (nx < 0 || nx >= h || ny < 0 || ny >= w) continue
            if (building[nx][ny] == "#" || dist[nx][ny] != 0) continue
            q.offer(FireNode(nx, ny))
            dist[nx][ny] = dist[x][y] + 1
        }
    }
}

private fun dogDFS(
    building: Array<Array<String?>>,
    dog_dist: Array<IntArray>,
    fire_dist: Array<IntArray>,
    q: Queue<FireNode>,
    h: Int,
    w: Int
): Int {
    var answer = Int.MAX_VALUE
    while (q.isEmpty().not()) {
        val cur = q.poll()
        val x = cur.x
        val y = cur.y
        // 만약 벽에 닿았을 경우 탈출
        if (checkFire(x, y, w, h)) {
            answer = Math.min(answer, dog_dist[x][y])
            continue
        }
        for (dir in 0..3) {
            val nx = x + dx[dir]
            val ny = y + dy[dir]
            if (nx < 0 || nx >= h || ny < 0 || ny >= w) continue
            if (building[nx][ny] == "#" || dog_dist[nx][ny] != 0) continue
            if (fire_dist[nx][ny] != 0 && dog_dist[x][y] + 1 >= fire_dist[nx][ny]) continue
            dog_dist[nx][ny] = dog_dist[x][y] + 1
            q.offer(FireNode(nx, ny))
        }
    }
    return answer
}

private fun checkFire(x: Int, y: Int, w: Int, h: Int): Boolean {
    return x + 1 == h || y + 1 == w || x == 0 || y == 0
}
