class Solution {
    
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        Map<String, Integer> map = new HashMap<>(); // <사야하는 품목, 개수>
        
        for(int i=0 ;i<want.length; i++) map.put(want[i], number[i]);
        
        Set<String> wantKeys = map.keySet(); //사야하는 품목들
        
        outer:for(int i=0; i<=discount.length - 10; i++){
            Map<String, Integer> buy = new HashMap<>(); //i번 일에 방문했을 때 구매할 수 있는 것
    
            for(int j=i; j<i+10; j++){
                String goods = discount[j];
                if(buy.containsKey(goods)) buy.put(goods, buy.get(goods)+1);
                else buy.put(goods, 1);
            }
            
            for(String s : wantKeys){
                if(!buy.containsKey(s)) continue outer; //사야하는데 구매한 목록에 없는 경우
                
                if(buy.get(s) != map.get(s)) continue outer; //사야하는 개수와 구매한 개수가 다른 경우
            }
            answer++;
        }
        
        return answer;
    }
}
