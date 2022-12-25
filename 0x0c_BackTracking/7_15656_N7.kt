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
    // 4. 이 문제는 전에 숫자를 사용했는지 체크할 필요가 없음
    list.forEachIndexed { index, s ->
        arr[depth] = s.toInt()
        solution(depth + 1, n, m)
    }
}