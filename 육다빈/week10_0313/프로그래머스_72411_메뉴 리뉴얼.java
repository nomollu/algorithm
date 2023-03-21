import java.util.*;

class Solution {
    int count, max;
    String order;
    boolean[] visit;
    SortedMap<String, Integer> menu_list;
    
    static String sort(String s) {
    	char[] ch = s.toCharArray();
    	Arrays.sort(ch);
    	String str = "";
    	for(char c : ch) str += c;
    	return str;
    }
    
    void comb(int n, int start){
        if(n==count){
            String s = "";
            for(int i=0; i<visit.length; i++){
                if (visit[i]) s += order.charAt(i);
            }
            if(menu_list.containsKey(s)) {
            	int cnt = menu_list.get(s)+1;
            	max = Math.max(max, cnt);
            	menu_list.replace(s, cnt);
            }
            else menu_list.put(s, 1);
            return;
        }
        for(int i=start; i<order.length(); i++){
            visit[i] = true;
            comb(n+1, i+1);
            visit[i] = false;
        }
    }
    
    public String[] solution(String[] orders, int[] course) {
        List<String> ans = new ArrayList<String>();
		
        for(int n : course){
            count = n;
            max = 0;
            menu_list = new TreeMap<String, Integer>();
            for(String od : orders){
                order = sort(od);
                int size = order.length();
                int max = 0;
                visit = new boolean[size];
                comb(0, 0);
            }
            for(String key : menu_list.keySet()) {
            	if(menu_list.get(key) == max) ans.add(key);
            }
        }
        
        int size = ans.size();
        String[] answer = new String[size];
        for(int i=0; i<size; i++) answer[i] = ans.get(i); 
        Arrays.sort(answer);
        return answer;
    }
}
