import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    // 1. Pair로 묶어서 앞은 시작, 뒤는 종료
    val usingMeetingRooms = mutableListOf<Pair<Int, Int>>()
    for (i in 0 until n) {
        val st = StringTokenizer(readLine(), " ")
        usingMeetingRooms.add(Pair(st.nextToken().toInt(), st.nextToken().toInt()))
    }
    // 2. 정렬할때 종료 시간 순으로 정렬을 하고 만약 같은 시간이면 시작되는 시간을 기준으로 한번 더 정렬
    // 처음에 시작되는 시간을 기준으로 정렬을 해보고, DP로도 풀다가 반대로 하면 되겠다 싶어서 해봤더니 됐습니다 ;)...
    val sorted = usingMeetingRooms.sortedWith(compareBy({ it.second }, { it.first }))
    // 3. for문 돌리면서 찾기
    var startTime = -1
    var count = 0
    for (i in 0 until n) {
        val currentRoomStart = sorted[i].first // 시작 시간
        if (currentRoomStart >= startTime ) {
            startTime = sorted[i].second
            count++
        }
    }
    println(count)
}



