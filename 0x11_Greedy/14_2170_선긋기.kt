import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()

    val xyList = arrayListOf<Pair<Long, Long>>()

    repeat(n) {
        val st = StringTokenizer(readLine(), " ")
        xyList.add(Pair(st.nextToken().toLong(), st.nextToken().toLong()))
    }

    xyList.sortWith(compareBy<Pair<Long, Long>> { it.first }.thenByDescending { it.second })

    var end = xyList[0].second
    var start = xyList[0].first
    var curSum = xyList[0].second - xyList[0].first

    // 한 선분이 다른 선분을 포함하면 작은 선분 무시
    // 겹치면 start 지점과 end 지점 에서 중복되는 지점 빼주기
    // 아예 안겹치면 그냥 둘 다 더하자.
    for (i in 1 until n) {
        val cur = xyList[i]
        val nextStart = cur.first
        val nextEnd = cur.second

        // 선 포함
        curSum += if ((start <= nextStart) && (nextEnd <= end)) {
            continue
        } else if (nextStart < end) { // 이전에 start 있는지 확인
            -(end - nextStart) + (nextEnd - nextStart)
        } else {
            nextEnd - nextStart
        }
        start = nextStart
        end = nextEnd
    }
    println(curSum)
}

/**
4
5 10
15 20
25 30
7 35
 */