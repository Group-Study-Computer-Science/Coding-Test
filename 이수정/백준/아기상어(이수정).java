//testcase는 맞는데 제출하며 틀린이유가 무엇일까

package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class 아기상어 {
    public static int N, cur_x, cur_y, age, map[][];
    public static ArrayList<Node> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        age = 2; // 초기 나이 2
        //입력
        for(int i = 0; i < N; i++) {
            String str[] = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(str[j]);
                //상어가 지나간 자리 -> 0 초기화
                if (Integer.parseInt(str[j]) == 9) {
                    cur_x = i;
                    cur_y = j;
                    map[i][j] = 0;
                }
            }
        }
        BFS(map);
    }
    public static void BFS(int[][] arr) {
        int time = 0;
        int count = 0;
        while(true) {
            Queue<Node> queue = new LinkedList<Node>();
            queue.offer(new Node(cur_x, cur_y, 0));
            //방문 여부 확인
            boolean visited[][] = new boolean[N][N];
            visited[cur_x][cur_y] = true;

            //먹을수 있는 물고기 저장 -> 가장 가까운 거리
            list = new ArrayList<Node>();
            //상 왼 우선순위가 높음
            int dx[] = {-1, 0, 0, 1};
            int dy[] = {0, -1, 1, 0};

            while(!queue.isEmpty()) {
                Node node = queue.poll();
                for(int d = 0; d < 4; d++) { // 상하좌우 탐색
                    int next_x = node.x + dx[d];
                    int next_y = node.y + dy[d];

                    if(next_x >=0 && next_x < N && next_y >=0 && next_y <N && !visited[next_x][next_y]) {
                        if(1 <= arr[next_x][next_y] && arr[next_x][next_y] < age) {
                            queue.add(new Node(next_x, next_y, node.dist + 1));
                            list.add(new Node(next_x, next_y, node.dist + 1));
                            visited[next_x][next_y] = true; //방문 표시
                        }

                        else if(arr[next_x][next_y] == age || arr[next_x][next_y] == 0) {
                            queue.add(new Node(next_x, next_y, node.dist + 1)); // 상어 위치 갱신
                            visited[next_x][next_y] = true; // 방문 표시
                        }
                    }
                }
            }

            //먹을 수 있는 물고기가 없는 경우
            if(list.size() == 0) {
                System.out.println(time);
                return;
            }
            // 먹을 물고기가 있는 경우 -> 짧은 거리
            Node fish = list.get(0);
            for(int i = 1; i < list.size(); i++){
                if(list.get(i).dist < fish.dist) {
                    fish = list.get(i);
                }
                if(fish.dist == list.get(i).dist) {
                    if(fish.x > list.get(i).x){
                        fish = list.get(i);
                    }
                }
            }
            //물고기를 먹으면, 그 칸은 빈 칸이 된다.
            time += fish.dist;
            count++;
            arr[fish.x][fish.y] = 0;
            //아기 상어는 자신의 크기와 같은 수의 물고기를 먹을 때 마다 크기가 1 증가한
            if(count == age) {
                age++;
                count = 0;
            }
            //이동 변경 좌표 갱신
            cur_x = fish.x;
            cur_y = fish.y;
        }
    }
    //가까운 거리를 확인 해야함
    public static class Node {
        int x, y, dist;
        public Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}
