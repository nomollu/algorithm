package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S14620_flowerRoad {
	static int N;
	static int[] di = {-1, 0, 1, 0};
	static int[] dj = {0, 1, 0, -1};
	static int[][] map; // 칸당 부과되는 비용
	static int[][] cost; // 해당 칸을 중심으로 꽃 생성 시 발생 비용
	static int[][] visit; // 이전에 선택한 꽃의 중심칸
	
	static int calcCost(int i, int j) { // (i, j)칸이 중심인 꽃의 비용
		int sum = map[i][j];
		for(int d=0; d<4; d++) sum += map[i+di[d]][j+dj[d]];
		return sum;
	}
	
	static boolean isOkay(int ni, int nj, int cnt) { // 이전에 선택한 꽃들과 겹쳐지는지 확인
		for(int i=0; i<cnt; i++) {
				if(Math.abs(ni-visit[i][0]) + Math.abs(nj-visit[i][1]) <= 2) return false;
		}
		return true;
	}
	
	static int comb(int n, int cnt, int sum) { // 전체 칸 중 3개 선택하여 합한 값 중 최소값 반환
		if(cnt==3) return sum;

		int min = Integer.MAX_VALUE;
		for(int k=n; k<N*N; k++) {
			int ni = k / N;
			int nj = k % N;
			if(ni==0 || ni==N-1 || nj==0 || nj==N-1 || !isOkay(ni, nj, cnt)) continue;
			
			visit[cnt][0] = ni;
			visit[cnt][1] = nj;
			min = Math.min(min, comb(k+1, cnt+1, sum+cost[ni][nj]));
		}
		return min;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		cost = new int[N][N];
		visit = new int[3][2];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=1; i<N-1; i++) {
			for(int j=1; j<N-1; j++) cost[i][j] = calcCost(i, j);
		}
		
		
		System.out.println(comb(0, 0, 0));
	}

}
