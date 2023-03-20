package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class G9489_cousin {
	static List<Integer>[] childs;
	
	static int bfs(int parent) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(1);
		
		while(!queue.isEmpty()) {
			int size = queue.size();
			for(int s=0; s<size; s++) {
				int now = queue.poll();
				System.out.println("부모 " + now + "꺼냄");
				if(childs[now].contains(parent)) {
					return findCousin(now, parent);
				}else {
					for(int child : childs[now]) {
						queue.add(child);
					}
				}
			}
		}
		return -1;
	}
	
	static int findCousin(int root, int parent) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(root);
		
		System.out.println("조부모가 " + root + "이고, 부모가 " + parent);
		int result = 0;
		for(int p : childs[root]) {
			if(parent == p) continue;
			System.out.println("이모삼촌 " + p);
			result += childs[p].size();
		}
		
		return result;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			if(N==0) break;
			
			st = new StringTokenizer(br.readLine());
			
			childs = new List[N+1];
			for(int i=1; i<=N; i++) {
				childs[i] = new ArrayList<Integer>();
			}
			
			int root = Integer.parseInt(st.nextToken());
			if(root == K) {
				sb.append("0\n");
				continue;
			}
			
			int cnt=1;
			boolean endPoint=false;
			Queue<Integer> queue = new LinkedList<Integer>();
			queue.add(cnt);

			// 1. 각 노드의 자식노드 저장하면서 K의 부모 찾기
			int pre=-1, targetParent=-1;
			loop : while(!queue.isEmpty()) {
				int size = queue.size();
				
				for(int s=0; s<size; s++) {
					int parent = queue.poll();

					while(++cnt <= N) {
						int now = Integer.parseInt(st.nextToken());

						if(pre!=-1 && pre+1!=now) parent = queue.peek();
						
						// 대상을 찾은 경우, 해당 대상의 부모 저장
						if(now==K) {
							if(parent==1) { //부모의 형제가 없는 경우
								endPoint = true;
								sb.append("0\n");
								break loop;
							}
							targetParent = parent;
						}

						// 자식노드에 추가 및 다음 부모명단에 올리기
						childs[parent].add(cnt);
						queue.add(cnt);
						pre = now;

						if(pre!=-1 && pre+1!=now) break;
						
					}
					
					
					
				}
			}
			
			for(int i=1; i<=N; i++) System.out.println(childs[i].toString());
			
			// 2. 사촌 찾기
			if(!endPoint) sb.append(bfs(targetParent) + "\n");
			System.out.println("===========");
		}
		
		System.out.println(sb);
	}
}
