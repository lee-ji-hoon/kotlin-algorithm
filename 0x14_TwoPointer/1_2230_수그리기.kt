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
    // 1. start end 지점이 존재한다.
    var end = 0
    var ans = Int.MAX_VALUE

    // 2. start는 0부터 배열의 끝(N)까지 진행
    for (start in 0 until N) {
        // 3. end 값이 N보다 작고 arr[end], [start] 값이 M보다 작다면 end를 한 칸 이동시킨다.
        while (end < N && arr[end] - arr[start] < M) end++
        // 4. 만약 end가 N과 같아졌다 -> 배열의 끝까지 왔다는 의미이므로 break 시켜준다.
        if (end == N) break
        // 5. 여기까지 왔다는 것은 arr[end] - arr[start]가 M보다 크거나 같다는 의미이므로 answer 값 갱신
        ans = min(ans, arr[end] - arr[start])
    }

    println(ans)
}
