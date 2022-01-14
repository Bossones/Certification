package testCreatingDirectories;

import java.io.IOException;
import java.nio.file.*;

public class TestCreatingDirectories {

    public static void main(String[] args) throws IOException {

//        Files.createDirectories(Paths.get("./files/innreFiles"));
        var p = Paths.get("/zoo/animals/bear/koala/food.txt");
        System.out.println(p.subpath(1, 3).getName(1).toAbsolutePath());

        var p1 = Path.of(".", "/", "goat.txt").normalize();
        var p2 = Path.of("./dog.txt");
        var stream = Files.find(Paths.get("."), 0, (pat, a) -> false);

        try (stream) {
            stream.forEach(System.out::println);
        }

        System.out.println(Path.of(".").getNameCount());

//        System.out.println(p1);
/*        System.out.println(p1.resolve(p2));
        System.out.println(p2.resolve(p1));

        Files.move(p1, p2, StandardCopyOption.ATOMIC_MOVE, LinkOption.NOFOLLOW_LINKS);*/
    }
}
