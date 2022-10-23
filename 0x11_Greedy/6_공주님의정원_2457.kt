import java.util.*

data class Flower(var start: Int, var end: Int)

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    var st: StringTokenizer
    val flowers = arrayListOf<Flower>()
    repeat(n) {
        st = StringTokenizer(readLine(), " ")

        // 원래는 월 일 각각 class로 만들어서 처리했었는데 하나의 수로 계산하는 방법도 있어서 도전해봄
        val startMonth = st.nextToken().toInt()
        val startDay = st.nextToken().toInt()
        val endMonth = st.nextToken().toInt()
        val endDay = st.nextToken().toInt()
        val start = startMonth * 100 + startDay
        val end = endMonth * 100 + endDay
        flowers.add(Flower(start, end))
    }

    val flowersSorted = flowers.sortedWith(compareBy({ it.start }, { it.end }))

    val endDay = 1201
    var startDay = 301
    var count = 0
    var max = 0
    var index = 0

    // 현재 종료일이 12월 1일보다 적은 경우만 보기
    // 12월 1일 지났으면 어차피 충족함
    while (startDay < endDay) {
        var isFind = false // 새 꽃 찾기
        for (i in index until n) {
            // 종료일보다 시작일이 이후면 의미없음.
            // 종료일에는 시작해야 이어지기 때문에
            if (flowersSorted[i].start > startDay) {
                break
            }
            if (max < flowersSorted[i].end) {
                isFind = true
                max = flowersSorted[i].end
                index = i + 1
            }
        }
        if (isFind) {
            startDay = max
            count++
        } else {
            break
        }
    }
    if (max < endDay) {
        println(0)
    } else {
        println(count)
    }
}
