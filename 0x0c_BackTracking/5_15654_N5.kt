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

    solution(0, n, m)
    println(sb.toString())
}

private fun solution(depth: Int, n: Int, m: Int) {
    if (depth == m) {
        arr.forEach {
            sb.append(it).append(' ')
        }
        sb.append("\n")
        return
    }
    list.forEachIndexed { index, s ->
        if (isUsed[index].not()) {
            isUsed[index] = true
            arr[depth] = s.toInt()
            solution(depth + 1, n, m)
            isUsed[index] = false
        }
    }
}
