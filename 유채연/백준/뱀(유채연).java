import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static class game{
		int second;
		String dir;
		
		public game(int second, String dir) {
			this.second = second;
			this.dir = dir;
		}
	}
	public static class direction{
		int x;
		int y;
		
		public direction(int x, int y) {
			this.x=x;
			this.y=y;
		}
	}
	
	public static int time = 0;
	public static int index = 0;
	public static int[] dx = {0, 1, 0, -1};
	public static int[] dy = {1, 0, -1, 0};
	public static List<direction> snake = new LinkedList<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());
		
		int[][] arr = new int[n][n];
		
		for(int i=0; i<k; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			arr[x][y] = 1;
		}
		
		int l = Integer.parseInt(br.readLine());
		Queue<game> q = new LinkedList<>();
		
		for(int i=0; i<l; i++) {
			st = new StringTokenizer(br.readLine());
			int second = Integer.parseInt(st.nextToken());
			String dir = (st.nextToken());
			q.add(new game(second, dir));
		}
		
		snake.add(new direction(0, 0));
		int nx=0, ny=0;
		
		while(true) {
			time++;
			
			nx += dx[index];
			ny += dy[index];
			
			boolean check=true;
			if(nx<0 || ny<0 || nx>=n || ny>=n)	break;
			for(int i=0; i<snake.size(); i++) {
				if(nx==snake.get(i).x && ny==snake.get(i).y) check=false;
			}
			if(!check) break;
						
			//사과가 있을 경우
			if(arr[nx][ny]==1) {
				arr[nx][ny] = 0;
				snake.add(new direction(nx, ny));
			}else {
				snake.add(new direction(nx, ny));
				snake.remove(0);
			}
			
			if(!q.isEmpty() && q.peek().second == time) {
				game g = q.poll();
				//오른쪽
				if(g.dir.equals("D")) {
					if(index==3) {
						index = 0;
					}else {
						index++;
					}
				}else if(g.dir.equals("L")){
					if(index==0) {
						index=3;
					}else {
						index--;
					}
				}
			}
		}
		
		System.out.println(time);
		
	}
	
}
