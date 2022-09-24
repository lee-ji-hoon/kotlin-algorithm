private lateinit var answer: IntArray
private lateinit var isUsed: BooleanArray
private lateinit var inputList: List<Int>
private val sb = StringBuilder()

fun main() = with(System.`in`.bufferedReader()) {
    // 1. 입력 받기
    val (n, m) = readLine().split(" ").map { it.toInt() }
    inputList = readLine().split(" ").map{it.toInt()}.sortedBy { it }
    answer = IntArray(m)
    isUsed = BooleanArray(n + 1)

    solution(0, 0, n, m)
    println(sb)
}

private fun solution(depth: Int, start:Int, n: Int, m: Int) {
    if (depth == m) {
        // 3. 출력하기 위해서 StirngBuilder에 누적
        for(s in answer) {
            sb.append(s).append(" ")
        }
        sb.append("\n")
        return
    }
    // 4. 동일한 경우도 체크해주기 위해서 이전 숫자 갖고있기
    var before = 0
    // 5. 사전 순으로 증가하는 것이므로 지금 현재 들린 Node 이전 값들을 확인할 필요가 없음
    for(i in start until n) {
        if (isUsed[i].not()) {
            if (before == inputList[i]) continue
            isUsed[i] = true
            answer[depth] = inputList[i]
            solution(depth + 1 , i + 1 , n , m)
            isUsed[i] = false
            before = inputList[i]
        }
    }
}
