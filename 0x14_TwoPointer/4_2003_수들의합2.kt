import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val NM = readLine().split(" ")

    val N = NM[0].toInt()
    val M = NM[1].toLong()

    val st = StringTokenizer(readLine(), " ")
    val arr = IntArray(N) { st.nextToken().toInt() }

    var start = 0
    var end = 0
    var sum = 0L
    var count = 0

    while (true) {
        if (sum >= M) {
            sum -= arr[start++]
        } else if (end == N) {
            break
        } else {
            sum += arr[end++]
        }
        if (sum == M) {
            count++
        }
    }
    println(count)
}
