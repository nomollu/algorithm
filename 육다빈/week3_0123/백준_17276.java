package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S17276_rotateArray {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[] di = {-1, -1, -1, 0, 1, 1, 1, 0};
		int[] dj = {-1, 0, 1, 1, 1, 0, -1, -1};
		
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int t=0; t<T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int rot = Integer.parseInt(st.nextToken())/45;
			
			int[][] map = new int[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) map[i][j] = Integer.parseInt(st.nextToken());
			}

			int[][] result = new int[N][N];
			int point = N/2;
			for(int n=1; n<=point; n++) {
				for(int d=0; d<8; d++) {
					int from_i = point + di[d]*n;
					int from_j = point + dj[d]*n;
					int to_i = point + di[(d+rot+8)%8]*n;
					int to_j = point + dj[(d+rot+8)%8]*n;
					result[to_i][to_j] = map[from_i][from_j];
				}
			}

			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					sb.append((result[i][j]==0 ? map[i][j] : result[i][j]) + " ");
				}
				sb.append("\n");
			}
			
		}
		System.out.println(sb);
	}

}
