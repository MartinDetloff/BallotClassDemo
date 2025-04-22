import java.io.*;

public class main {
    public static void main(String[] args) throws FileNotFoundException {
        ClassLoader classLoader = main.class.getClassLoader();
        File file = new File(classLoader.getResource("markUpEx1.txt").getFile());
        Ballot b = new Ballot(file);
        b.toString();
    }
}
