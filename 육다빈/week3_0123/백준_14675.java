package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class S14675_cuttingNodeLine {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		List<Integer>[] nodes = new List[N+1];
		
		for(int i=1; i<=N; i++) nodes[i] = new ArrayList<Integer>();
		
		for(int i=1; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			nodes[a].add(b);
			nodes[b].add(a);
		}

		StringBuilder sb = new StringBuilder();
		int Q = Integer.parseInt(br.readLine());
		
		for(int i=0; i<Q; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			if(Integer.parseInt(st.nextToken())==1) { // 단절점인지
				int target = Integer.parseInt(st.nextToken());
				if(nodes[target].size()>1) sb.append("yes\n");
				else sb.append("no\n");
				
			}else { // 단절선인지
				sb.append("yes\n");
			}
		}
		
		System.out.println(sb);
	}

}
