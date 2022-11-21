import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val sangeun = mutableMapOf<String, Boolean>()
    var st = StringTokenizer(readLine(), " ")
    while(st.hasMoreTokens()) {
        sangeun[st.nextToken()] = true
    }

    val m = readLine().toInt()
    st = StringTokenizer(readLine(), " ")
    val sb = StringBuilder()
    while(st.hasMoreTokens()) {
        if (sangeun[st.nextToken()] == true) sb.append("1 ")
        else sb.append("0 ")
    }
    println(sb)
}