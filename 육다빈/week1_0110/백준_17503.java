package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
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

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		beers = new Beer[K];
		visit = new boolean[K];
		
		PriorityQueue<Beer> beers = new PriorityQueue<Beer>();
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			beers.add(new Beer(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		int sum_l=0, max_a=-1, cnt=0;
		PriorityQueue<Integer> eaten = new PriorityQueue<Integer>();
		
		while(!beers.isEmpty()) {
			Beer b = beers.poll();
			sum_l += b.like;
			eaten.add(b.like);
		
			if(++cnt==N) {
				if(sum_l>=M) {
					max_a = b.alcohol;
					break;
				}else {
					cnt--;
					sum_l -= eaten.poll();
				}
			}
		}
		System.out.println(max_a);
	}

}
