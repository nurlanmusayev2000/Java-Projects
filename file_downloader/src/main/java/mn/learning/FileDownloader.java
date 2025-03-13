package mn.learning;

import java.util.Random;

public class FileDownloader extends Thread {
	private String file;
	private int fileSize;
	private int speed;


	public FileDownloader(String file,int fileSize, int speed ) {
		this.file = file;
		this.fileSize = fileSize;
		this.speed = speed;
	}

	public void run() {
		System.out.println(Thread.currentThread().getName() + " started downloading " + file);

		int downloaded = 0;
		while (downloaded < fileSize) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			downloaded += speed;
			if (downloaded > fileSize) {
				downloaded = fileSize;
			}

			System.out.println(
					Thread.currentThread().getName() + " [" + file + "] " + downloaded + " MB /" + fileSize + " MB downloaded");
		}
		System.out.println(Thread.currentThread().getName()+ " finished download "+ file);
	}
}
