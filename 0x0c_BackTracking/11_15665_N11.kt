private lateinit var answer: IntArray
private lateinit var isUsed: BooleanArray
private lateinit var inputList: List<Int>
private val sb = StringBuilder()

fun main() = with(System.`in`.bufferedReader()) {
    // 1. 입력 받기
    val (n, m) = readLine().split(" ").map { it.toInt() }
    inputList = readLine().split(" ").map { it.toInt() }.sortedBy { it }
    answer = IntArray(m)
    solution(0, n, m)
    println(sb)
}

private fun solution(depth: Int, n: Int, m: Int) {
    if (depth == m) {
        // 3. StringBuilder에 값 누적
        for (s in answer) {
            sb.append(s).append(" ")
        }
        sb.append("\n")
        return
    }
    // 4. 동일한 경우도 체크해주기 위해서 이전 숫자 갖고있기
    var before = 0
    // 5. 중복도 가능하므로 0부터 시작
    for (i in 0 until n) {
        // 6. 중복되는 수열을 피하되 같은 수는 여러번 골라도 되므로 이전에 사용한 숫자를 굳이 체크할 필요가 없음
        if (before == inputList[i]) continue
        answer[depth] = inputList[i]
        solution(depth + 1, n, m)
        before = inputList[i]
    }
}
