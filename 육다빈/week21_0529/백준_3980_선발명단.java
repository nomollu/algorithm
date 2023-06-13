package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class G3980_startingList {
	static int N, M;
	static List<Score>[] playerList;
	static boolean[] visit;
	
	static class Score{
		int position;
		int score;
		Score(int position, int score){
			this.position = position;
			this.score = score;
		}
	}
	
	static int go(int now, int sum) {
		if(now==11) return sum;
		
		int max = 0;
		for(Score s : playerList[now]) {
			if(visit[s.position]) continue;
		
			visit[s.position] = true;
			max = Math.max(max, go(now+1, sum+s.score));
			visit[s.position] = false;
		}
		
		return max;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t=0; t<T; t++) {
			playerList = new List[11];
			
			for(int i=0; i<11; i++) {
				playerList[i] = new ArrayList<Score>();
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<11; j++) {
					int tmp = Integer.parseInt(st.nextToken());
					if(tmp > 0) playerList[i].add(new Score(j, tmp));
				}
			}
			
			visit = new boolean[11];
			System.out.println(go(0, 0));
		}
		
	}

}
