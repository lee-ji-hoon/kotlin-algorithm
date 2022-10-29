import java.util.*

data class Lecture(
    val start: Int,
    val end: Int,
)

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()

    val arr = arrayListOf<Lecture>()
    for (i in 0 until n) {
        val stringToken = StringTokenizer(readLine(), " ")
        arr.add(Lecture(stringToken.nextToken().toInt(), stringToken.nextToken().toInt()))
    }
    val sorted = arr.sortedBy { it.start }

    val pq = PriorityQueue<Int>()
    pq.offer(sorted[0].end)
    for (i in 1 until n) {
        if (pq.peek() <= sorted[i].start) pq.poll()
        pq.offer(sorted[i].end)
    }
    println(pq.size)

    // 아래 방법으로 해결해볼려고 했지만 실패 예제만 통과
    /*var count = 1
    var temp = 0
    for (i in 1 until n) {
        if (sorted[temp].start <= sorted[i].end) {
            count++
            temp = i
        }
    }
    println(count)*/
}