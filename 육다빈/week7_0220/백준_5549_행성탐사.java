package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G5549_goPlanet {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(br.readLine());
		
		char[][] map = new char[N][M];
		for(int i=0; i<N; i++) map[i] = br.readLine().toCharArray();
		
		int[][][] sum = new int[3][N][M];
		for(int i=0; i<N; i++) {
			int[] tmp = new int[3];
			for(int j=0; j<M; j++) {
				int idx = (map[i][j]=='J' ? 0 : (map[i][j]=='O'?1:2));
				tmp[idx]++;
				for(int t=0; t<3; t++) {
					if(i==0) sum[t][i][j] = tmp[t];
					else sum[t][i][j] = sum[t][i-1][j]+tmp[t];
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for(int k=0; k<K; k++) {
			st = new StringTokenizer(br.readLine());
			int si = Integer.parseInt(st.nextToken())-2;
			int sj = Integer.parseInt(st.nextToken())-2;
			int ei = Integer.parseInt(st.nextToken())-1;
			int ej= Integer.parseInt(st.nextToken())-1;
			int tmp[] = new int[3];
			for(int t=0; t<3; t++) {
				if(si<0) {
					if(sj<0) tmp[t] = sum[t][ei][ej];
					else tmp[t] = sum[t][ei][ej] - sum[t][ei][sj];
				}else {
					if(sj<0) tmp[t] = sum[t][ei][ej] - sum[t][si][ej];
					else tmp[t] = sum[t][ei][ej] - sum[t][si][ej] - sum[t][ei][sj] + sum[t][si][sj];
				}
				sb.append(tmp[t]);
				sb.append(t==2 ? '\n' : ' ');
			}
		}
		
		System.out.println(sb);
	}

}
