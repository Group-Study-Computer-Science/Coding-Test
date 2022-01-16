package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int max_int = 22;
    static int max_dist = 401;

    static int[][] board = new int[22][22];
    static int[][] visited = new int[22][22];
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    static int n;
    static int shark_size, cnt, result;
    static int min_x,min_y,min_d;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        int shark_x = 0;
        int shark_y = 0;
        for(int i=0;i<n;i++) {
            String[] line  = br.readLine().split(" ");
            for(int j=0;j<n;j++) {
                board[i][j] = Integer.parseInt(line[j]);

                if(board[i][j]==9) {
                    board[i][j]=0;
                    shark_x = i;
                    shark_y = j;
                }
            }
         }
        shark_size = 2;
        cnt = 0;

        while(true) {
            init();

            bfs(shark_x,shark_y);
            //먹을 수 있는 물고기 위치 선정

            if(min_x !=max_int &&  min_y!=max_int) {
                //먹을 수 있는 물고기가 있는 경우
                board[min_x][min_y] = 0;
                cnt++;
                if (cnt == shark_size) {
                    shark_size++;
                    cnt = 0;
                }
                result += visited[min_x][min_y];
                shark_x = min_x;
                shark_y = min_y;
            }else {
                break;
            }
        }
        System.out.println(result);
    }

    static void init() {
        min_x = max_int;
        min_y = max_int;
        min_d = max_dist;
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                visited[i][j] = -1;
            }
        }
    }

    static void bfs(int x,int y) {
        Queue<Pair> q = new LinkedList<>();
        visited[x][y] = 0;
        q.add(new Pair(x, y));

        while (!q.isEmpty()) {
            Pair cur = q.poll();

            for (int dir = 0; dir < 4; dir++) {
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if (board[nx][ny] > shark_size || visited[nx][ny] != -1) continue;
                visited[nx][ny] = visited[cur.x][cur.y] + 1;

                if (board[nx][ny] < shark_size && board[nx][ny] > 0) {
                    //먹을 수 있는 경우
                    if (min_d > visited[nx][ny]) {
                        min_x = nx;
                        min_y = ny;
                        min_d = visited[nx][ny];
                    } else if (min_d == visited[nx][ny]) {
                        if (min_x == nx) {
                            if (min_y > ny) {
                                min_y = ny;
                            }
                        } else if (min_x > nx) {
                            min_x = nx;
                            min_y = ny;
                        }
                    }
                }
                q.add(new Pair(nx, ny));
            }
        }
    }

    static class Pair {
        int x;
        int y;
        Pair(int x,int y) {
            this.x = x;
            this.y = y;
        }
    }
}
