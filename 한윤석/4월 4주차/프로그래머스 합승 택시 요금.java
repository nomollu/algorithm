
public class P합승택시요금 {

	static final int INF = 99999999; 
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int fares [][] = {{4,1,10},{3,5,24},{5,6,2},{3,1,41},{5,1,24},{4,6,50},{2,4,66},{2,3,22},{1,6,25}};
		System.out.println(solution(6,4,6,2,fares));
	}

	static int solution(int n, int s, int a, int b, int [][] fares) {
		int answer = INF;
		int d[][] = new int [n+1][n+1];
		
		// 배열 초기화
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) d[i][j] = i==j ? 0 : INF;
		}
		
		// 배열에 간선 정보 입력
		for(int [] f : fares) {
			d[f[0]][f[1]] = f[2];
			d[f[1]][f[0]] = f[2];
		}
		
		
		for(int k=1; k<=n; k++) {
			for(int i=1; i<=n; i++) {
				for(int j=1; j<=n; j++) {
					// i에서 j로 가는 것과, k 거쳐서 가는 것 중 더 빠르거로 갱신
					d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
				}
			}
		}
	
		for(int i=1; i<=n; i++) answer = Math.min(answer, d[s][i] + d[i][a] + d[i][b]);
		
		return answer;
	}
}
