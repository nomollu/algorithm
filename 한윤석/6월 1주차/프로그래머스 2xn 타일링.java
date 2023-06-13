public int solution(int n) {
        int arr [] = new int [n+1];
        int MOD = 1000000007;
        arr[1] = 1;
        arr[2] = 2;
        
        for(int i=3; i<=n; i++) arr[i] = (arr[i-1] % MOD) + (arr[i-2] % MOD);
        
        return arr[n] % MOD;
}
