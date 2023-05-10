public class P캐시 {

	static public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        Map<String, Integer> dic = new HashMap<>();
        
        for(int i=0; i<cities.length; i++) {
        	String city = cities[i].toUpperCase();
        	int size = dic.size();
        	
        	// 캐싱되어 있으면
        	if(dic.containsKey(city)) {
        		dic.put(city, i);
        		answer++;
        	}else {
        		//이미 캐시 사이즈만큼 다 찼으면 가장 오래된 요소 제거 후 새거 넣음
        		if(size == cacheSize) {
        			String oldestCity = "";
        			int oldestIdx = Integer.MAX_VALUE;
        			
        			for(String tempCity : dic.keySet()) {
        				int idx = dic.get(tempCity);
        				if(oldestIdx > idx) {
        					oldestCity = tempCity;
        					oldestIdx = idx;
        				}
        			}
        			
        			dic.remove(oldestCity);
        		}
        		if(cacheSize != 0) dic.put(city, i);
        		answer += 5;
        	}
        			
        }
        
        return answer;
    }
}
