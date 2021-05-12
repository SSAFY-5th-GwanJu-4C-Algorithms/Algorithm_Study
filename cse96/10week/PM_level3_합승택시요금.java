package study.May;

public class PM_level3_합승택시요금 {
	
    public int solution(int n, int s, int a, int b, int[][] fares) {     
        int answer = Integer.MAX_VALUE;
        int[][] map = new int[n+1][n+1];
        
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                map[i][j] = Integer.MAX_VALUE;
                if(i == j) map[i][j] = 0;
            }
        }//초기화
        
        for(int i = 0; i < fares.length; i++){
            int from = fares[i][0];
            int to = fares[i][1];
            int fare = fares[i][2];
            
            map[from][to] = fare;
            map[to][from] = fare;
        }//입력 끝
        
        for(int k = 1; k <= n; k++){
            for(int i = 1; i <= n; i++){
                if(i == k) continue;
                for(int j = 1; j <= n; j++){
                    if(j == i || k == j) continue;
                    if(map[i][j] > map[i][k] + map[k][j])
                        map[i][j] = map[i][k] + map[k][j];
                }
            }
        }
        
        for(int i = 1; i <= n; i++){
            answer = Math.min(answer,map[s][i] + map[i][a] + map[i][b]);
        }
        return answer;
    }
}
