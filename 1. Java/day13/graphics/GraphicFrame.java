package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.MalformedURLException;
import java.net.URL;

public class GraphicFrame extends Frame {

	private Font font1, font2, font3;

	private Image image, image2;
	private String path = "classes/resource/test.jpg";
	private URL url;

	public GraphicFrame() {
		image = Toolkit.getDefaultToolkit().getImage(path);
		
		try {
			url = new URL("https://www.fashionseoul.com/wp-content/uploads/2017/09/201709018_PARK-2.jpg");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		image2 = Toolkit.getDefaultToolkit().getImage(url);
	}

	@Override
	public void paint(Graphics g) {
//		super.paint(g);

		System.out.println("paint() Called...");
		font1 = new Font("Serif", Font.BOLD, 12);
		font2 = new Font("Batang", Font.ITALIC + Font.BOLD, 24);
		font3 = new Font("SansSerif", Font.PLAIN, 14);
		g.setFont(font1); // 붓에 첫번째 폰트를 주고
		g.setColor(Color.red); // 색은 red로

		g.drawString("Serif 12 point bold.", 20, 100);
		g.setFont(font2);
		g.setColor(Color.green);
		g.drawString("바탕 24 point italic.", 20, 65);
		g.setFont(font3);
		g.setColor(Color.blue);
		g.drawString("SansSerif 14 point plain.", 20, 80);

		// 도형별 그리기 메소드 제공
		g.draw3DRect(0, 0, 46, 36, true);
		g.drawOval(50, 0, 46, 36);
		int x1[] = new int[] { 100, 300, 273 };
		int y1[] = new int[] { 0, 0, 36 };
		g.drawPolygon(x1, y1, x1.length);
		g.setColor(Color.blue);
		g.fill3DRect(0, 40, 46, 36, true);
		g.fillOval(50, 40, 46, 36);
		int x2[] = new int[] { 100, 300, 273 };
		int y2[] = new int[] { 40, 40, 76 };
		g.fillPolygon(x2, y2, x2.length);
		g.drawLine(150, 40, 396, 76);
		g.setColor(Color.black);
		g.drawString("www. bangry.com", 10, 115);
		g.setColor(Color.cyan);
		g.drawRoundRect(0, 120, 46, 36, 10, 10);
		g.fillRoundRect(50, 120, 46, 36, 10, 10);
		g.clearRect(30, 30, 250, 30);

		g.drawImage(image, 0, 0,  this);
		g.drawImage(image2, 200, 200,  this);
		

	}

	public static void main(String[] args) {
		GraphicFrame frame = new GraphicFrame();
		frame.setSize(1000, 1000);
		frame.setVisible(true);
	}
}
