package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class G3584_nearestCommonAncestor {
	static int[] parents;
	
	static void findParents(int child, Stack<Integer> stack) {
		stack.add(child);
		
		if(parents[child] != 0) {
			findParents(parents[child], stack);
		}
		
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t=0; t<T; t++) {
			int N = Integer.parseInt(br.readLine());
			parents = new int[N+1];
			
			for(int i=0; i<N-1; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int parent = Integer.parseInt(st.nextToken());
				int child = Integer.parseInt(st.nextToken());
				parents[child] = parent;
			}
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			Stack<Integer> stack_a = new Stack<Integer>();
			Stack<Integer> stack_b = new Stack<Integer>();
			
			findParents(a, stack_a);
			findParents(b, stack_b);
			
			int result = -1;
			while(!stack_a.isEmpty() && !stack_b.isEmpty()){
				int parent_a = stack_a.pop(); 
				int parent_b = stack_b.pop(); 
				
				if(parent_a == parent_b) {
					result = parent_a;
				}else {
					break;
				}
			}
			
			System.out.println(result);
		}
	}

}
