// https://mygumi.tistory.com/69?category=677288
// LIS 최장 증가 부분 수열
// {10, 20 10, 30, 20, 50} -> {10(o), 20(o), 10(x), 30(o), 20(x), 50(o)} 길이는 4이다.
fun main() = with(System.`in`.bufferedReader()){
    val n = readLine().toInt()

    val array = IntArray(n) // 인덱스마다 각 입력값
    val dp = IntArray(n) // 인덱스마다 각 증가 수열의 길이
    var max = 0

    dp[0] = 1

    for (i in 1 until n) {
        dp[i] = 1
        // i 를 기준으로 인덱스 0 에서부터 i-1까지 체크한다
        // 길이를 기준
        for (j in 0 until i) {
            if (array[i] > array[j] && dp[j] + 1 > dp[i]) {
                // 증가 수열
                dp[i] = dp[j] + 1
            }
        }
        if (max < dp[i]) {
            max = dp[i]
        }
    }
}