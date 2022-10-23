fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()

    val ropes = IntArray(n)
    for(i in 0 until n) {
        ropes[i] = readLine().toInt()
    }

    // 1. 중량을 많이 달 수 있는 순으로 정렬
    val ropesSorted = ropes.sortedDescending()

    // 2. 현재 로프에서 다음 로프를 추가했을대 최대 무게가 증가하면 계속 진행하고 아니면 끝내고 현재 최대 무게 출력
    var curWeight = ropesSorted[0]
    var curRopesSum = ropesSorted[0]

    for (i in 1 until n) {
        curRopesSum += ropesSorted[i]
        val temp = ropesSorted[i] * (i + 1) // 정렬을 했으므로 (현재 로프 최대 무게 * 로프 사용 수) 최대라고 인지
        if (temp >= curWeight) {
            curWeight = temp
        }

    // else break -> 4 30 10 9 8 했을 때 반례가 생긴다.. 무게가 줄어들면 더 이상 볼 필요가 없다고 생각했었는데 아래와 같은 반례가 생긴다.
    // 30 -> 20 > 27 -> 32 순으로 변화하기 때문에 마지막 32가 정답이 된다. 그러므로 중간에 끝내지말고 끝까지 봐야 한다.
    }
    println(curWeight)
}