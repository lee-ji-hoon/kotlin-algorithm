/**
 * dp 해당 숫자까지 오는데 오는 횟수와 오면서 포함된 숫자들을 출력해야 한다.
 * stackedArray는 경로를 저장한다. 즉 전에 거쳤던 경로를 나타내게 된다.
 *
 * 10을 예시로 처음에 StringBuilder에는 10을 저장한다.
 * 그리고 stackedArray[10] = 9 이다. 즉 이전에 갔던 index를 가리키므로 0이 될때까지 반복을 하면된다.
 */

fun main() = with(System.`in`.bufferedReader()) {
    var n = readLine().toInt()
    val dp = IntArray(n + 1)
    val stackedArray = IntArray(n + 1)
    val sb = StringBuilder()

    dp[1] = 0
    for (i in 2..n) {
        dp[i] = dp[i - 1] + 1

        stackedArray[i] = i - 1
        if (i % 3 == 0 && dp[i / 3] + 1 < dp[i]) {
            dp[i] = dp[i / 3] + 1
            stackedArray[i] = i / 3
        }

        if (i % 2 == 0 && dp[i / 2] + 1 < dp[i]) {
            dp[i] = dp[i / 2] + 1
            stackedArray[i] = i / 2
        }
    }
    println(dp[n])
    while (n > 0) {
        sb.append("$n ")
        n = stackedArray[n]
    }
    println(sb)
}