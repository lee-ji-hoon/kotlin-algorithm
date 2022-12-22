import java.util.*
import kotlin.math.min

fun main() {
    var st = StringTokenizer(readLine(), " ")

    val N = st.nextToken().toInt()
    val S = st.nextToken().toInt()

    st = StringTokenizer(readLine(), " ")
    val arr = IntArray(N) { st.nextToken().toInt() }
//    arr.sort() --> 무의식중에 sort를 해버려서 날린 1시간...

    var end = 0
    var answer = Int.MAX_VALUE
    var total = arr[0]
    for (start in 0 until N) {
        while (end < N && total < S) {
            end++
            if (end != N) total += arr[end]
        }
        if (end == N) break
        answer = min(end - start + 1, answer)
        total -= arr[start]
    }
    if (answer == Int.MAX_VALUE) answer = 0
    println(answer)
}
