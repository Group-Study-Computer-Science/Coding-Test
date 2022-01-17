package code20220116;

import java.util.Scanner;

public class 동전1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		
		int n = input.nextInt();
		int k = input.nextInt();
		int [] dp = new int [10001];
		dp[0] = 1;
		for(int i = 1; i <= n; i++) {
			int num = input.nextInt();
			for(int j = 0; j <= k - num; j++) {
				dp[j + num] += dp[j];
			}
		}
		System.out.println(dp[k]);
		
	}

}
