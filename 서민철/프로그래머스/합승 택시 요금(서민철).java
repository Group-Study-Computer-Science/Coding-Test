import java.util.*;
import java.io.*;

class Solution {
	public int[][] edge;

	public class Node implements Comparable<Node> {
		int v, weight;

		public Node(int v, int weight) {
			this.v = v;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node node) {
			return Integer.compare(this.weight, node.weight);
		}
	}

	public int solution(int n, int s, int a, int b, int[][] fares) {
		edge = new int[n + 1][n + 1];

		for (int y = 1; y <= n; y++) {
			for (int x = 1; x <= n; x++) {
				if (y == x) {
					edge[y][x] = 0;
					continue;
				}
				edge[y][x] = 100000001;
			}
		}

		for (int[] fare : fares) {
			int c = fare[0];
			int d = fare[1];
			int f = fare[2];

			edge[c][d] = f;
			edge[d][c] = f;
		}

		floydWarshall(n);

		int answer = edge[s][a] + edge[s][b];

		for (int i = 1; i <= n; i++) {
			answer = Math.min(answer, edge[s][i] + edge[i][a] + edge[i][b]);
		}

		return answer;
	}

	public void printEdges(int n) {
		System.out.println();

		for (int y = 0; y <= n; y++) {
			for (int x = 0; x <= n; x++) {
				if (y == 0) {
					if (x == 0) {
						System.out.printf(" \t");
						continue;
					}
					System.out.printf("%d\t", x);
				} else if (x == 0) {
					System.out.printf("%d\t", y);
				} else {
					if (edge[y][x] == 100000001) {
						System.out.printf("inf\t");
						continue;
					}
					System.out.printf("%d\t", edge[y][x]);
				}
			}
			System.out.println();
		}
	}

	public void floydWarshall(int n) {
		for (int share = 1; share <= n; share++) {
			for (int start = 1; start <= n; start++) {
				for (int dest = 1; dest <= n; dest++) {
					edge[start][dest] = Math.min(edge[start][dest], edge[start][share] + edge[share][dest]);
				}
			}
		}
	}

	public void dijkstra(int start, int n) {
		boolean[] visited = new boolean[n + 1];

		for (int i = 1; i <= n; i++) {
			visited[i] = false;
		}

		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		pq.add(new Node(start, 0));

		while (!pq.isEmpty()) {
			Node node = pq.poll();

			if (visited[node.v]) {
				continue;
			}

			visited[node.v] = true;

			for (int i = 1; i <= n; i++) {
				if (!visited[i] && edge[node.v][i] <= 100000000) {
					edge[start][i] = Math.min(edge[start][i], edge[start][node.v] + edge[node.v][i]);
					edge[i][start] = edge[start][i];
					pq.add(new Node(i, edge[start][i]));
				}
			}
		}
	}
}