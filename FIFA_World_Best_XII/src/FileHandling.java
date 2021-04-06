
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class FileHandling {
	/**
	 * Scanner에 읽어올 파일을 지정한다.
	 * @param fileName 파일명
	 * @return Scanner
	 */
	Scanner openFile(String fileName) {
		File file = new File(fileName);
		Scanner s = null;
		try {
			s = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return s;
	}
	/**
	 * 지정된 txt파일에 text를 입력한다.
	 * @param fileName 쓸 파일을 지정한다.
	 * @param txt 기록할 text
	 */
	public void writeFile_join(String fileName, String txt) {
		try{
            File file = new File(fileName) ;
            FileWriter fw = new FileWriter(file, true);
            fw.write(" "+txt);
            fw.flush();
            fw.close();
        }catch(Exception e){
            e.printStackTrace();
        }
	}
	/**
	 * 지정된 txt파일에 한줄 띄고 text를 입력한다.
	 * @param fileName 쓸 파일을 지정한다.
	 * @param txt 기록할 text
	 */
	public void writeFile_jump(String fileName, String txt) {
		try{
            File file = new File(fileName) ;
            FileWriter fw = new FileWriter(file, true);
            fw.write("\n");
            fw.write(txt);
            fw.flush();
            fw.close();
        }catch(Exception e){
            e.printStackTrace();
        }
	}
	//입력한 user의 나의 팀을 초기화시킨다.
	public void initUserIddATA(String userId) {
		String initData = "433 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1";
		int line = findRemovePoint(userId);
		removeLine(line, initData);
	}
	/**
	 * 해당 줄을 삭제하고 새로운 내용으로 대체 후 덮어씌운다.
	 * @param fileName 
	 * @param removePosition
	 * @param updateData
	 */
	public void removeLine(int removeLine, String updateData){
		//1. temp.txt 파일을 만들고, 기존 UserData 파일을 연다.
		File temp = new File("temp.txt");
		File f = new File("UserData.txt");
		String pasteText;
		String buffer="";
		Scanner sc = null;
		FileWriter fw = null;
		//2. temp.txt에 삭제할 줄 이전까지 복사한다.
		try {
			sc = new Scanner(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < removeLine; i++) {
			pasteText = sc.nextLine();
			buffer += pasteText+"\n"; 
		}
		//3. temp.txt에 삭제한 줄 위치에 대체할 데이터인 updateData를 입력한다.
		buffer += updateData+"\n";
		//4. 다시 temp.txt을 UserData끝까지 복사한다.
		sc.nextLine();
		while(sc.hasNext()) {
			pasteText = sc.nextLine();
			buffer += pasteText+"\n"; 
		}
		//6 temp.txt에 복사한 내용을 쓴다. 이후 모든 scanner와 filewriter를 끈다.
		try {
			fw = new FileWriter(temp, true);
			fw.write(buffer);
			fw.close();
			sc.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		//5. 복사가 끝난 후, UserData.txt를 삭제하고 temp.txt의 이름을 UserData.txt로 변경한다.
	    boolean delete = f.delete();
	    boolean b = temp.renameTo(f);
	}
	//해당 유저 아이디의 다음 줄을 찾아 반환한다. 0이면 못찾은거임.
	public int findRemovePoint(String userId) {
		Scanner f = openFile("UserData.txt");
		int line = 0;
		f.nextLine();
		while (f.hasNext()) {
			line++;
			String checkId = f.next();
			System.out.println("대조할 건" + checkId);
			if(checkId.equals(userId)) {
				System.out.println(Integer.toString(line)+"이 회원정보가 있는 줄입니다.");
				line++;
				f.close();
				return line; 
			}
			f.nextLine();
		}
		f.close();
		return 0;
	}
}