import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_Gold3_21276_계보복원가호석 {
    static int N, M;
    static Map<String, Integer> map;
    static ArrayList<Integer>[] list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); //사람 수
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            map.put(st.nextToken(), i); //사람마다 인덱스 지정
        }

        list = new ArrayList[N];
        for(int i=0; i<N; i++){
            list[i] = new ArrayList<Integer>();
        }

        M = Integer.parseInt(br.readLine()); //기억하는 정보 수
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            // a의 조상 중에 b가 있다
            String a = st.nextToken();
            String b = st.nextToken();
            list[map.get(b)].add(map.get(a)); //b에만 자식으로 a연결
        }

        for(int i=0; i<N; i++){
            if(list[i].size() == 0) continue;
            // 자식이 있는 부모일 경우에
            for(int j=0; j<list[i].size(); j++){
                int num = list[i].get(j);
                if(list[num].size() == 0) continue;
                //해당 자식이 또 자식이 있는지 확인..
                for(int k=0; k<list[num].size(); k++){
                    int child = list[num].get(k);
                    // 자식의 자식이 자식의 부모에 포함되어 있을때?
                    // 그니까 상도의 자식들이 호석, 대일인데 대일이의 자식 중 호석이가 있으면 상도의 자식에서 호석이를 빼야됨..
                    if(list[i].contains(child)){
                        list[i].remove(child);
                    }
                }
            }
        }

        // 최종적으로 가문의 개수가 몇개인지..
        dfs(0);
    }

    static void dfs(int num){

    }
}
