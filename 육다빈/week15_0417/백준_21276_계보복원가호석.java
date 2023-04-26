package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class G21276_translatorHoseok {

	static class Family implements Comparable<Family>{
		String child;
		List<String> parents; // child의 모든 조상들
		Family(String child){
			this.child = child;
			this.parents = new ArrayList<String>();
		}
		@Override
		public int compareTo(Family o) {
			return this.parents.size() - o.parents.size();
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		SortedMap<String, List<String>> childs = new TreeMap<String, List<String>>(); // 각 부모들의 자식 저장
		Map<String, Family> parents = new HashMap<String, Family>(); // 각 자식들의 조상들 저장
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			String name = st.nextToken();
			childs.put(name, new ArrayList<String>());
			parents.put(name, new Family(name));
		}
		
		// 각 자식들에게 연결된 모든 조상들을 저장한다
		int M = Integer.parseInt(br.readLine());
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			String child = st.nextToken();
			String parent = st.nextToken();
			parents.get(child).parents.add(parent);
		}
		
		// 조상의 수가 적은 사람 우선으로 정렬
		Family[] parentsArr = parents.values().toArray(new Family[N]);
		Arrays.sort(parentsArr);
		
		int depth = 0, cnt = 0;
		Map<String, Integer> level = new HashMap<String, Integer>(); // 각 사람들의 레벨 저장
		
		List<String> roots = new ArrayList<String>();
		for(Family f : parentsArr) { 
			depth = Math.max(depth, f.parents.size());

			for(String parent : f.parents) { // f.child의 모든 조상들
				if(!level.containsKey(parent)) { // 한번도 등장한 적 없는 부모
					level.put(parent, depth);
					if(depth == 1) roots.add(parent);
				}
				if(level.get(parent)==depth) { // f.child의 직계부모
					childs.get(parent).add(f.child);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		// 최상단 조상들 이름순 정렬 및 출력
		Collections.sort(roots); 
		sb.append(roots.size() + "\n");
		for(String r : roots) sb.append(r + " ");
		sb.append("\n");
		
		// 각 사람들의 자식 출력
		for(String parent : childs.keySet()) {
			sb.append(parent + " " + childs.get(parent).size());
			Collections.sort(childs.get(parent)); // 자식들도 이름순 정렬 필요
			for(String child : childs.get(parent)) {
				sb.append(" " + child);
			}				
			sb.append("\n");
		}
		
		System.out.println(sb);
	}

}
