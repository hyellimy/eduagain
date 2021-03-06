package kr.or.kosta.chat.client;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AddRoomFrame extends Frame{
	
	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;
	
	JLabel roomNameL, roomCapacityL, jLabel;
	JTextField roomNameTF, roomCapacityTF;
	JButton checkNameB, createRoomB, cancelB;
	
	Font font;
	
	WaitingPanel waitingPanel;
		
	boolean isCheck = false;
	
	public AddRoomFrame() {}
	
	public AddRoomFrame(String title, WaitingPanel waitingPanel) {
		super(title);
		this.waitingPanel = waitingPanel;
		
		font = new Font(Font.DIALOG, Font.PLAIN, 14);
		
		gridBagLayout = new GridBagLayout();
		gridBagConstraints = new GridBagConstraints();
		
		roomNameL = new JLabel("이름");
		roomNameL.setFont(font);
		roomCapacityL = new JLabel("참여자 수");
		roomCapacityL.setFont(font);
		
		roomNameTF = new JTextField(10);
		roomNameTF.setFont(font);
		roomNameTF.setSize(10, 25);
		roomCapacityTF = new JTextField(10);
		roomCapacityTF.setFont(font);
		
		checkNameB = new JButton("중복체크");
		checkNameB.setFont(font);
		createRoomB = new JButton("방 추가");
		createRoomB.setFont(font);
		cancelB = new JButton("취소");
		cancelB.setFont(font);
		
		setContents();
	}
	
	public void setContents() {
		
		setLayout(gridBagLayout);
		Panel nameP = new Panel(new BorderLayout(40, 10));
		nameP.add(roomNameL, BorderLayout.WEST);
		nameP.add(roomNameTF, BorderLayout.CENTER);
		nameP.add(checkNameB, BorderLayout.EAST);
		
		Panel capacityP = new Panel(new BorderLayout(10, 10));
		capacityP.add(roomCapacityL, BorderLayout.WEST);
		capacityP.add(roomCapacityTF, BorderLayout.CENTER);
		
		Panel settingP = new Panel(new FlowLayout(FlowLayout.CENTER,20,10));
		settingP.add(new Label());
		settingP.add(createRoomB);
		settingP.add(cancelB);
		
		add(nameP, 		0,0,1,1,0,0,0);
		add(capacityP, 	0,2,1,1,0,0,0);
		add(settingP, 	0,4,1,1,0,0,0);
		
		
	}

	private void add(Component component, int gridx, int gridy, int gridwidth, int gridheight, double weigthx,
			double weighty, int fill) {
		gridBagConstraints.gridx = gridx;
		gridBagConstraints.gridy = gridy;
		gridBagConstraints.gridwidth = gridwidth;
		gridBagConstraints.gridheight = gridheight;
		gridBagConstraints.weightx = weigthx;
		gridBagConstraints.weighty = weighty;
		gridBagConstraints.anchor = gridBagConstraints.WEST;

		gridBagConstraints.insets = new Insets(5, 5, 5, 5); // margin

		switch (fill) {
		case 1:
			gridBagConstraints.fill = gridBagConstraints.BOTH;
			break;
		case 2:
			gridBagConstraints.fill = gridBagConstraints.HORIZONTAL;
			break;
		case 3:
			gridBagConstraints.fill = gridBagConstraints.VERTICAL;
			break;
		default:
			gridBagConstraints.fill = gridBagConstraints.NONE;
			break;
		}

		gridBagLayout.setConstraints(component, gridBagConstraints);

		add(component);
	}
	
	/**
	 * frame 중간에 위치시키기
	 */
	public void setCenter() {
		Toolkit.getDefaultToolkit().beep();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		int x = (dim.width - getSize().width)/2;
		int y = (dim.height - getSize().height)/2;
		setLocation(x, y);
	}
	
	public void finish() {
		setVisible(false);
		dispose();
	}
	
	
	/**
	 * frame 정보 초기화
	 */
	public void init() {
		isCheck = false;
		roomNameTF.setEditable(true);
		roomNameTF.setText("");
		roomCapacityTF.setText("");
	}
	
	/**
	 * 방이름 중복확인 설정
	 */
	public void cs_checkName() {
		String name = roomNameTF.getText();
		if(name.trim().equals("")) {
			JOptionPane.showMessageDialog(null, "이름을 입력해주세요","입력 필수" , JOptionPane.ERROR_MESSAGE);
			return;
		}
		waitingPanel.cs_checkRoomName(name);
	}
	
	/**
	 * 방 이름 중복확인 결과
	 * @param message 중복확인 결과
	 */
	public void sc_CheckName(String message) {
		if(!(message.equalsIgnoreCase("SUCCESS"))){
			JOptionPane.showMessageDialog(null, "방이름이 이미 존재합니다.", "중복체크", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		int isUsernickName = JOptionPane.showConfirmDialog(null, "사용가능한 방이름입니다.\n사용하시겠습니까?", "방이름 사용", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(isUsernickName == 0) {
			isCheck = true;
			roomNameTF.setEditable(false);
		}
	}
	
	/**
	 *  방 생성
	 */
	public void createRoom() {
		if(roomCapacityTF.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "인원을 입력해주세요", "최대 인원", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// 방 생성 누르긴 한 경우 방 정보를 서버에 보낸다
		if(isCheck) {
			int checkRoom = JOptionPane.showConfirmDialog(null, "방을 만드시겠습니까?", "방이름 사용", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(checkRoom == 0){
				String name = roomNameTF.getText();
				String owner = waitingPanel.getNickName();
				System.out.println(owner);
				String capacity = roomCapacityTF.getText();
				waitingPanel.createRoom(name, owner, capacity);
				return;
			}
		}else {
			JOptionPane.showMessageDialog(null, "중복 확인을 눌러주세요", "중복 필수", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	//실제 방 개설 완성
	public void sc_checkCreate(String msg) {
		if(msg.equalsIgnoreCase("SUCCESS")) {
			waitingPanel.setRoomName(roomNameTF.getText());
			waitingPanel.changeCardPanel(2);
			finish();
		}
	}
	
	public void eventRegist() {
		
		/**방 최대 인원 숫자만 입력받기 */
		roomCapacityTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isDigit(c)) && (c != '\b')) {
					e.consume();
				}
			}
		});
		
		/**방이름 중복확인 */
		roomNameTF.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cs_checkName();
			}
		});
		
		/**방이름 중복확인 */
		checkNameB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cs_checkName();
			}
		});
		
		/**방 개설 */
		createRoomB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				createRoom();
			}
		});
		
		/**방 개설 */
		cancelB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				finish();
			}
		});
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				finish();
			}
		});
	}
	
	
}
