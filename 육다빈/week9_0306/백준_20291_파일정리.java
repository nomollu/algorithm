package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class S20291_fileOrganizing {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		SortedMap<String, Integer> map = new TreeMap<String, Integer>();
		for(int i=0; i<N; i++) {
			String str = br.readLine().split("\\.")[1];
			if(map.containsKey(str)) map.replace(str, map.get(str)+1);
			else map.put(str, 1);
		}
		
		StringBuilder sb = new StringBuilder();
		Set<String> list = map.keySet();
		for(String str : list) {
			sb.append(str + " " + map.get(str) + "\n");
		}
		
		System.out.println(sb);
	}

}
