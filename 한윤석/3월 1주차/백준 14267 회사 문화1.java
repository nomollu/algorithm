public class B14267_회사문화1 {
    
    static List<Integer> [] master;
    static int [] praise;
    static int [] totalPraise;
    static boolean [] visit;
    static int n;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        master = new LinkedList[n+1];
        praise = new int [n+1];
        totalPraise = new int [n+1];
        visit = new boolean[n+1];
        st = new StringTokenizer(br.readLine());
        
        for(int i=1; i<=n; i++) {
            master[i] = new LinkedList<>();
            int idx = Integer.parseInt(st.nextToken());
            if(i == 1) continue;
            master[idx].add(i);
        }
        
        while(m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            praise[i] += w;
        }
        
        for(int i=1; i<=n; i++) {
            if(!visit[i]) applaud(i, praise[i]);
        }
        
        for(int i=1; i<=n; i++) System.out.print(totalPraise[i] + " ");
    }
    
    static void applaud(int idx, int w) {
        totalPraise[idx] += w;
        visit[idx] = true;
        
        for(int i:master[idx]) {
            if(visit[i]) continue;
            totalPraise[i] += totalPraise[idx];
            applaud(i, praise[i]);
        }
    }
}
