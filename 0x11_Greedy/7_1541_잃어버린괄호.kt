import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val minusSt = StringTokenizer(readLine(), "-")

    // 아래처럼 덧셈 부분을 먼저 계산해주면 된다.
    // 덧셈 부분부터 계산을 해주다보면 자동적으로 -가 있다면 더해진 값이 -될 것이기 때문
    // 55 - (50 + 40) - (30 + 30)

    var flag = true
    var sum = 0

    while(minusSt.hasMoreTokens()) {
        var temp = 0

        // + 부분 이어져있으면 계속 해서 더해주기
        val addSt = StringTokenizer(minusSt.nextToken(), "+")
        while(addSt.hasMoreTokens()) {
            temp += addSt.nextToken().toInt()
        }

        when (flag) {
            true -> {
                sum = temp
                flag = false
            }
            false -> sum -= temp
        }
    }
    println(sum)

}