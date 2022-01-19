import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class 이분그래프 {
    static int K;
    static int V;
    static int E;
    static int visited[];
    static LinkedList<Integer>[] graph; //연걸 값의 정보
    static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //테스트 케이스
        K = Integer.parseInt(br.readLine());
        for(int i = 0; i < K; i++){
            String str = br.readLine();
            V = Integer.parseInt(str.split(" ")[0]);
            E = Integer.parseInt(str.split(" ")[1]);

            graph = new LinkedList[V];
            for(int j =0; j < V; j++){
                graph[j] = new LinkedList<>();
            }

            //간선에 대한 정보
            for(int j = 0; j < E; j++){
                str = br.readLine();
                int s = Integer.parseInt(str.split(" ")[0])-1;
                int e = Integer.parseInt(str.split(" ")[1])-1;
                //이분 그래프 서로가 연결이 되야함
                graph[s].add(e);
                graph[e].add(s);
            }
            visited = new int[V];
            BFS();
        }

    }
    static void BFS(){
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < V; i++){
            if(visited[i] == 0) {
                queue.offer(i);
                visited[i] = 1;
            }
            while (!queue.isEmpty()) {
                int node = queue.poll();
                for(int next : graph[node]){
                    if(visited[next] == visited[node]) {
                        System.out.println("NO");
                        return;
                    }
                    if (visited[next] == 0) {
                        queue.offer(next);
                        if(visited[node] == 1){
                            visited[next] = 2;
                        } else {
                            visited[next] = 1;
                        }
                    }
                }
            }
        }
        System.out.println("YES");
    }
}
