import java.util.*
import kotlin.math.min

fun main() = with(System.`in`.bufferedReader()) {
    val st = StringTokenizer(readLine(), " ")
    val N = st.nextToken().toInt() // 3
    val M = st.nextToken().toInt() // 2
    val arr = IntArray(N)

    for (i in 0 until N) {
        arr[i] = readLine().toInt()
    }

    // 오름차순으로 정렬하면 1,2,3,4,5가 된다.
    arr.sort()

    var i = 0
    var j = 0
    var ans = Int.MAX_VALUE

    // arr[i] - arr[j] >= M 을 만족하는지 순차적으로 체크해야 한다.
    // 처음은 0이니까 바로 오른쪽으로 이동
    // arr[1] - arr[0] = 1 이라서 얘도 M보다 작다.
    // arr[2] - arr[0] = 2 M보다 작거나 같아서 min(ans, a[2]-a[0]) 처리 해준다.
    // 만족을 시켰으므로 i를 1증가 시켜서 이동시킵니다.
    // 이런식으로 쭉 가면 됩니다.
    while (i < N) {
        if (arr[i] - arr[j] < M) {
            i++
            continue
        }
        if (arr[i] - arr[j] == M) {
            ans = M
            break
        }
        ans = min(ans, arr[i] - arr[j])
        j++
    }
    println(ans)
}
