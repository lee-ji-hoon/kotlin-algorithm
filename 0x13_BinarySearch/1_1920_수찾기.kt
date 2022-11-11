import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    var st = StringTokenizer(readLine(), " ")
    val list = IntArray(n) { st.nextToken().toInt() }
    list.sort()

    val m = readLine().toInt()
    st = StringTokenizer(readLine(), " ")
    while (st.hasMoreTokens()) {
        val binarySearch = Arrays.binarySearch(list, st.nextToken().toInt())
        if (binarySearch >= 0) println(1)
        else println(0)
    }
}

private fun binarySearch(arr: IntArray, from: Int, to: Int, num: Int): Int {
    var left = from
    var right = to - 1

    while (left <= right) {
        val midIndex = (left + right) / 2
        val midValue = arr[midIndex]
        when {
            midValue > num -> right = midIndex - 1
            midValue < num -> left = midIndex + 1
            else -> return midIndex
        }
    }
    return -1 // 못 찾았다는 의미로 -1 반환
}
