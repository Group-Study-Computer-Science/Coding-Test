import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, V;
	static boolean[] visited; //방문여부
	static int[][] line;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //정점
		M = Integer.parseInt(st.nextToken()); //간선
		V = Integer.parseInt(st.nextToken()); //시작번호
		visited = new boolean[N+1];
		line = new int[N+1][N+1];
		
		for(int i=0; i<M; i++) {
			String[] node = br.readLine().split(" ");
			int node1 = Integer.parseInt(node[0]);
			int node2 = Integer.parseInt(node[1]);
			line[node1][node2] = 1;
			line[node2][node1] = 1;
		}
		dfs(V); //시작점부터 dfs시작
		sb.append("\n");
		visited = new boolean[N+1];
		bfs(V);
		System.out.println(sb.toString());
	}
	
	//dfs
	public static void dfs(int node) {
		visited[node] = true; //방문하고 
		sb.append(node+" ");
		
		for(int i=1; i<=N; i++) {
			if(!visited[i] && line[node][i]==1 && node!=i)
				dfs(i);
		}
	}
	
	//bfs
	public static void bfs(int node) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(node); //큐에 추가
		visited[node] = true; //방문처리
		sb.append(node+" ");
		
		while(!queue.isEmpty()) {
			int current = queue.poll();
			
			for(int i=1; i<=N; i++) {
				if(line[current][i]==1 && !visited[i] && i!=current) {
					queue.add(i);
					visited[i] = true;
					sb.append(i+" ");
				}
			}
		}
		
	}
}
