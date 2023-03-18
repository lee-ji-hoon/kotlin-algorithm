import java.io.StreamTokenizer


fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val graph = Array(n + 1) { arrayListOf<Int>() }
    val m = input()

    repeat(m) {
        val u = input()
        val v = input()
        graph[u].add(v)
        graph[v].add(u)
    }

    // 각 정점의 방문 여부를 체크하는 배열
    val visited = BooleanArray(n + 1) { false }

    var count = dfs_2606(graph, visited, 1)

    // 1번 컴퓨터는 감염됐으므로 제외하고 출력
    println(count - 1)
}

// DFS 함수
fun dfs_2606(graph: Array<ArrayList<Int>>, visited: BooleanArray, v: Int): Int {
    visited[v] = true

    var count = 1 // 현재 정점을 감염시킨다.

    for (u in graph[v]) {
        if (visited[u].not()) {
            count += dfs_2606(graph, visited, u) // 재귀적으로 방문하고 감염된 컴퓨터 수를 더한다.
        }
    }

    return count // 현재 정점에서 감염된 컴퓨터 수 반환
}