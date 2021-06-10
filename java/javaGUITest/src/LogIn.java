import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogIn extends JFrame{
    private MainClass main;
    private JPanel loginPanel;
    private JTextField loginId;
    private JTextField loginPw;
    private JButton signUpBtn;
    private JButton logInBtn;

    public LogIn(MainClass main) {
        this.main = main;
        signUpBtn.addActionListener(new loginBtnEnventHandler());
        logInBtn.addActionListener(new loginBtnEnventHandler());

        setTitle("Login");
        setContentPane(loginPanel);
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    class loginBtnEnventHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == signUpBtn){
                main.moveToSignUp();
            } else {
                String text = "[로그인 버튼 클릭]\n입력한 id :" + loginId.getText()
                        + "\n입력한 pw :" + loginPw.getText();
                JOptionPane.showMessageDialog(null, text);
            }
        }
    }
}


