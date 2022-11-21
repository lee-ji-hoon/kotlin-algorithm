import java.util.*

// 중복을 제외하고 나보다 작은 수가 몇 개 있는지?.
//   2   4 -10 4 -9
// -10, -9, 2, 4, 4
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val st = StringTokenizer(readLine(), " ")
    val arr = IntArray(n) { st.nextToken().toInt() }
    val sorted = arr.distinct().sorted()
    val map = mutableMapOf<Int, Int>()
    for (target in sorted) {
        val lowerIndex = lowerIndex(target, sorted.size, sorted)
        map[target] = lowerIndex
    }

    val sb = StringBuilder()
    for (i in arr) {
        sb.append("${map[i]} ")
    }
    println(sb.toString())
}

// 본인보다 작은 수의 수를 찾는 것이므로 구간만 찾으면 된다.
private fun lowerIndex(target: Int, len: Int, arr: List<Int>): Int {
    var st = 0
    var en = len

    while (st < en) {
        val mid = (st + en) / 2
        if (arr[mid] >= target) en = mid
        else st = mid + 1
    }
    return st
}

