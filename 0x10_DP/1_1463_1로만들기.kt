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
