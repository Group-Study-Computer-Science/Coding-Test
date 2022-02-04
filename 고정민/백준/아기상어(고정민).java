import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] map; //공간
	static int[][] dist; //거리
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int sharkX, sharkY; //아기상어가 있는 좌표
	static int sharkSize = 2; // 아기상어의 크기
	static int minX, minY, minDist;
	static int ans=0, eatingCount=0;
	
	public static class Fish{
		int x;
		int y;
		Fish(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		
		StringTokenizer st;
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] == 9) {
					//아기상어의 좌표를 저장하고 0으로 변환
					sharkX = i;
					sharkY = j;
					map[i][j] = 0;
				}
			}
		}
		
		while(true) {
			dist = new int[N+1][N+1];
			minX = Integer.MAX_VALUE;
			minY = Integer.MAX_VALUE;
			minDist = Integer.MAX_VALUE;
			
			//bfs시작
			bfs(sharkX, sharkY);
			
			if(minX != Integer.MAX_VALUE && minY != Integer.MAX_VALUE) {
				map[minX][minY] = 0;
				eatingCount++;
				sharkX = minX;
				sharkY = minY;
				ans += dist[minX][minY];
				
				if(eatingCount == sharkSize) {
					sharkSize++;
					eatingCount = 0;
				}
			}else {
				break;
			}
			
		}
		System.out.println(ans);
	}
	
	private static void bfs(int x, int y) {
		Queue<Fish> q = new LinkedList<>();
		q.add(new Fish(x, y)); //현재 위치를 큐에 넣는다
		
		while(!q.isEmpty()) {
			Fish f = q.poll();
			
			for(int i=0; i<4; i++) {
				int nx = f.x + dx[i];
				int ny = f.y + dy[i];
				
				if(isInArea(nx, ny) && movable(nx,ny) && dist[nx][ny] == 0) {
					dist[nx][ny] = dist[f.x][f.y] +1;
					if(eatable(nx, ny)) {
						if(minDist > dist[nx][ny]) {
							minDist = dist[nx][ny];
							minX = nx;
							minY = ny;
						}else if(minDist == dist[nx][ny]) {
							if(minX == nx) {
								if(minY > ny) {
									minX = nx;
									minY = ny;
								}
							}else if (minX > nx) {
								minX = nx;
								minY = ny;
							}
						}
					}
					q.add(new Fish(nx, ny));
				}
			}
		}
	}
	
	//이동 가능한지 
	public static boolean movable(int nx, int ny) {
		return map[nx][ny] <= sharkSize;
	}

	//먹을 수 있는지
	public static boolean eatable(int nx, int ny) {
		return map[nx][ny] < sharkSize && map[nx][ny]!=0;
	}
	
	//map안에 있는지
	public static boolean isInArea(int nx, int ny) {
		return nx<=N && nx>0 && ny<=N && ny>0;
	}
	
}
