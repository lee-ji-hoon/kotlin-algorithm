import java.io.StreamTokenizer
import java.util.*
import kotlin.collections.ArrayList

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input() // 정점의 개수
    val m = input() // 간선의 개수
    val v = input() // 시작 정점
    val graph = Array(n + 1) { arrayListOf<Int>() } // 인접 리스트를 이용한 그래프
    val visited = BooleanArray(n + 1) // 방문 여부 체크 배열

    repeat(m) {
        val u = input()
        val w = input()
        graph[u].add(w)
        graph[w].add(u) // 무방향 그래프이므로 양방향으로 추가
    }

    // 인접 리스트의 각 리스트 내부의 정점을 번호가 작은 순서로 정렬
    for (i in 1..n) {
        graph[i].sort()
    }

    dfs_1260(graph, visited, v) // DFS 수행
    println() // 줄바꿈

    visited.fill(false) // 방문 여부 체크 배열 초기화

    bfs(graph, visited, v) // BFS 수행
}

// DFS 함수
fun dfs_1260(graph: Array<ArrayList<Int>>, visited: BooleanArray, v: Int) {
    visited[v] = true
    print("$v ") // 정점 출력

    // 인접한 정점에 대해 재귀적으로 DFS 수행
    for (u in graph[v]) {
        if (visited[u].not()) {
            dfs_1260(graph, visited, u)
        }
    }
}

// BFS 함수
fun bfs(graph: Array<ArrayList<Int>>, visited: BooleanArray, v: Int) {
    val queue = LinkedList<Int>()
    visited[v] = true
    queue.offer(v)

    // 큐가 빌 때까지 반복
    while (queue.isNotEmpty()) {
        val u = queue.poll() // 큐에서 정점 추출
        print("$u ") // 정점 출력

        // 인접한 정점 중에서 방문하지 않은 정점을 큐에 삽입
        for (w in graph[u]) {
            if (visited[w].not()) {
                visited[w] = true
                queue.offer(w)
            }
        }
    }
}
