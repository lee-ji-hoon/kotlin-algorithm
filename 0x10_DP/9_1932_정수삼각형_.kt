import java.util.*


/**
 * 맨 위층부터 시작해서 탐색을 한다.
 * 층마다 입력값에 주어진 길이만큼 모든 경우에대해서 최댓값을 조사해야한다.
 * 마지막 층에서 최대값을 가지는 수 찾기
 *
 * 처음에는 DFS로 하면 되지 않나하고 했는데
 * 왼쪽 오른쪽을 거친다고 해서 무조건 최대값을 가지지 않는 반례가 존재했었다.
 */


fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    var max = 0
    val dp = Array(n + 1) { IntArray(n + 1) }

    for (i in 1 until n + 1) {
        val st = StringTokenizer(readLine())
        var x = 1
        while (x <= i && st.hasMoreElements()) {
            val num = st.nextToken().toInt()
            when (x) {
                // 1과 i와 같은 경우는 비교할 값이 1개이기 때문에 따로 비교해서 계산을 해주었다.
                1 -> dp[i][x] = dp[i - 1][x] + num
                i -> dp[i][x] = dp[i - 1][x - 1] + num
                else -> dp[i][x] = dp[i - 1][x - 1].coerceAtLeast(dp[i - 1][x]) + num
            }
            x++
        }
    }

    for (num in dp[n]) {
        max = max.coerceAtLeast(num)
    }

    println(max)
}
