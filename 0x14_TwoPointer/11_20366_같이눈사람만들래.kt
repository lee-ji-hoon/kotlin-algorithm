import java.io.StreamTokenizer
import java.util.*

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = input()
    val arr = IntArray(N) { input() }.apply { sort() }
    var min = Int.MAX_VALUE
    for (i in 0 until N) {
        for (j in i + 1 until N) {
            val snowMan1 = arr[i] + arr[j]
            var start = 0
            var end = N - 1
            while (start < end) {
                if (start == i || start == j) {
                    start++
                    continue
                }
                if (end == i || end == j) {
                    end--
                    continue
                }
                val snowMan2 = arr[start] + arr[end]
                min = Math.min(min, Math.abs(snowMan1 - snowMan2))
                if (snowMan1 > snowMan2) {
                    start++
                } else if (snowMan1 < snowMan2) {
                    end--
                } else {
                    println(0)
                    return
                }
            }
        }
    }
    println(min)
}
