package threaded_crawler;

import java.io.File;

public class crawler implements Runnable{
	String path;
	public crawler(String current_path){
		this.path=current_path;
	}
	@Override
	public void run() {
		try {
		int thread_count=0;int max_threads=3;
		Thread[] th = new Thread[max_threads];
		File file = new File(path);
		File[] files = file.listFiles();
		for (int i = 0;i<files.length;i++){
			if (!files[i].isDirectory()&&!files[i].isHidden()){
				GUI.lt.lock();
				GUI.ht.put(files[i].getName(), files[i].getPath());
				GUI.lt.unlock();
			}
			else if (files[i].isDirectory()&&!files[i].isHidden()){
				if (thread_count>=max_threads){
					for (int j=0;j<max_threads;j++)
							th[j].join();
					thread_count=0;
				}
				th[thread_count] = new Thread(new crawler(files[i].getPath()));th[thread_count].start();thread_count++;
			}			
		}
		} catch (InterruptedException e) {e.printStackTrace();}
	}
}
