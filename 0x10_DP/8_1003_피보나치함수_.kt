


/**
 * 1시간 반 동안 자력으로 푸는데 실패
 * https://st-lab.tistory.com/124
 */

var dp = Array(41) { arrayOfNulls<Int>(2) }

fun main() = with(System.`in`.bufferedReader()) {

    dp[0][0] = 1 // N=0 일 때의 0 호출 횟수
    dp[0][1] = 0 // N=0 일 때의 1 호출 횟수
    dp[1][0] = 0 // N=1 일 때의 0 호출 횟수
    dp[1][1] = 1 // N=1 일 때의 1 호출 횟수

    val t: Int = readLine().toInt()
    for (i in 0 until t) {
        val n: Int = readLine().toInt()
        fibonacci(n)
        println(dp[n][0].toString() + " " + dp[n][1])
    }
}

private fun fibonacci(N: Int): Array<Int?> {
    if (dp[N][0] == null || dp[N][1] == null) { // 탐색하지 않은 경우에만 찾음
        // 각 N에 대한 0 호출 횟수와 1 호출 횟수를 재귀호출한다.
        dp[N][0] = fibonacci(N - 2)[0]?.let { fibonacci(N - 1)[0]?.plus(it) }
        dp[N][1] = fibonacci(N - 2)[1]?.let { fibonacci(N - 1)[1]?.plus(it) }
    }
    return dp[N]
}
