import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Main a = new Main();
		a.myMain();
	}
	
	public static Connection mysqlConn;
	
	Login login = new Login();
	FileHandling fh = new FileHandling();
	RecommendPlayer rp = new RecommendPlayer();
	public static MyXII mx;
	
	public static String userId;
	public static ArrayList<Player> players = new ArrayList<>();
	public static ArrayList<Year> years = new ArrayList<>();
	
	Scanner filein = null;
	Scanner sc = new Scanner(System.in);
	
	void myMain() {
		
		DBMS dbms = new DBMS();
		mysqlConn = dbms.DBA();
		
		login.flow();
		
		filein = fh.openFile("WorldBestXII.txt");
		read();
		filein.close();
		
		mx = new MyXII(userId);
		filein = fh.openFile("UserData.txt");
		mx.reading();
		filein.close();
		while(true) {
			print("menu");
			String select =  sc.next();
			menu(select);
		}
	}
	
	public void menu(String i){

		switch (i) {
		case "1":
			while(true) {
				print("find");
				sc = new Scanner(System.in);
				String select = sc.next();
				int roop = findMenu(select);
				if(roop ==0)
					break;
			}
			break;
		case "2":
			rp.flow();
			break;
		case "3":
			mx.flow();
			break;
		case "4":
			while(true) {
				System.out.println("경고 : 저장하지 않은 나만의 팀 설정은 모두 지워집니다. 그래도 종료하시겠습니까?");
				System.out.println("[1] : yes         [2] no");
				System.out.println(">>");
				String select2 = sc.next();
				if(select2.equals("1")) {
					System.out.println("FIFA WORLD BEST XII을 종료합니다. 이용해주셔서 감사합니다.");
					System.exit(0);
				}
				else if (select2.equals("2"))
					return;
				else
					System.out.println("잘못된 입력입니다. 다시 입력해 주세요.");
				break;
			}
		default:
			System.out.println("잘못된 입력입니다. 다시 입력해 주세요.");
		}
	}
	
	int findMenu(String order) {
		switch(order) {
		case "1":
			findYear();
			break;
		case "2":
			findName();
			break;
		case "3":
			findPosition();
			break;
		case "4":
			findNation();
			break;
		case "5":
			return 0;
		default:
			System.out.println("잘못된 입력입니다. 다시 입력해 주세요.");
		}
		return 1;
	}
	
	void findYear() {
		int loop = 0;
		while(loop == 0) {
			System.out.println("2005~2018년도 중 입력해 주세요. end를 입력시 메뉴로 돌아옵니다.");
			String y = sc.next();
			if(y.equals("end"))
				return;
			for (int i = 0; i  < years.size(); i++) {
				if(years.get(i).match(y)) {
					years.get(i).print();
					System.out.println("내 팀에 선수를 추가하시려면, 선수의 번호를 입력하세요. 뒤로가시려면 100을 입력해주세요.");
					int p = sc.nextInt();
					if(p==100)
						break;
					mx.pick(p);
					return;
				}
			}
			System.out.println("*** 올바른 값을 입력해주세요.***");
		}
	}
	
	void findName() {
		int result = 0;
		System.out.println("선수의 이름을 입력해 주세요.");
		String kwd = sc.next();
		for(Player player : players) {
			if(player.matchName(kwd)) {
				player.print();
				result++;
			}
		}
		if(result == 0) {
			System.out.println("검색결과가 없습니다.");
			return;
		}
		System.out.println("내 팀에 선수를 추가하시려면, 선수의 번호를 입력하세요. 뒤로가시려면 100을 입력해주세요.");
		int p = sc.nextInt();
		if(p==100)
			return;
		mx.pick(p);
	}
	
	void findPosition() {
		System.out.println("원하는 포지션을 입력해 주세요.(GK, DF, MF, FW)");
		String kwd = sc.next();
		for(Player player : players) {
			if(player.match(kwd))
				player.print();
		}
		System.out.println("내 팀에 선수를 추가하시려면, 선수의 번호를 입력하세요. 뒤로가시려면 100을 입력해주세요.");
		int p = sc.nextInt();
		if(p==100)
			return;
		mx.pick(p);
	}
	
	void findNation(){
		System.out.println("원하는 국적을 입력해 주세요.");
		String kwd = sc.next();
		for(Player player : players) {
			if(player.match(kwd)) 
				player.print();
		}
		System.out.println("내 팀에 선수를 추가하시려면, 선수의 번호를 입력하세요. 뒤로가시려면 100을 입력해주세요.");
		int p = sc.nextInt();
		if(p==100)
			return;
		mx.pick(p);
	}
	
	void print(String order) {
		switch(order){
		case "menu":
			System.out.println();
			System.out.println("       ___________________________");
			System.out.println("       |                          |");
			System.out.println("       |   메뉴의 숫자를 입력해 주세요.   |");
			System.out.println("       |      [1] 선수검색                  |");  
			System.out.println("       |      [2] 선수추천                  |"); 
			System.out.println("       |      [3] 나만의 팀 관리           |");     
			System.out.println("       |      [4] 종료                       |");
			System.out.println("       |__________________________|");
			System.out.print(">>");
			break;
		case "find":
			System.out.println();
			System.out.println("       ___________________________");
			System.out.println("       |                          |");
			System.out.println("       |    선수를 어떻게 찾으시겠습니까? |");
			System.out.println("       |      [1] 연도로 검색              |");      
			System.out.println("       |      [2] 이름으로 검색            |");     
			System.out.println("       |      [3] 포지션으로 검색         |");
			System.out.println("       |      [4] 국적으로 검색            |");
			System.out.println("       |      [5] 뒤로가기                  |");
			System.out.println("       |__________________________|");
			System.out.print(">>");
			break;
		}
	}
	
	void read() {
		int playerId = 0;
		while(filein.hasNext()) {
			//#1 year 저장 (1개)
			Year year = new Year();
			year.read(filein);
			//#2 Player 저장 (11개)
			for(int i = 0; i < 11; i++) {
				Player player = new Player();
				player.read(filein);
				
				int k = -1;
				for (int j = 0; j < players.size(); j++) {
					if(players.get(j).name.equals(player.name)) {
						k = j;
						break;
					}
				}
				//처음 등록되는 선수라면, id를 부여하고 players와 years에 저장한다.
				if (k == -1) {
					player.id=playerId;
					player.years.add(year.year);
					player.age = player.years.get(0) - player.age;
					players.add(player);
					//Year에도 해당 선수를 저장
					year.players.add(player);
					playerId++;
				}
				//만약, 이미 players에 등록된 선수라면, player.year에 해당연도를 추가,
				//year엔 기존 players의  선수를 추가, players에는 등록하지 않음.
				else {
					players.get(k).years.add(year.year);
					year.players.add(players.get(k));
				}
			}
			years.add(year);
		}
	}
	
	void allPrint() {
		for (Year y : years) {
			y.print();
		}
	}

}
