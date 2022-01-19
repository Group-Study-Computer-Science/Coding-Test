import java.util.*;

class Solution {
    int[][] map;
    public int[] BFS(int start, int n){
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (x,y) -> x[1] - y[1]
        );
        pq.add(new int[]{start,0}); 
        
        int[] result = new int[n]; 
        Arrays.fill(result, Integer.MAX_VALUE); 
        result[start]=0; 
                       
        while(!pq.isEmpty()) { 
            int[] node = pq.poll();
            if(node[1] > result[node[0]]) continue;
            
            for(int i=0;i<n;i++) { 
                int cost = map[node[0]][i]+result[node[0]];
                if(map[node[0]][i]>0 && result[i]>cost) { 
                    result[i] = cost;
                    pq.offer(new int[]{i,cost});
                }
            }
        }
        return result;
    }
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;
        map = new int[n][n];
        for(int i = 0; i < fares.length; i++){
            map[fares[i][0]-1][fares[i][1]-1] = fares[i][2];
            map[fares[i][1]-1][fares[i][0]-1] = fares[i][2];
        }
        int[] start = BFS(s-1, n);
        int[] A_end = BFS(a-1, n);
        int[] B_end = BFS(b-1, n);
        
        for(int i = 0; i < n; i++){
            int cost = start[i] + A_end[i] + B_end[i];
            answer = Math.min(cost, answer);
        }
        return answer;
    }
}
