
import java.util.ArrayList;
import java.util.Scanner;

public class Year {
	int year;
	int pomation;
	ArrayList<Player> players = new ArrayList<>();
	
	void read(Scanner sc) {
		//2008 442, gk - df - mf - fw 순오르 players index 나열
		year = sc.nextInt();
		pomation = sc.nextInt();
	}
	
	void print() {
		System.out.printf("%d년도 World Besr XII [포메이션:%d]\n", year, pomation);
		for (int i = 0; i < players.size(); i++ ) {
			players.get(i).print();
		}
	}
	
	boolean match(String year) {
		if (this.year == Integer.parseInt(year)) {
			return true;
		}
		return false;
	}
	
}
