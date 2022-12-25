import java.util.*
import kotlin.text.StringBuilder

private lateinit var arr: IntArray
private lateinit var isUsed: BooleanArray
private val sb = StringBuilder()
private var N = 0
private var M = 0

fun main() = with(System.`in`.bufferedReader()) {
    // 1. 입력 받기
    val input = StringTokenizer(readLine(), " ")
    N = input.nextToken().toInt()
    M = input.nextToken().toInt()

    arr = IntArray(M)
    isUsed = BooleanArray(N + 1)

    solution(0)
    println(sb.toString())
}

private fun solution(depth: Int) {
    // 2. depth랑 내가 원하는 크기가 되면 출력
    if (depth == M) {
        arr.forEach {
            sb.append(it).append(" ")
        }
        sb.append("\n")
        return
    }

    // 3. 내가 원하는 크기의 배열 만들기
    for (i in 1..N) {
        // 4. 이미 사용 중이거나 현재 값보다 이전의 값이 더 크면 continue
        if (isUsed[i]) continue
        if (depth != 0 && arr[depth - 1] > i) continue

        arr[depth] = i
        isUsed[i] = true
        solution(depth + 1)
        isUsed[i] = false
    }
}