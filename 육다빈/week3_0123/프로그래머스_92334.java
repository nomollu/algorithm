import java.util.*;

class Solution {
    static class Report{
        int cnt = 0;
        List<String> reportList = new ArrayList<String>();
    }
    public int[] solution(String[] id_list, String[] report, int k) {
        
        Map<String, Report> rep = new HashMap<String, Report>();
        
        
        for(String id : id_list) rep.put(id, new Report());

        for(String s : report){
            String[] id = s.split(" ");
            List<String> rpt = rep.get(id[0]).reportList;
            if(!rpt.contains(id[1])){
                rpt.add(id[1]);
                rep.get(id[1]).cnt++;
            }
        }

        int size = id_list.length;
        List<String> outList = new ArrayList<String>();
        String[] idList = rep.keySet().toArray(new String[size]);
        for(String id : idList){
            if(rep.get(id).cnt>=k) outList.add(id);
        }

        int[] answer = new int[size];
        for(int i=0; i<size; i++){
            List<String> list = rep.get(id_list[i]).reportList;
            for(String id : list){
                if(outList.contains(id)) answer[i]++;
            }
        }
        return answer;
    }
}
