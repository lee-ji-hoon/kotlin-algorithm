private lateinit var arr: IntArray
private lateinit var isUsed: BooleanArray
private lateinit var list: List<String>
var set = LinkedHashSet<String>()

fun main() = with(System.`in`.bufferedReader()) {
    // 1. 입력 받기
    val (n, m) = readLine().split(" ").map { it.toInt() }
    list = readLine().split(" ").sortedBy { it.toInt() }
    arr = IntArray(m)
    isUsed = BooleanArray(n)

    solution(0, n, m)
    for (s in set) {
        println(s)
    }
}

private fun solution(depth: Int, n: Int, m: Int) {
    if (depth == m) {
        // 3. 같은 조합이 들어올수가 없으므로 set으로 걸러주기
        set.add(arr.joinToString(" "))
        return
    }
    list.forEachIndexed { index, s ->
        if (isUsed[index].not()) {
            isUsed[index] = true
            arr[depth] = s.toInt()
            solution(depth + 1, n, m)
            isUsed[index] = false
        }
    }
}
