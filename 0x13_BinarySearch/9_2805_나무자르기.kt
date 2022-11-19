
import java.util.*
import kotlin.math.max

// 처음 풀이에 비해서 더 좋은 글을 발견해서 참고 링크 남깁니다!
// https://st-lab.tistory.com/270
fun main() = with(System.`in`.bufferedReader()) {
    var st = StringTokenizer(readLine(), " ")
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val tree = IntArray(N)

    st = StringTokenizer(readLine(), " ")

    var min = 0
    var max = 0

    for (i in 0 until N) {
        tree[i] = st.nextToken().toInt()

        max = max(tree[i], max)
    }

    // 이분 탐색 (upper bound)
    while (min < max) {
        val mid = (min + max) / 2
        var sum: Long = 0
        for (treeHeight in tree) {
            /*
             *  tree의 잘린 길이 = tree의 높이 - 자르는 위치(mid)
             *  tree의 잘린 길의의 합을 구한다.
             *  이 때 자르는 위치가 주어진 나무의 높이보다 클 수 있기 때문에
             *  0 이하인 경우(=음수)에는 합산을 하지 않고 양수만 합산하도록 해야한다.
             */
            if (treeHeight - mid > 0) {
                sum += (treeHeight - mid).toLong()
            }
        }

        /*
         * 자른 나무 길의의 합이 M보다 작다는 것은
         * 자르는 위치(높이)가 높다는 의미이므로 높이를 낮춰야 한다.
         * 즉, 상한(max)를 줄여야 한다.
         */
        if (sum < M) {
            max = mid
        } else {
            min = mid + 1
        }
    }
    println(min - 1)
}
