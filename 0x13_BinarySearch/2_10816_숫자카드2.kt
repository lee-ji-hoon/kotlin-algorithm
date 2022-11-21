import java.util.*

// HashMap이나 Array에 해당 위치 나오면 +1 해주면 될거 같은데... 이분 탐색 해야 하나?..
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    var st = StringTokenizer(readLine(), " ")
    val sangeun = IntArray(n) { st.nextToken().toInt() }
    val m = readLine()
    sangeun.sort()
    st = StringTokenizer(readLine(), " ")
    val sb = StringBuilder()
    while (st.hasMoreTokens()) {
        val target = st.nextToken().toInt()
        // println(upperIndex(target, n, sangeun) - lowerIndex(target, n, sangeun)) -> 시간 초과의 주범
        sb.appendLine(upperIndex(target, n, sangeun) - lowerIndex(target, n, sangeun))
    }
    println(sb.toString())
}

// 2 4 6 10 10 10 16 16 16 30 32 -> 10을 찾고자 할 때
// 10의 시작과 끝을 찾아야한다.
// 그러므로 중간위치를 찾아서 왼쪽, 오른쪽 경계값을 찾자!
private fun lowerIndex(target: Int, len: Int, arr: IntArray): Int {
    var st = 0
    var en = len

    while (st < en) {
        val mid = (st + en) / 2
        if (arr[mid] >= target) en = mid
        else st = mid + 1
    }
    return st
}

private fun upperIndex(target: Int, len: Int, arr: IntArray): Int {
    var st = 0
    var en = len

    while (st < en) {
        val mid = (st + en) / 2
        if (arr[mid] > target) en = mid
        else st = mid + 1
    }
    return st
}