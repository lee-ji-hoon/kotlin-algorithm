
/**
 * 세칸 연속으로 갈 수 없다는 점 생각하면서 점화식 짜보기
 *
 * 2칸 전의 dp값 || 3칸 전의 dp + 한 칸 전의 값 (3칸 전의 dp값이므로 2번째 값을 안사용 했다는 의미 -> 바로 전 값을 사용할 수 있다.)
 * 위 내용을 점화식으로 쓰면 아래와 같다.
 * Math.Max(dp[i-2], dp[i-3] + arr[i-1]) + arr[i] (더 큰 값에 현재 값 더하기)
 *
 * 표로 그리자면 아래와 같다.
 *
 *  10 20 15 25 10 20
 *  N   0  1  2  3  4  5  6
 *  dp  0  10 30 35 50 65 65
 *  dp  0  0  20 25 55 45 75
 */

fun main() = with(System.`in`.bufferedReader()) {
    val n: Int = readLine().toInt()
    val arr = IntArray(n + 1) // 시작점은 포함x
    val dp = IntArray(n + 1)
    for (i in 1..n)
        arr[i] = readLine().toInt()

    dp[0] = 0 // 시작
    dp[1] = arr[1] // 1도 볼 필요 없으므로 바로 값 할당

    if (n >= 2) {
        dp[2] = dp[1] + arr[2]
    }
    for (i in 3..n) {
        dp[i] = dp[i - 2].coerceAtLeast(dp[i - 3] + arr[i - 1]) + arr[i]
    }
    println(dp[n])
}
