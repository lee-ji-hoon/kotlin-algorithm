import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine()
    val zero = StringTokenizer(n, "0")
    val one  = StringTokenizer(n, "1")
    println(zero.countTokens().coerceAtMost(one.countTokens()))
}