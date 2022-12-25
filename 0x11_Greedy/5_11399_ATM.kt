import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val arr = IntArray(n)

    val st = StringTokenizer(readLine(), " ")
    for (i in 0 until n) {
        arr[i] = st.nextToken().toInt()
    }
    arr.sort()
    var sum = arr[0]
    var prevSum = arr[0]

    for (i in 1 until n) {
        prevSum += arr[i]
        sum += prevSum
    }
    println(sum)
}