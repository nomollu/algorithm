package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class G16234_populationMovement {
	static int N, L, R, sum, cnt;
	static int[][] map, visit;
	static int[] di = {-1, 0, 1, 0};
	static int[] dj = {0, 1, 0, -1};
	
	static void check(int i, int j, int v) {
		for(int d=0; d<4; d++) {
			int ni = i + di[d];
			int nj = j + dj[d];
			if(ni<0 || ni>=N || nj<0 || nj>=N || visit[ni][nj]!=0) continue;
			int diff = Math.abs(map[i][j] - map[ni][nj]);
			if(diff>=L && diff<=R) {
				sum += map[ni][nj];
				cnt++;
				visit[ni][nj] = v;
				check(ni, nj, v);
			}
		}
	}
	
	static void change(int i, int j, int v, int p) {
		map[i][j] = p;
		visit[i][j] = -1;
		for(int d=0; d<4; d++) {
			int ni = i + di[d];
			int nj = j + dj[d];
			if(ni<0 || ni>=N || nj<0 || nj>=N || visit[ni][nj]!=v) continue;
			change(ni, nj, v, p);
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int day = 0;
		while(true) {
			boolean isChanged = false;
			visit = new int[N][N];
			int v = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(visit[i][j]==0) { //연합 발견
						sum = map[i][j];
						cnt = 1;
						visit[i][j] = ++v;
						check(i, j, v);
						if(cnt>1) {
							isChanged = true;
							change(i, j, v, sum/cnt);
						}
					}
				}
			}
			if(!isChanged) break;
			day++;
		}
		
		System.out.println(day);
		
	}

} 
