import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 동전1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        int[] dp = new int[k+1];
        dp[0] = 1;
        for(int i = 0; i < n; i++){
            for(int j = arr[i]; j<=k; j++){
                    dp[j] = dp[j] + dp[j - arr[i]];
            }
        }
        System.out.println(dp[k]);
    }
}
