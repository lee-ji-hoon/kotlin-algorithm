import java.io.BufferedReader
import java.io.IOException
import java.util.*


fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val dp = IntArray(1001)
    dp[1] = 1
    dp[2] = 3
    for (i in 3..n) dp[i] = (dp[i - 2] * 2 + dp[i - 1]) % 10007
    println(dp[n])
}
