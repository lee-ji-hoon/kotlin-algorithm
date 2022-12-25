import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()

    val st = StringTokenizer(readLine(), " ")
    val arr = Array(n) { st.nextToken().toInt() }
    var isVisited = BooleanArray(100_001)

    var end = 0
    var answer = 0L

    // start부터 시작해서 같은 숫자가 나올때까지 end 증가
    // end가 더 갈 수 없다면 end - start 값을 누적
    // 1,2,3,4,5 라면 start는 0일때 end가 5까지 진행
    // 1, 12, 123, 1234, 12345 5개 수열이 생성될 수 있다.
    for (start in 0 until n) {
        while (end < n) {
            if (isVisited[arr[end]]) break
            isVisited[arr[end++]] = true
        }
        answer += (end - start)
        isVisited[arr[start]] = false
    }
    println(answer)
}
