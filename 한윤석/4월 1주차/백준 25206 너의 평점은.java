public class B25206_너의평점은 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int totalScore = 0;
		double totalCal = 0;
		for(int i=0; i<20; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			st.nextToken();
			double score = Double.parseDouble(st.nextToken());
			String grade = st.nextToken();
			double cal = score;
			
			if(grade.equals("A+")) {
				totalCal += 4.5 * score;
			}else if(grade.equals("A0")) {
				totalCal += 4.0 * score;
			}else if(grade.equals("B+")) {
				totalCal += 3.5 * score;
			}else if(grade.equals("B0")) {
				totalCal += 3.0 * score;
			}else if(grade.equals("C+")) {
				totalCal += 2.5 * score;
			}else if(grade.equals("C0")) {
				totalCal += 2.0 * score;
			}else if(grade.equals("D+")) {
				totalCal += 1.5 * score;
			}else if(grade.equals("D0")) {
				totalCal += 1.0 * score;
			}else if(grade.equals("F")) {
				totalCal += 0 * score;
			}else continue;
			
			totalScore += score;
		}
		System.out.printf("%6f", totalCal/ totalScore);
	}
}
