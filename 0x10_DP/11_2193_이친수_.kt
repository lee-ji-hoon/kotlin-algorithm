import java.util.*


/**
 * 1 : 1개
 * 2 : 1개
 * 3 : 2개
 * 4 : 3개
 * 5 : 5개
 * 피보나치 규칙 이용
 * dp[n] = dp[n-2] + dp[n-1]
 */

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val dp = LongArray(n + 1)
    println(solution(n, dp))
}

private fun solution(n: Int, dp: LongArray): Long {
    if (n == 1 || n == 2) return 1
    if (dp[n] > 0) return dp[n]
    dp[n] = solution(n - 1, dp) + solution(n - 2, dp)
    return dp[n]
}
