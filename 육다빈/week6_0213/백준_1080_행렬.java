package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1080_matrix {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		char[][] origin = new char[N][M];
		for(int i=0; i<N; i++) origin[i] = br.readLine().toCharArray();

		char[][] tobe = new char[N][M];
		for(int i=0; i<N; i++) tobe[i] = br.readLine().toCharArray();
		
		int result = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(origin[i][j]==tobe[i][j]) continue;
				else if(i+2>=N || j+2>=M) {
					System.out.println(-1);
					return;
				}else {
					result++;
					for(int di=0; di<=2; di++) {
						for(int dj=0; dj<=2; dj++) {
							origin[i+di][j+dj] = (origin[i+di][j+dj]=='1' ? '0' : '1'); 
						}
					}
				}
			}
		}
		System.out.println(result);
	}

}
