import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (N, K) = readLine().split(" ").map { it.toInt() }
    val dist = IntArray(100_001)
    solution(N, K, dist)
    println(dist[K])
}

private fun solution(n: Int, k: Int, dist: IntArray) {
    val q: Queue<Int> = LinkedList()
    q.offer(n)
    // 1. 3가지 방향 확인 [-1, +1, *2]
    while (q.isEmpty().not()) {
        val cur = q.poll()
        // 2. 만약 이동한 방향의 값이 동생 위치와 동일하다면 answer = arr[nx];
        if (cur == k) break
        val max = dist.size

        if (cur + 1 < max && dist[cur + 1] == 0) {
            dist[cur + 1] = dist[cur] + 1
            q.offer(cur + 1)
        }
        if (cur - 1 >= 0 && dist[cur - 1] == 0) {
            dist[cur - 1] = dist[cur] + 1
            q.offer(cur - 1)
        }
        if (cur * 2 < max && dist[cur * 2] == 0) {
            dist[cur * 2] = dist[cur] + 1
            q.offer(cur * 2)
        }
    }
}