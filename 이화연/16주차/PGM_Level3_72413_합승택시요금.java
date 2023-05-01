class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int[][] fee = new int[n+1][n+1];
        for(int i=0; i<=n; i++){
            for(int j=0; j<=n; j++){
                fee[i][j] = 200 * 100000 + 1;
                if(i == j){
                    fee[i][j] = 0;
                }
            }
        }
        
        for(int i=0; i<fares.length; i++){
            int c = fares[i][0]; //c지점
            int d = fares[i][1]; //d지점
            int f = fares[i][2]; //요금
            fee[c][d] = fee[d][c] = f;
        }
        
        // 모든 정점 최소 요금 구하기
        for(int k=1; k<=n; k++){
            for(int i=1; i<=n; i++){
                for(int j=1; j<=n; j++){
                    if(fee[i][j] > fee[i][k] + fee[k][j]){
                        fee[i][j] = fee[i][k] + fee[k][j];
                    }
                }
            }
        }
        
        int ans = fee[s][a] + fee[s][b]; //각자 이동하는 경우
        for(int i=1; i<=n; i++){
            // i까지 합승한다음 i에서 각자 이동하는 경우
            if(ans > fee[s][i] + fee[i][a] + fee[i][b]){
                ans = fee[s][i] + fee[i][a] + fee[i][b];
            }
        }
        return ans;
    }
}
