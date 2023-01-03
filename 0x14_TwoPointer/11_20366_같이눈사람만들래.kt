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

    // 2중 for문 돌면서 모든 눈사람 조합 구하기
    // 차이가 최소라면 두 값이 붙어 있어야 한다고 이해
    // 붙어 있는 두 눈사람이 같은 눈덩이를 사용하지 않을 때만 값을 비교

    // 5, 1, 3, 6, 9
    // 4, 6, 7, 8, 9 ~ 15
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
