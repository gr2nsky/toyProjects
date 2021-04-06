
import java.util.ArrayList;
import java.util.Scanner;

public class Player {
	
	int id;
	String position;
	String name;
	String nation;
	int age;
	int height;
	int weight;
	int pop = 0;
	ArrayList<Integer> years = new ArrayList<>();

	
	void read(Scanner sc) {
		//GK [디다] 브라질 32 195 85 
		position = sc.next();
		name = sc.next();
		if(name.charAt(0)=='[') {
			name = name.substring(1, name.length());
			while(true) {
				if(name.charAt(name.length()-1) == ']') {
					name = name.substring(0, name.length()-1);
					break;
				}
				String tmp = null;
				tmp = sc.next();
				name += " "+tmp;
			}
		}
		nation = sc.next();
		age = sc.nextInt();
		height = sc.nextInt();
		weight = sc.nextInt();
	}
	
	void print() {
		System.out.printf("[%s] 이름 : %s, 포지션 : %s \n",id, name, position);
		System.out.printf("     국적 : %s, 나이 : %d년생, 신장 : %d, 체중 : %s, ", nation, age, height, weight);
		System.out.printf("수상년도 : ");
		for (int i : years)
			System.out.printf("%5s", i);
		System.out.printf("\n");
	}
	
	boolean matchName(String kwd) {
		if(name.contains(kwd))
			return true;
		return false;
	}
	
	boolean match(String kwd) {
		if(position.equals(kwd) || nation.equals(kwd)) {
			return true;
		}
		return false;
	}
}