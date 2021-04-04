import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUp extends JFrame{
    private JPanel SignUpPanel;
    private JTextField signUpId;
    private JTextField signUpPw;
    private JButton signUpOkBtn;
    private JButton signUpCancleBtn;

    SignUp(){
        signUpOkBtn.addActionListener(new signUpBtnEnventHandler());
        signUpCancleBtn.addActionListener(new signUpBtnEnventHandler());

        setTitle("SignUp");
        setContentPane(SignUpPanel);
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    class signUpBtnEnventHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == signUpOkBtn){
                String text = "[회원가입 버튼 클릭]\n입력한 id :" + signUpId.getText()
                        + "\n입력한 pw :" + signUpPw.getText();
                JOptionPane.showMessageDialog(null, text);
            } else {
                dispose();
            }
        }
    }
}
