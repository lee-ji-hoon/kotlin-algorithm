/**
 * 숫자가 입력이 되면 그 숫자가 2로 나눠지는 3으로 나눠지는지 그게 아니라면 -1 한다. 라고 생각 할 수 있다.
 * 하지만 이 문제에는 말장난이 하나 있다. 3이나 2로 나눠 떨어지는게 아닌경우 -1 한다는게 아니라 그냥 1을 뺀다 라는 경우의 수가 있는 것이다.
 * 그 말은 즉 10에 -1을 9번 해서 1로 만드는 경우의 수도 있다는 말이다.
 *
 * 예로 들어보자면
 * 입력값이 10이라고 가정했을 때 두가지 경우의 수가 있다.
 * 10 / 2 = 5 (count = 1) && 10 - 1 = 9 (count = 1)
 * 5 - 1 = 4 (count = 2) && 9 / 3 = 3 (count = 2)
 * 4 / 2 = 2 (count = 3) && 3 / 3 = 1 (count = 3)
 * 2 / 2 = 1 (count = 4)
 * count = 4 && count = 3
 *
 * 10은 2로 나누거나 -1 하는 경우
 * 그 후 5는 -1 만 가능
 * 9는 3으로 나누거나 -1 하는 경우
 *
 * 이런 식으로 2혹은 3으로 나눠떨어지면 나누는 경우 + -1만 하는 경우 이런식으로 되는 것이다.
 * 가령 6이 입력을 받았다고 하면 3가지의 경우의 수로 시작하는 것이다.
 * 2로 나누는 경우 3으로 나누는경우 -1하는 경우 이렇게 3가지로 시작을 해서 쭉 이어 나가는 것이다.
 */

private var answer = Int.MAX_VALUE

fun main() = with(System.`in`.bufferedReader()) {
    val x = readLine().toInt()
    solution(0, x)
    println(answer)
}

private fun solution(count: Int, x: Int) {
    if (count >= answer) return
    if (x == 1) {
        answer = answer.coerceAtMost(count)
        return
    }
    if (x % 3 == 0) solution(count + 1, x / 3)
    if (x % 2 == 0) solution(count + 1, x / 2)
    solution(count + 1, x - 1)
}
