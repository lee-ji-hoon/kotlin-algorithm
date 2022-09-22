import java.util.*

private lateinit var arr: IntArray
private lateinit var list: List<String>
private val sb = StringBuilder()

fun main() = with(System.`in`.bufferedReader()) {
    // 1. 입력 받기
    val (n, m) = readLine().split(" ").map { it.toInt() }
    list = readLine().split(" ").sortedBy { it.toInt() }
    arr = IntArray(m)

    // 2. 중복 제외 순열
    solution(0, n, m)
    println(sb.toString())
}

private fun solution(depth: Int, n: Int, m: Int) {
    // 3. depth 판별
    if (depth == m) {
        arr.forEach {
            sb.append(it).append(" ")
        }
        sb.append("\n")
        return
    }
    // 4. 이전 숫자보다 커야지 들어갈 수 있다! 같아도 된다!
    list.forEach {
        if (depth == 0 || arr[depth - 1] <= it.toInt()) {
            arr[depth] = it.toInt()
            solution(depth + 1, n, m)
        }
    }
}