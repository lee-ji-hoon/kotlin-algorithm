import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    // 최댓값과 최솟값의 차이가 최소가 되도록 선수를 선발
    val n = input()
    val m = input()

    val map = Array(n) { IntArray(m) { input() }.apply { sort() } }
    val arr = IntArray(n)
    var result = Int.MAX_VALUE
    var minArray = 0
    while (true) {
        var max = Int.MIN_VALUE
        var min = Int.MAX_VALUE
        for (i in 0 until n) {
            max = Math.max(max, map[i][arr[i]])
            min = Math.min(map[i][arr[i]], min)
            if (min == map[i][arr[i]]) minArray = i
        }
        result = Math.min(result, max - min)
        arr[minArray]++
        if (arr[minArray] == m) break
    }
    println(result)
}
