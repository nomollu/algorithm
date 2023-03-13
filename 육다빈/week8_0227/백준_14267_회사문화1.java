package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class G14267_companyCulture {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		List<Integer>[] childs = new List[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			childs[i] = new ArrayList<Integer>();
			int parent = Integer.parseInt(st.nextToken());
			if(i>1) childs[parent].add(i);
		}
		
		int[] praise = new int[N+1];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			praise[idx] += Integer.parseInt(st.nextToken());
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++) {
			for(int child : childs[i]) praise[child] += praise[i];
			sb.append(praise[i]+(i<N ? " " : "\n"));
		}
		
		System.out.println(sb);
	}

}
