import java.util.Scanner;

public class PatternMatching {
	public void patternMatch(String str, String pattern){
		int lenStr = str.length();
		int lenPatt = pattern.length();
		int patIndex = 0;
		boolean flag = false;
		for(int i =0;i<lenStr;i++){
			char cStr = str.charAt(i);
			if(patIndex < lenPatt){
				if(cStr == pattern.charAt(patIndex)){
					patIndex++;
					flag = true;
					if(patIndex == lenPatt)
						break;
				}
				else{
					flag = false;
					patIndex = 0;
				}
			}
			else{
				patIndex = 0;
			}
		}
		if(flag == true)
			System.out.println("Found");
		else
			System.out.println("Not Found");
	}
	public static void main(String...args){
		Scanner s = new Scanner(System.in);
		System.out.println("Enter input string");
		String inp = s.nextLine();
		System.out.println("Enter pattern to be matched");
		String pat = s.nextLine();
		PatternMatching p1 = new PatternMatching();
		p1.patternMatch(inp,pat);
	}
}
