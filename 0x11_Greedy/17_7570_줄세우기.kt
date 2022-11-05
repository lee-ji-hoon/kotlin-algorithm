import java.util.*

/** LIS 알고리즘 적용 O(N^2) -> LIS.kt
 *  4, 2, 1, 3, 5 에서 기본 LIS를 적용하면 {2,3,5} {1,3,5}가 나온다.
 *  그렇기에 맨앞뒤가 아니고 아무렇게나 이동이 가능하다면 최소 이동횟수는 2이다.
 *
 *  하지만 우리 문제에서는 맨앞 혹은 맨뒤로만 이동할 수 있다.
 *  고정 {2,3} 이동 {4,1,5} 라는 것을 알 수 있는데
 *  여기서 유추할 수 있는 것은 고정된 얘들은 [가장 긴 증가수열 && 연속된 수] 라는 것이다.
 *
 *  for문 처음꺼는 0~n까지 중첩은 i~n까지 돌리는 방식을 선택했었다.
 *  하지만 이렇게 돌리니 아마 O(n^2)? 라서 터지는거 같다.
 *  두번째 for문에서 0~n이 아니라 i~n까지라 가능할거 같았는데 아쉽다.
 *
 *  2차원 배열로 하니까 시간초과가 떠서 어쩔 수 없이 다른 방법을 택했다.
 */
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()

    // dp[n] = n번호일때까지 연속된 증가수열의 개수
    val dp = IntArray(n + 1)
    val st = StringTokenizer(readLine(), " ")
    while (st.hasMoreTokens()) {
        val j = st.nextToken().toInt()
        dp[j] = dp[j - 1] + 1
    }

    /**
     * dp[5] = dp[4] + 1 => 0 + 1 = 1
     * dp[2] = dp[1] + 1 => 0 + 1 = 1
     * dp[4] = dp[3] + 1 => 0 + 1 = 1
     * dp[1] = dp[0] + 1 => 0 + 1 = 1
     * dp[3] => dp[2] + 1 => 1 + 1 = 2
     */

    dp.sort()
    println(n - dp[n])
}
