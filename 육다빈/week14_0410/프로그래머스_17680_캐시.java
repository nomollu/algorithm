import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        if(cacheSize == 0) return cities.length*5; // 캐시 크기가 0인 경우 예외처리
        
        int answer = 0;
        List<String> list = new ArrayList<String>();
        
        for(String c : cities){
            String city = c.toLowerCase();
            if(list.contains(city)) {
                answer++;
                list.remove((Object) city);
                list.add(city);
            }else{
                answer += 5;
                if(list.size() < cacheSize) list.add(city);
                else{
                    list.remove(0);
                    list.add(city);
                }
            }
        }
        
        return answer;
    }
}
