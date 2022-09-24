import java.util.*

private lateinit var answer: IntArray
private lateinit var isUsed: BooleanArray
private lateinit var inputList: List<Int>
private val sb = StringBuilder()

fun main() = with(System.`in`.bufferedReader()) {
    // 1. 입력 받기
    val (n, m) = readLine().split(" ").map { it.toInt() }
    inputList = readLine().split(" ").map{it.toInt()}.sortedBy { it }
    answer = IntArray(m)
    solution(0, 0, n, m)
    println(sb)
}

private fun solution(depth: Int, start:Int, n: Int, m: Int) {
    if (depth == m) {
        // 3. StringBuilder에 값 누적
        for(s in answer) {
            sb.append(s).append(" ")
        }
        sb.append("\n")
        return
    }
    // 중복되는 수열 걸러주기 위함
    var before = 0
    for(i in start until n) {
        if (before == inputList[i]) continue
        before = inputList[i]
        answer[depth] = inputList[i]

        solution(depth + 1 , i , n , m) // start를 어떻게 할당을 해줘야할까 많은 고민끝에 비내림차순이므로 현재 i를 넘겨주는 방식을 사용
    }
}
