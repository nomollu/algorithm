package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S13305_gasStation {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] street = new int[N];
		for(int i=1; i<N; i++) street[i] = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int min = Integer.parseInt(st.nextToken()); // 거쳐온 주유소 중 최소 기름 비용
		long cnt=0, result=0; // cnt = 채워야 할 기름 양, result = 최소기름비용 합계
		
		for(int i=1; i<N; i++) {
			int now = Integer.parseInt(st.nextToken());
			cnt += street[i];

			if(now < min) {
				result += cnt * min;
				min = now;
				cnt = 0;
			}
		}
		
		if(cnt!=0) result += cnt * min;
		
		System.out.println(result);
	}

}
