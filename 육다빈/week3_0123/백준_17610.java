package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class S17610_scales {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		Set<Long> set = new TreeSet<Long>();
		set.add((long) 0);
		int sum = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++) {
			int w = Integer.parseInt(st.nextToken());
			sum += w;

			Iterator<Long> it = set.iterator();
			Set<Long> tmpSet = new HashSet<Long>();
			
			while(it.hasNext()) {
				long now = it.next();
				tmpSet.add(now + w);
				tmpSet.add(now - w);
			}
			
			set.addAll(tmpSet);
		}

		Iterator<Long> it = set.iterator();
		while(it.hasNext()) {
			long now = it.next();
			if(now>0) break;
			else it.remove();
		}
		
		System.out.println(sum - set.size());
	}

}
