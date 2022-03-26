package 프로그래머스.Level3;

import java.util.LinkedList;
import java.util.Queue;

public class 가장먼노드 {

    public static void main(String[] args) {
        System.out.println(new Solution().solution(6, new int[][]{{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}}));
    }

    static class Solution {

        static int answer;
        static Node[] adjList;

        public int solution(int n, int[][] edge) {
            adjList = new Node[n+1];

            for (int i=0; i<edge.length; i++) {
                int from = edge[i][0];
                int to = edge[i][1];
                adjList[from] = new Node(to, adjList[from]);
                adjList[to] = new Node(from, adjList[to]);
            }
            solve(n, 1);
            return answer;
        }

        public static void solve(int n, int start) {
            Queue<Integer> queue = new LinkedList<>();
            int[] visited = new int[n+1];
            queue.add(start);
            visited[start] = 1; // 방문 표시

            while (!queue.isEmpty()) {
                int current = queue.poll();

                for (Node temp = adjList[current]; temp != null; temp = temp.link) {
                    if (visited[temp.vertex] == 0) {
                        queue.offer(temp.vertex);
                        visited[temp.vertex] = visited[current] + 1;
                    }
                }
            }

            int maxDist = 0;
            for (int i=1; i<=n; i++) {
                if (maxDist < visited[i]) {
                    maxDist = visited[i];
                    answer = 0;
                }
                if (maxDist == visited[i]) {
                    answer += 1;
                }
            }
        }

        public static class Node {
            int vertex;
            Node link;

            public Node(int vertex, Node link) {
                this.vertex = vertex;
                this.link = link;
            }
        }
    }
}
