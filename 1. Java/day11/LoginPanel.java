import java.awt.Button;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.peer.KeyboardFocusManagerPeer;

public class LoginPanel extends Panel implements ActionListener{
	
	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;
	
	TalkFrame talkFrame;
	
	Label emailL, passwdL;
	TextField emailTF, passwdTF;
	Button loginB, registB;
	
	public LoginPanel(TalkFrame talkFrame) {
		this.talkFrame = talkFrame;
		gridBagLayout = new GridBagLayout();
		gridBagConstraints = new GridBagConstraints();
		
		emailL = new Label("EMAIL");
		passwdL = new Label("PASSWD");
		emailTF = new TextField();
		passwdTF = new TextField();
		passwdTF.setEchoChar('*');
		loginB = new Button("LOGIN");
		registB = new Button("REGIST");
		setContents();
		
		loginB.addActionListener(this);
	}
	private void add(Component component, int gridx, int gridy, int gridwidth, int gridheight, double weightx, double weighty) {
		gridBagConstraints.gridx = gridx;
		gridBagConstraints.gridy = gridy;
		gridBagConstraints.gridwidth = gridwidth;
		gridBagConstraints.gridheight = gridheight;
		gridBagConstraints.weightx = weightx;
		gridBagConstraints.weighty = weighty;
		gridBagConstraints.fill = gridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(4, 1, 4, 1);
		
		gridBagLayout.setConstraints(component, gridBagConstraints);		
		add(component);
	}
	
	public void setContents() {
		setLayout(gridBagLayout);
		add(new Label(" "),   0, 0, 1, 1, 0, 0);
		add(emailL,   1, 0, 1, 1, 0, 0);
		add(emailTF,  2, 0, 1, 1, 1, 0);
		add(new Label(" "), 3, 0, 1, 1, 0, 0);
		
		add(new Label(" "),   0, 1, 1, 1, 0, 0);
		add(passwdL,  1, 1, 1, 1, 0, 0);
		add(passwdTF, 2, 1, 1, 1, 1, 0);
		add(new Label(" "),   3, 1, 1, 1, 0, 0);
		
		Panel buttonPanel = new Panel();
		buttonPanel.add(loginB);
		buttonPanel.add(registB);
		add(buttonPanel, 0, 2, 3, 1, 0, 0);
		
	}
	
//	public static void main(String[] args) {
//		Frame frame = new Frame("메인화면");
//		
//		LoginPanel loginPanel = new LoginPanel();
//		
//		frame.add(loginPanel);
//		frame.setSize(300, 500);
//		frame.pack();
//		frame.setVisible(true);
//	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		talkFrame.cardLayout.show(talkFrame.cardPanel, "Main"); //좋은 코드X
		
		//클래스 캡슐화 (자기 기능은 자신이 제공..) 
		//!= 은닉화
		talkFrame.changeCard("Main");
	}
}
