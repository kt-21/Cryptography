/*
 * Katherine Tsai
 * This program allows the user to encrypt and decrypt text by interacting with the GUI.
 */

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class KTsai_Cryptography extends JFrame implements ActionListener {

	private KTsai_Cryptable crypt;
	private JTextField plainText;
	private JTextField cipherText;	
	
	public KTsai_Cryptography(KTsai_Cryptable type) {
		
		//setup frame
		crypt = type;
		setSize(340,140);
		setTitle("Cryptography");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PicPanel mainPanel = new PicPanel("pic.jpg");
		
		//setup textfields and buttons
		JLabel plain = new JLabel("Plaintext:    ");
		plainText = new JTextField(20);
		JLabel cipher = new JLabel("Ciphertext: ");
		cipherText = new JTextField(20);
		JButton encrypt = new JButton("Encrypt");
		JButton decrypt = new JButton("Decrypt");
		JButton reset = new JButton("Reset");
		encrypt.addActionListener(this);
		decrypt.addActionListener(this);
		reset.addActionListener(this);
		mainPanel.setLayout(new FlowLayout());
		
		//add widgets
		mainPanel.add(plain);
		mainPanel.add(plainText);
		mainPanel.add(cipher);
		mainPanel.add(cipherText);
		mainPanel.add(encrypt);
		mainPanel.add(decrypt);
		mainPanel.add(reset);
		add(mainPanel);
		
		setVisible(true);
	}
	
	public class PicPanel extends JPanel{

		private BufferedImage image;
		
		public PicPanel(String fname){

			//reads the image
			try {
				image = ImageIO.read(new File(fname));
				
			} catch (IOException ioe) {
				System.out.println("Could not read in the pic");
				System.exit(0);
			}

		}
		
		//this will draw the image
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			g.drawImage(image,0,0,this);
		}
	}
	
	//call methods based on button pressed
	public void actionPerformed(ActionEvent ae){
		
		if(ae.getActionCommand().equals("Encrypt") && !plainText.getText().equals(""))
			cipherText.setText(crypt.encrypt(plainText.getText()));
		else if(ae.getActionCommand().equals("Decrypt") && !cipherText.getText().equals(""))
			plainText.setText(crypt.decrypt(cipherText.getText()));
		else {
			
			plainText.setText("");
			cipherText.setText("");
		}
	}
	
	public static void main(String[] args){
		
		//prompt the user for which type of encryption algorithm they would like to use
		System.out.println("Enter type of encryption algorithm: ");
		Scanner keyboard = new Scanner(System.in);
		String type = keyboard.nextLine();
		KTsai_Cryptable cType = null;
		
		if(type.equals("UnstoppableCrypt"))
			cType = new KTsai_UnstoppableCrypt();
		else if(type.equals("VigenereCrypt"))
			cType = new KTsai_VigenereCrypt();
		else if(type.equals("PigLatin"))
			cType = new KTsai_PigLatin();
		
		KTsai_Cryptography c = new KTsai_Cryptography(cType);
	}
}
