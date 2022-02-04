import java.io.*;
import java.util.*;

public class Main {
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static int N, M, answer;
	public static int[][] map;
	public static int[] dirY = { -1, 0, 1, 0 };
	public static int[] dirX = { 0, 1, 0, -1 };
	public static final int UP = 0, RIGHT = 1, DOWN = 2, LEFT = 3;
	public static ArrayList<CCTV> cctv;

	public static class CCTV {
		int y, x, type, number;
		boolean[] range;
		boolean[] usable;

		public CCTV(int y, int x, int type, int number) {
			this.y = y;
			this.x = x;
			this.number = number;
			usable = new boolean[4];

			if (type == 1) {
				boolean[] r = { false, true, false, false };
				range = r;
				for (int i = 0; i < 4; i++) {
					usable[i] = range[i];
				}
			} else if (type == 2) {
				boolean[] r = { false, true, false, true };
				range = r;
				for (int i = 0; i < 4; i++) {
					usable[i] = range[i];
				}
			} else if (type == 3) {
				boolean[] r = { true, true, false, false };
				range = r;
				for (int i = 0; i < 4; i++) {
					usable[i] = range[i];
				}
			} else if (type == 4) {
				boolean[] r = { true, true, false, true };
				range = r;
				for (int i = 0; i < 4; i++) {
					usable[i] = range[i];
				}
			} else if (type == 5) {
				boolean[] r = { true, true, true, true };
				range = r;
				for (int i = 0; i < 4; i++) {
					usable[i] = range[i];
				}
			}
		}

		public void rotate(int n) {
			usable[UP] = range[(UP - n + 4) % 4];
			usable[RIGHT] = range[(RIGHT - n + 4) % 4];
			usable[DOWN] = range[(DOWN - n + 4) % 4];
			usable[LEFT] = range[(LEFT - n + 4) % 4];
		}

		public void getState() {
			System.out.printf("%d번 카메라(%d, %d) 감시 범위: ", number, y, x);

			if (usable[UP]) {
				System.out.printf("상 ");
			}
			if (usable[RIGHT]) {
				System.out.printf("우 ");
			}
			if (usable[LEFT]) {
				System.out.printf("좌 ");
			}
			if (usable[DOWN]) {
				System.out.printf("하 ");
			}
			System.out.println();
		}

		// CCTV를 설치한다.
		public void install() {
			for (int i = 0; i < 4; i++) {
				if (usable[i]) {
					for (int j = 0; j < 8; j++) {
						int tmpY = y + dirY[i] * j;
						int tmpX = x + dirX[i] * j;

						if (visitable(tmpY, tmpX)) {
							if (map[tmpY][tmpX] <= 0) {
								map[tmpY][tmpX] -= 1;
							}
						} else {
							break;
						}
					}
				}
			}
		}

		// CCTV를 설치한다.
		public void remove() {
			for (int i = 0; i < 4; i++) {
				if (usable[i]) {
					for (int j = 0; j < 8; j++) {
						int tmpY = y + dirY[i] * j;
						int tmpX = x + dirX[i] * j;

						if (visitable(tmpY, tmpX)) {
							if (map[tmpY][tmpX] < 0) {
								map[tmpY][tmpX] += 1;
							}
						} else {
							break;
						}
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		cctv = new ArrayList<CCTV>();
		answer = N * M;

		for (int y = 0; y < N; y++) {
			st = new StringTokenizer(br.readLine(), " ");

			for (int x = 0; x < M; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());

				if (0 < map[y][x] && map[y][x] <= 5) {
					cctv.add(new CCTV(y, x, map[y][x], cctv.size()));
				}
			}
		}

		DFS(0);

		bw.write(String.valueOf(answer));

		bw.flush();
		bw.close();
		br.close();
	}

	public static boolean visitable(int y, int x) {
		return (0 <= y) && (y < N) && (0 <= x) && (x < M) && (map[y][x] < 6);
	}

	public static void DFS(int depth) {
		if (depth == cctv.size()) {
			// 사각지대 크기 체크
			// printMap();
			answer = Math.min(answer, check());
			return;
		}

		CCTV tv = cctv.get(depth);

		for (int i = 0; i < 4; i++) {
			tv.rotate(i);
			// tv.getState();
			tv.install();
			// System.out.printf("%d번 카메라 설치\n", depth);
			// printMap();
			DFS(depth + 1);
			tv.remove();
			// System.out.printf("%d번 카메라 제거\n", depth);
			// printMap();
		}
	}

	public static void printMap() {
		System.out.println();
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < M; x++) {
				if (map[y][x] < 0) {
					System.out.printf("# ");
				} else {
					System.out.printf("%d ", map[y][x]);
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	public static int check() {
		int n = 0;

		for (int y = 0; y < N; y++) {
			for (int x = 0; x < M; x++) {
				if (map[y][x] == 0) {
					n += 1;
				}
			}
		}

		return n;
	}
}