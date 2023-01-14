package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class S17503_beerFestival {
	static int N, M, K;
	static Beer[] beers;
	static boolean[] visit;
	
	static class Beer implements Comparable<Beer>{
		int like, alcohol;
		Beer(int like, int alcohol){
			this.like = like;
			this.alcohol = alcohol;
		}
		@Override
		public int compareTo(Beer o) {
			if(this.alcohol == o.alcohol) return o.like-this.like; //내림차순
			else return this.alcohol-o.alcohol; //오름차순
		}
		
	}
	//start:조합 시작인덱스, cnt:마신 횟수, sum_l:누적 선호도, max_a:알코올 최댓값
	static int dfs(int start, int cnt, int sum_l, int max_a){ 
		if(cnt==N) {
			if(sum_l >= M) return max_a; // 기준선호도 넘을 경우만 값 반환
			else return -1;
		}
		for(int i=start; i<K; i++) {
			int result = dfs(i+1, cnt+1, sum_l+beers[i].like, Math.max(max_a, beers[i].alcohol));
			if(result!=-1) return result;
		}
		return -1;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		beers = new Beer[K];
		visit = new boolean[K];
		
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			beers[i] = new Beer(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(beers);
		System.out.println(dfs(0, 0, 0, 0));
	}

}
