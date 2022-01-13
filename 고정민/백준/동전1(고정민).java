package boj.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 동전_2293 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] coin = new int[n]; // 동전의 가치를 담을 배열
		int[] dp = new int[k+1]; //dp[i] = i원을 만드는 데 필요한 경우의 수
		
		for(int i=0; i<n; i++) {
			coin[i] = Integer.parseInt(br.readLine());
		}
		
		dp[0] = 1;
		
		for(int i=0; i<n; i++) {
			for(int j=1; j<=k; j++) {
				if(j>=coin[i]) dp[j] += dp[j-coin[i]];
			}
		}
		System.out.println(dp[k]);
	}
}
