package threaded_crawler;

import java.util.Hashtable;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class GUI {
	static Hashtable<String, String> ht;
	static Lock lt;static String root;
	static JFrame frame;static JButton button;static JList<String> filelist;static JPanel panel;
	
	@SuppressWarnings({ "resource" })
	public static void main(String[] args) throws InterruptedException{
		lt = new ReentrantLock();
		Scanner in = new Scanner(System.in);
		root = in.next();
		ht = new Hashtable<String, String>();
		Thread th = new Thread(new crawler(root));
		th.start();
		GUI ui = new GUI();
		ui.setup(root);
	}
	public void setup(String path){
		frame = new JFrame("Desktop Crawler");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 800);
		panel = new JPanel();
		DefaultListModel<String> filepaths = new DefaultListModel<String>();
		filepaths.addElement(path);
		filepaths.addElement("Back");
		filelist = new JList<String>(filepaths);
		filelist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    filelist.setSelectedIndex(0);filelist.setFixedCellWidth(1000);
	    filelist.setVisibleRowCount(3);filelist.setFixedCellHeight(20);
	    JScrollPane fileScroll = new JScrollPane(filelist);
	    panel.add(filelist);
	    panel.add(fileScroll);
		back_bone list = new back_bone();
		button = new JButton("Select");
		button.addActionListener(list);
		panel.add(button);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true); 	
	}
}
