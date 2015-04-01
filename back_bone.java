package threaded_crawler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.DefaultListModel;

public class back_bone implements ActionListener{
	
	static String current;
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equalsIgnoreCase("Select")){
			String selected=GUI.filelist.getSelectedValue().toString();
			if (!selected.equalsIgnoreCase("Back")){
				current=selected;
				make_list(selected);
			}
			else{
				if (current!=GUI.root)
					make_list(current+"\\..");
			}
		}		
	}
	void make_list(String selected){
		System.out.println(selected);
		File file = new File(selected);
		File[] files = file.listFiles();
		DefaultListModel<String> filepaths = new DefaultListModel<String>();
		for (int i = 0;i<files.length;i++){
			if (!files[i].isHidden()){
				filepaths.addElement(files[i].getPath());
			}		
		}
		filepaths.addElement("Back");
		GUI.filelist.setModel(filepaths);
		GUI.filelist.setSelectedIndex(0);
	}
}
