import java.util.*

private lateinit var arr: IntArray
private lateinit var isUsed: BooleanArray
private lateinit var list: List<String>
private val sb = StringBuilder()

fun main() = with(System.`in`.bufferedReader()) {
    // 1. 입력 받기
    val (n, m) = readLine().split(" ").map { it.toInt() }
    list = readLine().split(" ").sortedBy { it.toInt() }
    arr = IntArray(m)
    isUsed = BooleanArray(n)

    // 2. 중복 제외 순열
    solution(0, n, m)
    println(sb.toString())
}

private fun solution(depth: Int, n: Int, m: Int) {
    // 3. depth 판별
    if (depth == m) {
        arr.forEach {
            sb.append(it).append(' ')
        }
        sb.append("\n")
        return
    }

    // 4. list 에서 숫자가 만약 이전 숫자보다 큰지 확인하면서 돌기
    list.forEachIndexed { index, s ->
        if (isUsed[index].not()) {
            if (depth == 0 || arr[depth - 1] < s.toInt()) {
                isUsed[index] = true
                arr[depth] = s.toInt()
                solution(depth + 1, n, m)
                isUsed[index] = false
            }
        }
    }
}