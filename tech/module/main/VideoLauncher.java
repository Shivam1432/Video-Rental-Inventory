package tech.module.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import tech.module.bean.VideoStore;
import tech.module.bean.Video;
import java.util.Scanner;

public class VideoLauncher implements ActionListener{
	VideoStore store = new VideoStore();
	JTextField tf1,tf2;
	JButton b1,b2,b3,b4,b5,b6;
	JTextArea t;
	JLabel l1,l2;
	VideoLauncher()
	{
		JFrame f = new JFrame();
		
		
		b1 = new JButton("Add Video");
		b1.setBounds(34, 56, 170, 40);
		
		b2 = new JButton("Checkout");
		b2.setBounds(34, 128, 170, 40);
		
		b3 = new JButton("Return Video");
		b3.setBounds(34, 197, 170, 40);
		
		b4 = new JButton("Receive Rating");
		b4.setBounds(34, 266, 170, 40);
		
		b5= new JButton("List Inventory");
		b5.setBounds(34, 340, 170, 40);
		
		tf1 = new JTextField();
		tf1.setBounds(327, 56, 288, 28);
		tf1.setColumns(10);
		tf1.setVisible(false);
		
		l1 = new JLabel("Enter Name:");
		l1.setBounds(327, 20, 106, 28);
		l1.setVisible(false);
		
		l2 = new JLabel("Enter Rating:");
		l2.setBounds(327, 95, 93, 28);
		l2.setVisible(false);
		
		tf2 = new JTextField();
		tf2.setBounds(327, 139, 288, 29);
		tf2.setColumns(10);
		tf2.setVisible(false);
		
		t = new JTextArea();
		t.setBounds(327, 197, 288, 258);
		
		b6 = new JButton("Submit");
		b6.setBounds(34, 415, 170, 40);
		
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					l1.setVisible(true);
					tf1.setVisible(true);
					l2.setVisible(false);
					tf2.setVisible(false);
					tf1.setText("");
					tf2.setText("");
					t.setText("");
					b6.setActionCommand("add");
			}
		});
		
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					l1.setVisible(true);
					tf1.setVisible(true);
					l2.setVisible(false);
					tf2.setVisible(false);
					tf1.setText("");
					tf2.setText("");
					t.setText("");
					b6.setActionCommand("check");
				
			}
		});
		
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					l1.setVisible(true);
					tf1.setVisible(true);
					l2.setVisible(false);
					tf2.setVisible(false);
					tf1.setText("");
					tf2.setText("");
					t.setText("");
					b6.setActionCommand("return");
				
			}
		});
		
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					l1.setVisible(true);
					tf1.setVisible(true);
					l2.setVisible(true);
					tf2.setVisible(true);
					tf1.setText("");
					tf2.setText("");
					t.setText("");
					b6.setActionCommand("rating");
				
			}
		});
		
		b5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					b6.setActionCommand("list");
					tf1.setText("");
					tf2.setText("");
					l2.setVisible(false);
					tf2.setVisible(false);
					String data="";
					String newLine="\n";
					data+="Name\tRating\tCheckout\n";
					for(Video v:store.getStore())
					{
						data+=String.valueOf(v.getName()+"\t"+v.getRating()+"\t"+v.getCheckout()+newLine);
					}
					t.setText(data);
				}
					
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null,ex);
				}
			}
		});
		
		b6.addActionListener(this);
		f.add(b1);
		f.add(b2);
		f.add(b3);
		f.add(b4);
		f.add(b5);
		f.add(b6);
		f.add(l1);
		f.add(l2);
		f.add(t);
		f.add(tf1);
		f.add(tf2);
		f.setSize(900, 900);
		f.setLayout(null);
		f.setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
			if("add".equals(e.getActionCommand()))
			{
				String name=tf1.getText();
				if(name==null)
					JOptionPane.showMessageDialog(null, "Name cannot be empty");
				else
				{
					store.addVideo(name);
					t.setText("Video " + name + " added successfully.");
				}
				
			}
			if("check".equals(e.getActionCommand()))
			{
				String name=tf1.getText();
				if(name==null)
					JOptionPane.showMessageDialog(null, "Name cannot be empty");
				else
				{
					store.doCheckout(name);
					t.setText("Video " + name + " checked out successfully.");
				}
				
			}
			if("return".equals(e.getActionCommand()))
			{
				String name=tf1.getText();
				if(name==null)
					JOptionPane.showMessageDialog(null, "Name cannot be empty");
				else
				{
					store.doReturn(name);
					t.setText("Video " + name + " returned successfully.");
				}
			}
			if("rating".equals(e.getActionCommand()))
			{
				String name=tf1.getText();
				if(name==null)
					JOptionPane.showMessageDialog(null, "Name cannot be empty");
				String rate=tf2.getText();
				if(rate==null)
					JOptionPane.showMessageDialog(null, "Rating cannot be empty");
				int rating=Integer.parseInt(rate);
				if(rating<0)
					JOptionPane.showMessageDialog(null, "Reenter the number");
				else
				{
					store.receiveRating(name, rating);
					t.setText("Rating " + rating + " has been mapped to the Video " + name + ".");
				}
			}
			if("list".equals(e.getActionCommand()))
			{
				t.setText("Cannot submit this");
			}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new VideoLauncher();
		
		Scanner sc = new Scanner(System.in);
		VideoStore store = new VideoStore();
		int choice = 0;
		
		do {
			System.out.println("\n1. Add Videos: \n" + 
								"2. Check Out Video: \n" + 
								"3. Return Video: \n" + 
								"4. Receive Rating: \n" + 
								"5. List Inventory: \n" + 
								"6. Exit: \n" + 
								"Enter your choice (1..6): ");
			choice = sc.hasNextInt() ? sc.nextInt() : 6;
			sc.nextLine();
			
			String name;
			
			switch (choice) {			
			case 1:
				System.out.println("Enter the name of the video you want to Add: ");
				name = sc.nextLine();
				store.addVideo(name);
				System.out.println("Video " + name + " added successfully.");
				break;
			case 2:
				System.out.println("Enter the name of the video you want to Checkout: ");
				name = sc.nextLine();
				store.doCheckout(name);
				System.out.println("Video " + name + " checked out successfully.");
				break;
			case 3:
				System.out.println("Enter the name of the video you want to Return: ");
				name = sc.nextLine();
				store.doReturn(name);
				System.out.println("Video " + name + " returned successfully.");
				break;
			case 4:
				System.out.println("Enter the name of the video you want to Rate: ");
				name = sc.nextLine();
				System.out.println("Enter the rating for this video: ");
				int rating = sc.nextInt();
				store.receiveRating(name, rating);
				System.out.println("Rating " + rating + " has been mapped to the Video " + name + ".");
				break;
			case 5:
				store.listInventory();
				break;
			default:
				System.out.println("Exiting...!! Thanks for using the application.");
				break;
			}
		} while (choice != 6);

		sc.close();
	}

}
