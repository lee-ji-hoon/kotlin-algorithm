import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val a = IntArray(n)
    val b = IntArray(n)

    var st = StringTokenizer(readLine(), " ")
    var aCount = 0
    while(st.hasMoreTokens()) {
        a[aCount++] = st.nextToken().toInt()
    }

    st = StringTokenizer(readLine(), " ")
    var bCount = 0
    while(st.hasMoreTokens()) {
        b[bCount++] = st.nextToken().toInt()
    }

    // ㅎㅎ.. b는 재배열하지 말랬지만 그리디니까 해도 되지 않을까?..
    a.sortDescending()
    b.sort()

    var sum = 0
    for (i in 0 until n) {
        sum += a[i] * b[i]
    }
    println(sum)
}