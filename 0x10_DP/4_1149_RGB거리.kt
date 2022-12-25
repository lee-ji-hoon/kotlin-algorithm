import java.util.*

/**
 * https://gyeong-log.tistory.com/96
 *
 * 식이 도저히 생각이 안나서 아래 해설을 읽고서 코드를 구현해봤습니다.
 *
 * index 번째 집을 빨간색(0)으로 -> min (index-1번째 집이 파란색(1)인 경우, index-1번째 집이 초록색(2)인 경우)
 * index 번째 집을 파란색(1)으로 -> min (index-1번째 집이 빨간색(0)인 경우, index-1번째 집이 초록색(2)인 경우)
 * index 번째 집을 초록색(2)으로 -> min (index-1번째 집이 빨간색(0)인 경우, index-1번째 집이 파란색(1)인 경우)
 * 마지막집까지 확인을 한 후젤 작은 값을 출력하면 된다.
 */

fun main() = with(System.`in`.bufferedReader()){
    val n = readLine().toInt()
    val dp = Array(n) { StringTokenizer(readLine(), " ").run { IntArray(3) {nextToken().toInt()} }}

    for (index in 1 until n) {
        dp[index][0] += minOf(dp[index-1][1], dp[index-1][2])
        dp[index][1] += minOf(dp[index-1][0], dp[index-1][2])
        dp[index][2] += minOf(dp[index-1][0], dp[index-1][1])
    }

    print(minOf(dp[n-1][0], dp[n-1][1], dp[n-1][2]))
}