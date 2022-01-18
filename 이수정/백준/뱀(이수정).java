import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.LinkedList;
import java.util.List;

public class 뱀 {
    static int N;
    static int K;
    static int L;
    static int time = 0;
    static int index = 0;

    static int[] dx = {0, 1, 0, -1}; //세로
    static int[] dy = {1, 0, -1, 0}; //가로

    static int[][] map;
//    static List<int[]> snake;
    static List<Point> snake;
    static Map<Integer, String> rotate; // 뱀의 방향 정보

    public static void main(String[] args) throws Exception, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        map = new int[N][N];

        // 사과 위치
        for(int i=0; i<K; i++) {
            String str = br.readLine();
            map[Integer.parseInt(str.split(" ")[0])-1][Integer.parseInt(str.split(" ")[1])-1] = 2;
        }

        // 뱀 방향
        rotate = new HashMap<>();
        L = Integer.parseInt(br.readLine());
        for(int i=0; i<L; i++) {
            String str = br.readLine();
            rotate.put(Integer.parseInt(str.split(" ")[0]), str.split(" ")[1]);
        }
        BFS();
    }
    static void BFS(){
        int x = 0; int y = 0;
        snake = new LinkedList<>();
//        snake.add(new int[]{x,y});
        snake.add(new Point(x, y));
        int nx, ny;
        // 뱀 움직임 시작
        while(true) {
            time++;
            nx = x + dx[index];
            ny = y + dy[index];
            if(isBoolean(nx,ny)) break;
            // 사과 있는지
            if(map[nx][ny] == 2) {
                map[nx][ny] = 0;
//                snake.add(new int[] {nx, ny});
                snake.add(new Point(nx, ny));
            }
            else {
//                snake.add(new int[] {nx, ny});
                snake.add(new Point(nx, ny));
                snake.remove(0);
            }
            x = nx;
            y = ny;
            timeRotate();
        }
        System.out.println(time);
    }
    // 게임 시작 시간으로부터 X초가 끝난 뒤
    static void timeRotate() {
        if(rotate.containsKey(time)) {
            if(rotate.get(time).equals("D")) {
                index++;
                if(index == 4)
                    index = 0;
            }
            if(rotate.get(time).equals("L")) {
                index--;
                if(index == -1)
                    index = 3;
            }
        }
    }
    static boolean isBoolean(int nx, int ny){
        //벽에 부딪힘
        if(nx < 0 || ny < 0 || nx >= N || ny >= N) {
            return true;
        }
        //자기 몸에 부딪힘
        for(int i=0; i<snake.size(); i++) {
            if(nx == snake.get(i).x && ny == snake.get(i).y)
                return true;
        }
        return false;
    }

}
