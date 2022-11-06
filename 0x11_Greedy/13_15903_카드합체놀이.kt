import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }

    // 결국에는 낮은 점수부터 더하면 되니 우선순위 큐 사용
    val pq = PriorityQueue<Long>()
    val split = readLine().split(" ").map { it.toLong() }
    pq.addAll(split)

    for (i in 0 until m) {
        // 2개 빼서 더한 다음 다시 Queue에 집어 넣어주기 -> 우선순위 큐이므로 자동 정렬
        val first = pq.poll()
        val second = pq.poll()

        val temp = first + second
        pq.offer(temp)
        pq.offer(temp)
    }

    val sum = pq.sum()
    println(sum)
}