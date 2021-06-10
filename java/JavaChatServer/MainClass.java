package chatServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;


public class MainClass {

	private ServerSocket server;
	
	//닉네임과 userClass user룰 묶을 해쉬맵
	Hashtable<String, UserClass> map1;
	Hashtable<String, UserClass> map2;
	Hashtable<String, UserClass> map3;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new MainClass();
	}	
    //메인메소드가 static으로 되어있기 때문에 다른것들을 다 static 으로 하기 귀찮기 때문에
	// 따로 생성자를 만들어서 진행 - > 메인에서는 호출정도의 기능만 구현하는게 좋다.
	public MainClass() {
		try {

			map1 = new Hashtable<String, UserClass>();
			map2 = new Hashtable<String, UserClass>();
			map3 = new Hashtable<String, UserClass>();
			
			// 서버 가동
			server=new ServerSocket(10022);
			// 사용자 접속 대기 스레드 가동
			ConnectionThread thread= new ConnectionThread();
			thread.start();
		}catch(Exception e) {e.printStackTrace();}
	}
	
	class ConnectionThread extends Thread{
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				while(true) {
					System.out.println("사용자 접속 대기");
					Socket socket=server.accept();
					System.out.println("사용자가 접속하였습니다.");
					// 사용자 닉네임을 처리하는 스레드 가동
					NickNameThread thread=new NickNameThread(socket);
					thread.start();
				}
			}catch(Exception e) {e.printStackTrace();}
		}
	}
	
	class NickNameThread extends Thread{
		private Socket socket;
		int numberofUser = 0; // 현재 속한 서버의 사용자수를 구함
		String numberOfUser = null; //사용자수의 string 형태
		
		public NickNameThread(Socket socket) {
			this.socket=socket;
		}
		public void run() {
			try {
				// 스트림 추출
				InputStream is = socket.getInputStream();
				OutputStream os= socket.getOutputStream();
				DataInputStream dis=new DataInputStream(is);
				DataOutputStream dos=new DataOutputStream(os);

				//닉네임 및 소속 서버 수신
				String CutText=dis.readUTF();
				String array[] = CutText.split("_");
				String roomNo = array[0];
				String nickName = array[1];

				
				// 사용자 정보를 관리하는 객체를 생성한다.
				UserClass user= new UserClass(nickName, socket, roomNo);
				user.start();
				switch (roomNo) {
				case "room1" : map1.put(nickName, user); break;
				case "room2" : map2.put(nickName, user); break;
				case "room3" : map3.put(nickName, user); break;
				}
				// 환영 메세지를 전달한다.
				//전송하는 msg에 마찬가지로 _를 기준으로 두개의 정보를 담을 것이다.
				//_이전엔 사용자를 포함한 이용자의 수를 입력해 클라이언트의 채팅 인터페이스가 현재 서버의 채팅인원을 나타낼 정보를 준다.
				//arr[0] = "justMsg"면 단순 msg, arr[0] = "numberOfuser" 면 사용자의 수 구분해 받게끔
				numberOfUsers(roomNo);
		
				//클라이언트들에게 msg 전송
				dos.writeUTF("numberOfUser_"+numberOfUser+"_"+nickName+" 님 환영합니다.");
				sendToClient("numberOfUser_"+numberOfUser+"_"+"서버 : "+nickName+"님이 접속하였습니다.", roomNo);
				
				System.out.println(nickName+"님이"+roomNo+"서버에 접속하셧습니다.");
				
			}catch(Exception e) {e.printStackTrace();}
		}
		
		public void numberOfUsers(String roomNo) {
			switch (roomNo){
			case "room1" : numberofUser = map1.size(); break;
			case "room2" : numberofUser = map2.size(); break;
			case "room3" : numberofUser = map3.size(); break;
			}
			numberOfUser = Integer.toString(numberofUser);
		}
		
	}
	
	// 사용자 정보를 관리하는 클래스
		class UserClass extends Thread {
			String nickName;
			Socket socket;
			DataInputStream dis;
			DataOutputStream dos;
			String roomNo;
			int numberofUser = 0; // 현재 속한 서버의 사용자수를 구함
			String numberOfUser = null; //사용자수의 string 형태
			
			public UserClass(String nickName,Socket socket, String roomno) {
				try {
				this.nickName =nickName;
				this.socket = socket;
				this.roomNo = roomno;
				InputStream is=socket.getInputStream();
				OutputStream os=socket.getOutputStream();
				dis = new DataInputStream(is);
				dos=new DataOutputStream(os);
				
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			// 사용자로부터 메세지를 수신받는 스레드
			public void run() {
				try {
					while(true) {
						//클라이언트에게 command_msg 꼴의 string data을 수신한다.
						//그리고 split 을 이용해 분리, 각각 string 변수형으로 저장한다.
						String command_msg=dis.readUTF();
						String arr[] = command_msg.split("_");
						String command = arr[0];
						String msg = arr[1];
						
						//사용자가 입력한 command에 따라 분기
						switch (command){
						//접두사가 justMsg인 경우 단순 메세지 전송
							case "justMsg" : 
								sendToClient("justMsg_"+nickName+" : "+ msg, roomNo); 
								break;
							case "userList" :
								sendToOne("userList_"+userListEx(roomNo), roomNo, nickName); 
								break;
						}
					}
					//클라이언트가 접속을 종료한다면, 여기서 에러가 발생한다.
				}catch(Exception e) {
					switch (roomNo){
					case "room1" : 
						map1.remove(nickName);
						numberofUser = map1.size(); break;
					case "room2" : 
						map2.remove(nickName);
						numberofUser = map2.size(); break;
					case "room3" : 
						map3.remove(nickName);
						numberofUser = map3.size(); break;						}
					}
					numberOfUser = Integer.toString(numberofUser);
					sendToClient("numberOfUser_"+numberOfUser+"_"+nickName + "님이 나가셨습니다.", roomNo);
					
				}
			}
		
		public synchronized void sendToClient(String msg, String roomNo) {
			Iterator iterator = null;
			Hashtable<String, UserClass> table = null;
			
			switch (roomNo){
			case "room1" : iterator = map1.keySet().iterator(); table = map1; break;
			case "room2" : iterator = map2.keySet().iterator(); table = map2; break;
			case "room3" : iterator = map3.keySet().iterator(); table = map3; break;
			}
		
			while(iterator.hasNext()) {
				String nickName = (String)iterator.next();
				try {
					table.get(nickName).dos.writeUTF(msg);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		//해당 유저에게만 전송
		public synchronized void sendToOne(String msg, String roomNo, String nickName) {
			Iterator iterator = null;
			Hashtable<String, UserClass> table = null;
			
			switch (roomNo){
			case "room1" : iterator = map1.keySet().iterator(); table = map1; break;
			case "room2" : iterator = map2.keySet().iterator(); table = map2; break;
			case "room3" : iterator = map3.keySet().iterator(); table = map3; break;
			}
			
			try {
				table.get(nickName).dos.writeUTF(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		public String userListEx(String roomNo) {
			String userList = null;
			switch(roomNo) {
			case "room1" : 
				  Set key1 = map1.keySet();
				  for (Iterator iterator = key1.iterator(); iterator.hasNext();) {
					  String user = (String) iterator.next();
					  userList += user+",";
				  }
				break;
			case "room2" : 
				  Set key2 = map2.keySet();
				  for (Iterator iterator = key2.iterator(); iterator.hasNext();) {
					  String user = (String) iterator.next();
					  userList += user+",";
				  }
				break;
			case "room3" : 
				  Set key3 = map3.keySet();
				  for (Iterator iterator = key3.iterator(); iterator.hasNext();) {
					  String user = (String) iterator.next();
					  userList += user+",";
				  }
				break;
			}
			return userList;
		}
		
}
