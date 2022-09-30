import java.util.*

private val sb = StringBuilder()
fun main() = with(System.`in`.bufferedReader()) {
    while (true) {
        val st = StringTokenizer(readLine(), " ")
        // 1. 첫 숫자가 array의 길이다.
        val n = st.nextToken().toInt()
        if (n == 0) break
        val roto = IntArray(n) { st.nextToken().toInt() }
        rotoSolution(0, 0, roto, IntArray(6))
        println()
    }
}

fun rotoSolution(start: Int, depth: Int, roto: IntArray, answer: IntArray) {
    // 2. 내가 원하는 depth 까지 도착하면 answer 출력
    if (depth == 6) {
        for (i in answer) {
            print("${i} ")
        }
        println()
        return
    }

    // 3. 중복조합이 아니므로 start를 따로 지정해서 하자!
    for (i in start until roto.size) {
        // 4. 이번꺼는 굳이 boolean 값으로 체크할 필요도 없어보이고 array를 계속 덮어씌우는 방식으로 하면될거 같다.
        answer[depth] = roto[i]
        rotoSolution(i + 1, depth + 1, roto, answer)
    }
}

