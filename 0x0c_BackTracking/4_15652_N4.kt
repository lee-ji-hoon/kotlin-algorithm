import java.util.*

private lateinit var arr: IntArray
private val sb = StringBuilder()
private var N = 0
private var M = 0

fun main() = with(System.`in`.bufferedReader()) {
    // 1. 입력 받기
    val input = StringTokenizer(readLine(), " ")
    N = input.nextToken().toInt()
    M = input.nextToken().toInt()
    arr = IntArray(M)

    Solution().solution(0)
    println(sb.toString())
}

class Solution() {
    fun solution(depth: Int) {
        // 2. depth 체크 후 stringBuilder 누적
        if (depth == M) {
            arr.forEach {
                sb.append(it).append(' ')
            }
            sb.append("\n")
            return
        }
        // 3. 내림차순으로 누적하면 되니까 1~N까지 그리고 이미 사용했는지 체크할 필요가 없음
        for (i in 1..N) {
            // 4. 현재 숫자가 이전 숫자보다 작거나 같은지 확인 (비내림차순)
            if (depth != 0 && arr[depth - 1] > i) continue
            arr[depth] = i
            solution(depth + 1)
        }
    }
}
