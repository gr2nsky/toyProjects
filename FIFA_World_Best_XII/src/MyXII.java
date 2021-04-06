import java.util.InputMismatchException;
import java.util.Scanner;

public class MyXII {
	
	FileHandling fh = new FileHandling();
	
	String userid;
	int pomation;
	//저장기록 없으면 0, 있으면 1
	int nonn = 0;
	int[] mPlayers = new int[11];
	Scanner sc = new Scanner(System.in);
	
	//생성자
	public MyXII(String userid) {
		this.userid = userid;
	}
	
	void flow() {
		while(true) {
			print("menu");
			String order = sc.next();
			switch (order) {
			case "1":
				allPrint();
				break;
			case "2":
				savePlayers();
				break;
			case "3":
				initPlayers();
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
			System.out.println("       |    나만의 팀 관리 메뉴입니다.    |");
			System.out.println("       |      [1] 출력하기                  |");
			System.out.println("       |      [2] 저장하기                  |");
			System.out.println("       |      [3] 초기화                     |");
			System.out.println("       |      [4] 뒤로가기                  |");
			System.out.println("       |__________________________|");
			System.out.print(">>");
			break;
		}
	}
	
	void reading() {
		
		String data = Querys.readMyXII(Main.mysqlConn, userid);
		if( data.equals("-1") || data == null) {
			for(int i = 0; i < 11; i++) {
				mPlayers[i] = -1;
				if(i == 11)
					break;
			}
			return;
		}
		nonn = 1;
		String players[] = data.split(",");
		int i = 0;
		for(String p : players) {
			mPlayers[i] = Integer.parseInt(p);
			i++;
			if(i == 12)
				break;
		}
		System.out.println("***나만의 팀 loading 완료***");
	}
	
	void positionPrint(int i) {
		Player player = Main.players.get(i);
		player.print();
	}
	
	void allPrint() {
		int num = 0;
		System.out.println("--------------------");
		System.out.println("         GK          ");
		System.out.println("--------------------");
		for(int i : mPlayers) {
			if(i == -1) 
				System.out.println("없음");
			else
				Main.players.get(i).print();
			if(num == 0 || num == 4 || num == 7 ) {
				System.out.println("--------------------");
				if(num == 0)
					System.out.println("         DF          ");
				if(num == 4)
					System.out.println("         MF          ");
				if(num == 7)
					System.out.println("         FW          ");
				System.out.println("--------------------");
			}
			num++;
		}
	}
	
	void move() {
		System.out.println("");
	}
	
	//포메이션에 따라 수정 요함 현재는 433 포메이션.
	void pick(int p) {
		Player player = Main.players.get(p);
		String position = player.position;
		String name = player.name;
		int id = player.id;
		
		int start = -1;
		int end = -1;
		
		switch(position) {
		case "GK":
			start = 0;
			end = 1;
			break;
		case "DF":
			start = 1;
			end = 5;
			break;
		case "MF":
			start = 5;
			end = 8;
			break;
		case "FW":
			start = 8;
			end = 10;
			break;
		}
		for(int i = start; i < end; i++) {
			if(mPlayers[i] == -1) {
				System.out.println(position+"에 "+name+" 선수를 추가했습니다.");
				mPlayers[i] = id;
				return;
			}
		}
		int select = -1;
		while(select != 100) {
			System.out.println("해당 포지션에 빈자리가 없습니다. 대체할 선수의 번호를 입력해주세요. 100을 입력시 취소됩니다.");
			for(int i = start; i < end; i++) {
				positionPrint(i);
			}
			Scanner input = new Scanner(System.in);
			try {
				select = input.nextInt();
			} catch(InputMismatchException e) {
				System.out.println("올바른 입력이 아닙니다.");
				continue;
			}
			for(int i = start; i < end; i++) {
				if(i == select) {
					System.out.println(position+"에 +"+Main.players.get(i).name+" 대신 "+name+" 선수를 추가했습니다.");
					mPlayers[i] = id;
					select = 100;
				}
			}
		}
	}
	//데이터 저장
	public void savePlayers() {
		String data = "";
		for(int i = 0; i < 11; i ++) {
			data += Integer.toString(mPlayers[i]);
			if(i!=10){
				data+=",";
			}
		}
		Querys.saveMyXII(Main.mysqlConn, userid, data);
	}
	//초기화
	public void initPlayers() {
		for(int i = 0; i < 11; i++) {
			mPlayers[i] = -1;
		}
	}
	
}
