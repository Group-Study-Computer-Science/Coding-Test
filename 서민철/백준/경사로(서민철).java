import java.io.*;
import java.util.*;

public class Main {
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static int N, L, way;
	public static int[][] map;
	public static final int ROW = 0;
	public static final int COL = 1;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		way = 2 * N;

		for (int y = 0; y < N; y++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int x = 0; x < N; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < N; i++) {
			check(i, ROW);
			check(i, COL);
		}

		bw.write(String.valueOf(way));

		bw.flush();
		bw.close();
		br.close();
	}

	public static void check(int n, int type) {
		if (type == ROW) {
			int now = map[n][0];
			int cnt = L;
			int move = 1;

			for (int x = 1; x < N; x++) {
				// 경사로를 사용하는 중이 아님
				if (cnt == L) {
					// 경사로를 사용해야 함
					if (now != map[n][x]) {
						// 경사로를 설치할 수 있음
						if (Math.abs(now - map[n][x]) == 1) {
							// 오르막길이라면
							if (now < map[n][x]) {
								if (move >= L) {
									now = map[n][x];
									move = 1;
								}

								else {
									way -= 1;
									return;
								}
							}

							// 내리막길이라면
							else {
								now = map[n][x];
								cnt -= 1;
							}

						}
						// 경사로를 설치할 수 없음
						else {
							way -= 1;
							return;
						}
					}

					else {
						move += 1;
					}
				}

				// 경사로를 사용하는 중임
				else {
					// 경사로를 설치하는 길의 높이가 일정하지 않은 경우
					if (now != map[n][x]) {
						way -= 1;
						return;
					}

					else {
						// 경사로를 설치하는 길의 높이가 일정한 경우
						cnt -= 1;
					}
				}

				if (cnt == 0) {
					cnt = L;
					move = 0;
				}
			}

			if (cnt != L) {
				way -= 1;
				return;
			}
		}

		else {
			int now = map[0][n];
			int cnt = L;
			int move = 1;

			for (int y = 1; y < N; y++) {
				// 경사로를 사용하는 중이 아님
				if (cnt == L) {
					// 경사로를 사용해야 함
					if (now != map[y][n]) {
						// 경사로를 설치할 수 있음
						if (Math.abs(now - map[y][n]) == 1) {
							// 오르막길이라면
							if (now < map[y][n]) {
								if (move >= L) {
									now = map[y][n];
									move = 1;
								}

								else {
									way -= 1;
									return;
								}
							}

							// 내리막길이라면
							else {
								now = map[y][n];
								cnt -= 1;
							}

						}
						// 경사로를 설치할 수 없음
						else {
							way -= 1;
							return;
						}
					}

					else {
						move += 1;
					}
				}

				// 경사로를 사용하는 중임
				else {
					// 경사로를 설치하는 길의 높이가 일정하지 않은 경우
					if (now != map[y][n]) {
						way -= 1;
						return;
					}

					else {
						// 경사로를 설치하는 길의 높이가 일정한 경우
						cnt -= 1;
					}
				}

				if (cnt == 0) {
					cnt = L;
					move = 0;
				}
			}

			if (cnt != L) {
				way -= 1;
				return;
			}
		}

		if (type == ROW) {
			// System.out.printf("%d행 성공\n", n);
		} else if (type == COL) {
			// System.out.printf("%d열 성공\n", n);
		}
	}
}