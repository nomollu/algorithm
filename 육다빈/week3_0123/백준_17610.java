package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class S17610_scales {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] weight = new int[N];
		List<Integer> list = new ArrayList<Integer>();
		list.add(0);
		int S = 0;
		for(int i=0; i<N; i++) {
			weight[i] = Integer.parseInt(st.nextToken());
			S += weight[i];
			int len = list.size();
			for(int j=0; j<len; j++) {
				int tmp = list.get(j);
				list.add(tmp + weight[i]);
				list.add(tmp - weight[i]);
			}
		}
		
		boolean[] visit = new boolean[S+1];
		for(int num : list) {
			if(num>0) visit[num] = true;
		}
		
		int cnt = 0;
		for(int i=1; i<=S; i++) {
			if(!visit[i]) cnt++;
		}
		
		System.out.println(cnt);
	}

}
