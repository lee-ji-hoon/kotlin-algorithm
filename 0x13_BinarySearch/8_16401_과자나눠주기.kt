import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    var st = StringTokenizer(readLine(), " ")

    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()

    st = StringTokenizer(readLine(), " ")

    var max = 0 // 길이 최대값
    val arr = IntArray(m)

    for (i in 0 until  m) {
        arr[i] = st.nextToken().toInt()
        max = Math.max(max, arr[i])
    }

    println(binarySearch(arr, n, 1, max))
}

private fun binarySearch(arr: IntArray, n: Int, left: Int, right: Int): Int {
    var left = left
    var right = right

    var result = 0
    while (left <= right) {
        val mid = left + (right - left) / 2
        var cnt = 0
        for (i in arr.indices) {
            cnt += arr[i] / mid
        }
        if (cnt >= n) {
            result = mid
            left = mid + 1
        } else {
            right = mid - 1
        }
    }
    return result
}