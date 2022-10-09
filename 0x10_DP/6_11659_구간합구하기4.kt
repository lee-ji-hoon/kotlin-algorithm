import java.util.*

/**
 * 만약 4~5번째 수열의 값을 알고 싶다.
 * 그러면 1~5번째 값에서 1~3번째 값을 빼면 되는 것이다.
 * 이러한 접근 방식을 위해서 sum(IntArray)를 만들고 미리 합들을 저장했다.
 *
 * 1번째에는 수열의 첫번째
 * 2번째는 1 + 2번째 합
 * 3번째는 1~3번째의 합
 */
fun main() = with(System.`in`.bufferedReader()) {
    var st = StringTokenizer(readLine(), " ")
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()

    val sum = IntArray(n + 1)

    /*st = StringTokenizer(readLine(), " ")
    for (i in 0 until n) {
        sum[i + 1] = sum[i] + st.nextToken().toInt()
    }*/
    // 이거 때문에 시간초과나는거 같아보임...
    readLine().split(" ").map { it.toInt() }.forEachIndexed { index, value ->
        sum[index + 1] = sum[index] + value
    }

    val sb = StringBuilder()
    repeat(m) {
        st = StringTokenizer(readLine(), " ")
        val start = st.nextToken().toInt()
        val end = st.nextToken().toInt()
        sb.appendLine(sum[end].minus(sum[start - 1]))
    }
    println(sb)
}