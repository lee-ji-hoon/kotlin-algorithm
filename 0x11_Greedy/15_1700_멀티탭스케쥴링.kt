import java.util.*

/** 최적 페이지 교체 알고리즘(Optimal Page Replacement, OPT)
 * 페이지 교체가 필요할 때 앞으로 가장 멀리 사용될 페이지를 뺀다.
 * 하지만 현실에서는 사용자가 앞으로 어떤 페이지를 방문할지 모르기에 이론적으로만 가능해보인다.
 *
 * 뭐 이딴 알고리즘이 있지?
 */

// 수 - 금 했지만 못 푼 문제
fun main() = with(System.`in`.bufferedReader()) {
    val st = StringTokenizer(readLine(), " ")
    val n = st.nextToken().toInt()
    val k = st.nextToken().toInt()

    val plugs = StringTokenizer(readLine(), " ").run { IntArray(k) { nextToken().toInt() } }
    val isUsed = BooleanArray(k + 1)

    var answer = 0

    plugs.forEachIndexed { index, plug ->
        // 이미 꽂혀있을수도 있다.
        if (isUsed[plug].not()) {
            // 비어 있는 곳이 있다면 넣어주고 아니면 제거하는 과정을 거치자
            if (isUsed.count { it } < n) {
                isUsed[plug] = true
            } else {
                val list = mutableSetOf<Int>()
                for (next in index + 1 until k) {
                    // 다음으로 올 플러그를 검사
                    // 단 나오는 순서대로 넣고 한 번 리스트에 넣었으면 넣을 필요가 없음
                    if (isUsed[plugs[next]]) list.add(plugs[next])
                }

                if (list.size != n) {
                    // 나중에 사용되지 않을 콘센트를 제거 해야 한다.
                    for ((nextPlug, use) in isUsed.withIndex()) {
                        if (use && nextPlug !in list) {
                            isUsed[nextPlug] = false
                            break
                        }
                    }
                } else {
                    // 만약 지금 꽂혀있는 플러그가 모두 다시 나온다면 가장 마지막에 사용할 플러그를 제거해야 한다.
                    isUsed[list.last()] = false
                }
                isUsed[plug] = true
                answer += 1
            }
        }
    }
    println(answer)
}

/**
2 10
1 1 2 1 1 2 1 1 2 1
 */