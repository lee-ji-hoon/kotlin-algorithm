import java.util.*
import kotlin.text.StringBuilder

private lateinit var arr: IntArray
private lateinit var isUsed: BooleanArray
private val sb = StringBuilder()
private var N = 0
private var M = 0

fun main() = with(System.`in`.bufferedReader()) {
    // 1. 입력 받기
    val input = StringTokenizer(readLine(), " ")
    N = input.nextToken().toInt()
    M = input.nextToken().toInt()

    arr = IntArray(M)
    isUsed = BooleanArray(N + 1)

    dfs(0)
    println(sb.toString())
}

fun dfs(depth: Int) {
    // 2. 내가 원하는 수(M)만큼 depth가 들어갔으면 더 이상 값을 조합할 필요가 없어지므로 StringBuilder에 누적
    if (depth == M) {
        arr.forEach {
            sb.append(it).append(' ')
        }
        sb.append("\n")
        return
    }

    // 3. 아직 내가 원하는 만큼(M) depth이 되지 않았다면 1 ~ N까지 아직 안들린곳의 수를 추가하고 dfs 다시 시작
    for (i in 1..N) {
        if (isUsed[i]) continue

        isUsed[i] = true
        arr[depth] = i
        dfs(depth + 1)
        // 4. dfs가 끝나고 해당 함수로 다시 돌아왔을때 visit은 다시 false로 바꿔줘야 한다.
        // 이 부분을 놓쳐서 해결을 못했었다.
        isUsed[i] = false
    }
}
