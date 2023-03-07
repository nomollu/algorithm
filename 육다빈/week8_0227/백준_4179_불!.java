package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class G4179_fire {
	static int R, C;
	static char[][] map;
	static int[] di = {-1, 0, 1, 0};
	static int[] dj = {0, 1, 0, -1};
	static Queue<Point> jihun = new LinkedList<Point>();
	static Queue<Point> fire = new LinkedList<Point>();
	
	static class Point{
		int i, j;
		Point(int i, int j){
			this.i = i;
			this.j = j;
		}
	}
	
	static String exit() {
		int time = 0;
		while(!jihun.isEmpty()) {
			time++;
			int size = jihun.size();
			// 1. 지훈 대피
			for(int s=0; s<size; s++) {
				Point p = jihun.poll();
				if(map[p.i][p.j]=='F') continue; // 이미 불에 휩싸여버린 지훈
				for(int d=0; d<4; d++) {
					int ni = p.i + di[d];
					int nj = p.j + dj[d];
					if(ni<0 || ni>=R || nj<0 || nj>=C) return time+"";
					else if(map[ni][nj]=='.') {
						map[ni][nj] = 'J';
						jihun.add(new Point(ni, nj));
					}
				}
			}
			// 2. 불 번지기
			size = fire.size();
			for(int s=0; s<size; s++) {
				Point p = fire.poll();
				for(int d=0; d<4; d++) {
					int ni = p.i + di[d];
					int nj = p.j + dj[d];
					if(ni<0 || ni>=R || nj<0 || nj>=C 
							|| map[ni][nj]=='#' || map[ni][nj]=='F') continue; 
					map[ni][nj] = 'F';
					fire.add(new Point(ni, nj));
					
				}
			}
		}
		return "IMPOSSIBLE";
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		
		for(int i=0; i<R; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0; j<C; j++) {
				if(map[i][j]=='J') jihun.add(new Point(i, j));
				else if(map[i][j]=='F') fire.add(new Point(i, j));
			}
		}
		
		System.out.println(exit());
		
	}

}
