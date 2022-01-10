package testFile;

import java.io.*;

public class FileTest implements Serializable {

    private Object obj = new Object();

    public static void main(String[] args) throws IOException {
        /*var ob = new FileTest();

        try (
                var objWriter = new ObjectOutputStream(
                        new BufferedOutputStream(
                                new FileOutputStream("kek")
                        )
                )
        ) {
            objWriter.writeObject(ob);
        }

        try (var reader = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream("kek")
                )
        )) {
            FileTest test = (FileTest) reader.readObject();
            System.out.println(test.obj);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/

        var console = System.console();
        if (console != null) {
            Writer w = console.writer();

            try (w) {
                w.append("Heh");
                w.flush();
            }
        }
    }
}
