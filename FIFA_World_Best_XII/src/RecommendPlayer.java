import java.sql.Connection;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class RecommendPlayer {
	
	MyXII mx = Main.mx;
	
	public void flow() {
		while(true) {
			Scanner scan = new Scanner(System.in);
			print("menu");
			String order = scan.next();
			switch (order) {
			case "1":
				randomRec();
				break;
			case "2":
				macthRec();
				break;
			case "3":
				popularityRec();
				System.out.println("초기화 되었습니다.");
				break;
			case "4":
				return;
			default:
				System.out.println("잘못된 입력입니다. 다시 입력해 주세요.");
			}
		}
	}
	
	void print(String order) {
		switch (order){
		case "menu":
			System.out.println();
			System.out.println("       ___________________________");
			System.out.println("       |                          |");
			System.out.println("       |    선수 추천 메뉴입니다.       |");
			System.out.println("       |      [1] 랜덤선수 추천            |");
			System.out.println("       |      [2] 맞춤선수 추천            |");
			System.out.println("       |      [3] 인기선수 추천            |");
			System.out.println("       |      [4] 뒤로가기                  |");
			System.out.println("       |__________________________|");
			System.out.print(">>");
			break;
		}
	}
	
	void randomRec() {
		Random ran = new Random();
		int num = Main.players.size();
		System.out.println("[랜덤으로 5명의 선수를 추천해 드립니다.]");
		for (int i = 0 ; i < 5; i++) {
			int r = ran.nextInt(num-1);
			Player player = Main.players.get(r);
			player.print();
		}
		System.out.println("내 팀에 선수를 추가하시려면, 선수의 번호를 입력하세요. 뒤로가시려면 end룰 입력해주세요.");
		System.out.println(">>");
		Scanner s = new Scanner(System.in);
		String p = s.next();
		if(p.equals("end")) {
			return;
		}
		mx.pick(Integer.parseInt(p));
	}
	void macthRec() {
		//연도, 국적, 키, 체중
		ArrayList<Player> result = new ArrayList<>();
		ArrayList<Player> tmp = new ArrayList<>();
		
		Scanner s = new Scanner(System.in);
		
		String buffer;
		String buffers[];
		int buffer1, buffer2;
		
		System.out.println("[맞춤형 선수추천 기능입니다.]");
		System.out.println("[수상년도, 국적, 키, 체중을 차례로 입력하시면 해당 선수들을 출력합니다.]");
		System.out.println("[stop을 입력하시면 메뉴로 돌아가실 수 있습니]");
		System.out.println("원하시는 연도가 있습니까? (ex. 2005-2006, 없으면 pass룰 입력해주세요");
		System.out.println(">>");
		buffer = s.next();
		if( buffer.equals("stop"))
			return;
		if( !buffer.equals("pass")) {
			buffers = buffer.split("-");
			buffer1 = Integer.parseInt(buffers[0]);
			buffer2 = Integer.parseInt(buffers[1]);
			
			for (int i = buffer1; i <= buffer2; i++) {
				for(Year y : Main.years) 
					if ( y.year == i) 
						for(Player p : y.players)
							result.add(p);
			}
		}
		
		System.out.println("원하시는 국적이 있숩니까? 없다면 pass를 입력해주세요.");
		buffer = s.next();
		if( buffer.equals("stop"))
			return;
		if( !buffer.equals("pass")) {
			if(result.isEmpty()) {
				for(Player p : Main.players)
					if(p.nation.equals(buffer))
						result.add(p);
			}
			else {
				for(Player p : result)
					if(p.nation.equals(buffer))
						tmp.add(p);
				result.clear();
				result = tmp;
			}
		}
		
		System.out.println("원하시는 체중이 있습니까? (ex. 60-80) 없다면 pass을 입력해주세요.");
		buffer = s.next();
		if( buffer.equals("stop"))
			return;
		if( !buffer.equals("pass")) {
			buffers = buffer.split("-");
			buffer1 = Integer.parseInt(buffers[0]);
			buffer2 = Integer.parseInt(buffers[1]);
			if(result.isEmpty()) {
				for(Player p : Main.players)
					if(buffer1 <= p.weight && buffer2 >= p.weight)
						result.add(p);
			}
			else {
				for(Player p : result)
					if(buffer1 > p.weight && buffer2 < p.weight)
						tmp.add(p);
				result.clear();
				result = tmp;
			}
		}
		
		System.out.println("원하시는 키가 있습니까? (ex. 170-180) 없다면 pass을 입력해주세요.");
		buffer = s.next();
		if( buffer.equals("stop"))
			return;
		if( !buffer.equals("pass")) {
			buffers = buffer.split("-");
			buffer1 = Integer.parseInt(buffers[0]);
			buffer2 = Integer.parseInt(buffers[1]);
			if(result.isEmpty()) {
				for(Player p : Main.players)
					if(buffer1 <= p.height && buffer2 >= p.height)
						result.add(p);
			}
			else {
				for(Player p : result)
					if(buffer1 > p.height && buffer2 < p.height)
						tmp.add(p);
				result.clear();
				result = tmp;
			}
		}
		
		if(result.isEmpty()) {
			System.out.println("#검색조건에 해당되는 선수가 없습니다.");
			return;
		}
		System.out.println("해당 조건에 해당되는 선수들의 목록입니다.");
		for(Player p : result)
			p.print();
		System.out.println("내 팀에 선수를 추가하시려면, 선수의 번호를 입력하세요. 뒤로가시려면 end룰 입력해주세요.");
		System.out.println(">>");
		String p = s.next();
		if(p.equals("end")) {
			return;
		}
		mx.pick(Integer.parseInt(p));
	}
	
	public void popularityRec() {
		System.out.println("[해당 프로그램의 사용자들에게 가장 많이 선택받은 tp10 명을 출력합니다.]");
		
		int[] arr = new int[59];
		
		String data = Querys.pop(Main.mysqlConn);

		String datas[] = data.split(",");
		for (int i = 0; i < datas.length; i++) {
			int n = Integer.parseInt(datas[i]);
			if(n == -1)
				continue;
			arr[n]++;
			}
		int pop = 0;
		int big = 0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < arr.length; j++) {
				if(arr[j] > pop) {
					pop = arr[j];
					big = j;
				}
			}
			System.out.printf("인기 %d위 (%d명이 선택) \n", i+1, arr[big]);
			Main.players.get(big).print();
			arr[big] = 0;
			pop = 0;
			big = 0;
		}
		Scanner s = new Scanner(System.in);
		System.out.println("내 팀에 선수를 추가하시려면, 선수의 번호를 입력하세요. 뒤로가시려면 end룰 입력해주세요.");
		System.out.println(">>");
		String p = s.next();
		if(p.equals("end")) {
			return;
		}
		mx.pick(Integer.parseInt(p));
	}
	

}
