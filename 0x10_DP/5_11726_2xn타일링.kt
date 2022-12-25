/**
 * n = 1 ? |
 * n = 2 ? ||,=
 * n = 3 ? |||, |=, =|
 * n = 4 ? ||||, ||=, |=|, =||, ==
 *
 * 위와 같이 만들어지는데 1, 2, 3, 5
 * 3부터는 (n-2) + (n-1)이게 된다.
 * 그러면 피보나치 처럼 풀 수 있을거 같다.
 *
 * n = 1 answer[1] = 1
 * n = 2 answer[2] = 2
 * n = 3 answer[3] = answer[1] + answer[2]
 * n = 4 answer[4] = answer[2] + answer[3]
 */


private lateinit var answer: IntArray
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    answer = IntArray(n + 1)
    solution(n)

    println(answer[n])
}

private fun solution(n: Int): Int {
    return if(answer[n] > 0) answer[n];
    else if(n == 1) {
        answer[n] = 1
        answer[n]
    }
    else if(n == 2) {
        answer[n] = 2
        answer[n]
    }
    else {
        answer[n] = (solution(n - 2) + solution(n - 1)) % 10007;
        answer[n]
    }
}