import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val a = IntArray(n) { input() }

    val op = IntArray(4) { input() } // +, -, *, /

    var max = Int.MIN_VALUE // 최댓값
    var min = Int.MAX_VALUE // 최솟값

    // DFS 함수 정의
    fun dfs(i: Int, res: Int) {
        // 모든 연산자의 조합을 사용한 경우 최댓값과 최솟값 갱신
        if (i == n) {
            max = max.coerceAtLeast(res)
            min = min.coerceAtMost(res)
            return
        }

        // 모든 연산자의 조합을 구하기 위해 DFS 탐색
        for (j in 0..3) {
            if (op[j] > 0) {
                op[j]--
                when (j) {
                    0 -> dfs(i + 1, res + a[i]) // +
                    1 -> dfs(i + 1, res - a[i]) // -
                    2 -> dfs(i + 1, res * a[i]) // *
                    3 -> dfs(i + 1, res / a[i]) // /
                }
                op[j]++
            }
        }
    }

    dfs(1, a[0]) // 첫 번째 숫자는 미리 더해놓음
    println(max)
    println(min)
}