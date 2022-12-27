import java.io.StreamTokenizer
import kotlin.math.max

// https://www.acmicpc.net/problem/20922
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val k = input()

    val arr = IntArray(n) { input() }
    val checkdNum = IntArray(100_000 + 1)

    var end = 0
    var length = 0
    var answer = 0

    for (start in 0 until n) {
        while (end < n) {
            val endNum = arr[end]
            if (checkdNum[endNum] >= k) {
                break
            } else {
                end++
                checkdNum[endNum]++
                length++
            }
        }
        answer = max(length, answer)
        checkdNum[arr[start]]--
        length--
    }
    println(answer)
}
