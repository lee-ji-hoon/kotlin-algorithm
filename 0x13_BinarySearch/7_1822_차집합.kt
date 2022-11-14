import java.util.*

// 하나의 집합에 속하는 모든 원소의 값은 다르다 -> 이 부분이 중요했다!
fun main() = with(System.`in`.bufferedReader()) {
    var st = StringTokenizer(readLine(), " ")
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()

    st = StringTokenizer(readLine(), " ")
    val a = IntArray(n) { st.nextToken().toInt() }

    var st2 = StringTokenizer(readLine(), " ")
    val b = IntArray(m) { st2.nextToken().toInt() }

    // 각 배열 오름차순으로 정렬
    a.sort()
    b.sort()

    // a와 b 비교 하기
    // a의 각 원소가 b의 비교 대상 원소보다 클 때는 b의 포인터 증가
    // b의 원소가 a 원소보다 클때에는 결과(result에 저장) 후 a의 포인터 1증가
    // a와 b의 원소의 값이 동일하다면 두 포인터 모두 증가
    var aPointer = 0
    var bPointer = 0

    val result = ArrayList<Int>()

    while (aPointer < n) {
        if (bPointer >= m) {
            result.add(a[aPointer++])
            continue
        }
        if (bPointer < m) {
            when {
                a[aPointer] > b[bPointer] -> bPointer++
                a[aPointer] == b[bPointer] -> {
                    aPointer++
                    bPointer++
                }
                else -> result.add(a[aPointer++])
            }
        }
    }

    // 2 5 7 11
    // 4 7 9
    // 2 < 4 이므로 결과에 2를 저장하고 a의 포인터 증가
    // 5 > 4 b의 포인터만 증가
    // 5 < 7 5를 결과에 저장 a의 포인터 증가

    val sb = StringBuffer()
    sb.append(result.size).append("\n")
    for (num in result) {
        sb.append(num).append(" ")
    }
    println(sb.toString())
}