package mn.learning;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        FileDownloader file1 = new FileDownloader("file1.zip", 10, 2);
        FileDownloader file2 = new FileDownloader("file2.mp4", 25, 5);
        FileDownloader file3 = new FileDownloader("file3.pdf", 5, 1);

        file1.start();
        file2.start();
        file3.start();

        try {
            file1.join();
            file2.join();
            file3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

            System.out.println("All downloads completed!");
        }
}
