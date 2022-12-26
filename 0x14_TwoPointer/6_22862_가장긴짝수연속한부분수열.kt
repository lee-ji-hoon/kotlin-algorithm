import java.io.StreamTokenizer
import kotlin.math.max

// https://www.acmicpc.net/problem/22862
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val s = input()

    val arr = IntArray(n) { input() }

    var end = 0
    var even = 0 // 짝
    var odd = 0 // 홀

    var answer = Int.MIN_VALUE

    for (start in 0 until n) {
        while (end < n) {
            if (arr[end] % 2 == 1 && odd == s) {
                break
            } else if (arr[end] % 2 == 0) {
                even++
            } else {
                odd++
            }
            end++
        }

        answer = max(even, answer)

        if (arr[start] % 2 == 1) {
            odd--
        } else {
            even--
        }
    }

    println(answer)
}
