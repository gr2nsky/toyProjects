import java.sql.Connection;
import java.util.Scanner;

public class Login {
	
	FileHandling fh = new FileHandling();
	Scanner sc = new Scanner(System.in);
	
	public void flow() {
		int repeat = 0;
			print("init");
			while(repeat == 0) {
				print("menu");
				int select =  sc.nextInt();
				repeat = menu(select);
		}
	}
	
	public int menu(int i){
		switch (i) {
		case (1):
			return login();
		case (2):
			join();
			break;
		case (3):
			System.exit(0);
		default:
			print("wrong");
			print("menu");
			break;
		}
		return 0;
	}
	public int login() {
		String inputId = null;
		String sucess;
		
		while(true) {
			System.out.println("ID를 입력해 주시거나, exit를 입력해서 메뉴에서 나와주세요.");
			System.out.print(">>");
			inputId = sc.next(); 
			System.out.println("입력한 아이디 : "+inputId);
			
			if(inputId.equals("exit")) {
				return 0;
			}
			sucess = checkId(inputId);
			if(sucess.equals("fail"))
				continue;
			else {
				Main.userId = sucess;
				System.out.printf("\n\n\n\n\n\n\n\n\n\n\n\n\n");
				System.out.println("#로그인 성공. "+Main.userId+"님 환영합니다.#");
				return 1;
			}
		}
	}
	public String checkId(String inputId) {
		if(!Querys.checkId(Main.mysqlConn, inputId)) {
			if (checkPw(inputId)==1)
				return inputId;
			else
				return "fail";
		}
		System.out.println("존재하지 않는 id입니다.");
		return "fail";
		
	}
	public int checkPw(String inputId) {
		String inputPw = null;
		while(true) {
			System.out.println("비밀번호를 입력해 주세요. end를 입력하면 메뉴로 돌아갑니다.");
			System.out.print(">>");
			inputPw = sc.next();
			if(inputPw.equals("end"))
				return 0;
			if( Querys.loginAccount(Main.mysqlConn, inputId, inputPw) ) {
				return 1;
			} else {
				System.out.println("비밀번호가 일치하지 않습니다.");
			}
		}

	}
	public void join() {
		String makeId;
		String makePw;
		
		System.out.println("사용하실 id를 입력해 주세요. exit를 입력해 메뉴로 돌아갈 수 있습니다.");
		System.out.print(">>");
		while(true) {
			makeId = sc.next();
			if(makeId.equals("exit"))
				break;
			
			if(Querys.checkId(Main.mysqlConn, makeId)) {
				System.out.println(makeId+"는 사용하실수 있는 id입니다.");
				makePw = joinPw();
				Querys.insertAccount(Main.mysqlConn, makeId, makePw);
				break;
			}
			else 
				System.out.println("중복된 id입니다. 다시 입력해 주세요.");
				System.out.print(">>");
			}
	}
	public String joinPw() {
		String makePw; 
		
		System.out.println("사용하실 비밀번호를 입력해 주세요. exit를 입력하면 회원가입을 취소합니다.");
		System.out.print(">>");
		makePw = sc.next();
		if(makePw.equals("exit"))
			return "fail";
		System.out.println("비밀번호를 한번 더 입력해 주세요. exit를 입력하시면 회원가입을 취소합니다.");
		System.out.print(">>");
		while(true) {
			String repeatPw = sc.next();
			if(makePw.equals("exit"))
				return "fail";
			if(repeatPw.equals(repeatPw)) {
				System.out.println("회원가입에 성공했습니다. 가입한 계정으로 로그인 해 주세요.");
				return makePw;
			}
			else {
				System.out.println("비밀번호가 일치하지 않습니다. 다시 입력해 주세요. exit를 입력하면 회원가입을 취소합니다.");
				System.out.print(">>");
			}
		}
	}
	public void print(String p) {
		switch (p){
			case "wrong":
				System.out.println();
				System.out.println("올바르지 않은 입력입니다. 다시 입력해 주세요.");
				System.out.println();
				break;
			case "init":
				System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
				System.out.println("■ ______  _____ ______   ___    _    _               _      _  ______             _    __   __ _____  _____  ■");
				System.out.println("■ |  ___||_   _||  ___| / _ \\  | |  | |             | |    | | | ___ \\           | |   \\ \\ / /|_   _||_   _| ■");
				System.out.println("■ | |_     | |  | |_   / /_\\ \\ | |  | |  ___   _ __ | |  __| | | |_/ /  ___  ___ | |_   \\ V /   | |    | |   ■");
				System.out.println("■ |  _|    | |  |  _|  |  _  | | |/\\| | / _ \\ | '__|| | / _` | | ___ \\ / _ \\/ __|| __|  /   \\   | |    | |   ■");
				System.out.println("■ | |     _| |_ | |    | | | | \\  /\\  /| (_) || |   | || (_| | | |_/ /|  __/\\__ \\| |_  / /^\\ \\ _| |_  _| |_  ■");
				System.out.println("■ \\_|     \\___/ \\_|    \\_| |_/  \\/  \\/  \\___/ |_|   |_| \\__,_| \\____/  \\___||___/ \\__| \\/   \\/ \\___/  \\___/  ■");
				System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
				System.out.println("FIFA World Best XII에 접속하신것을 환영합니다.");
				System.out.println("2005년도부터 2018년도까지의 FIFA World Best XII에 선정된 선수를 찾고 나의 팀에 등록해 보세요!");
				break;
			case "menu":
				System.out.println();
				System.out.println("       ___________________________");
				System.out.println("       |                          |");
				System.out.println("       |   메뉴의 숫자를 입력해 주세요.   |");
				System.out.println("       |      [1] 로그인                    |");      
				System.out.println("       |      [2] 회원가입                  |");     
				System.out.println("       |      [3] 종료                       |");
				System.out.println("       |__________________________|");
				System.out.print(">>");
				break;
		}

	}
}
