import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_Gold4_9489_사촌 {
	static int n, k, arr[];
	static ArrayList<Integer> prev, now;
	static boolean visited[];
	static ArrayList<Node>[] depth;
	static ArrayList<Integer>[] set;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken()); // 노드 개수
			k = Integer.parseInt(st.nextToken()); // 사촌의 수 구해야할 노드

			if (n == 0 && k == 0)
				break;

			arr = new int[n];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			int setCnt = 1;
			for (int i = 2; i < n; i++) {
				if (arr[i - 1] + 1 == arr[i]) {

				} else {
					setCnt++;
				}
			}
			System.out.println(setCnt + 1);

			set = new ArrayList[setCnt];
			for (int i = 0; i < set.length; i++) {
				set[i] = new ArrayList<>();
			}
			int idx = 1;
			set[0].add(arr[0]);
			for (int i = 1; i < arr.length - 1; i++) {
				set[idx].add(arr[i]);
				if (arr[i] != arr[i + 1] - 1) {
					idx++;
				}
			}

			depth = new ArrayList[n + 1];
			for (int i = 1; i < depth.length; i++) {
				depth[i] = new ArrayList<>();
			}

			depth[1].add(new Node(arr[0], arr[0]));

//			prev = new ArrayList<>();
//			now = new ArrayList<>();
//
//			st = new StringTokenizer(br.readLine());
//			prev.add(Integer.parseInt(st.nextToken())); // 루트 노드
//			depth[1].add(new Node(prev.get(0), prev.get(0))); // 루트 노드의 값과 부모는 자기자신
//			visited = new boolean[prev.size()];
//			int nowIdx = 0;
//			int depthIdx = 2; // 루트노드 depth는 1
//			while (true) {
//				int node = Integer.parseInt(st.nextToken());
//				if (now.size() == 0) {
//					now.add(node);
//				} else {
//					if (now.get(nowIdx) + 1 == node) { // 연속된 수
//						now.add(node);
//					} else { // 연속된 집합 끝났을 경우 부모랑 depth체크하기
//						
//						int size = prev.size();
//						for (int i = 0; i < size; i++) {
//							if (!visited[i]) {
//								for (int j = 0; j < now.size(); j++) {
//									depth[depthIdx].add(new Node(now.get(j), prev.get(i)));
//								}
//								visited[i] = true;
//							}
//						}
//					}
//				}
//			}
		}

	}

	static class Node {
		int value, parent;

		public Node(int value, int parent) {
			super();
			this.value = value;
			this.parent = parent;
		}
	}

}
