package HW5;


import java.io.File;

/**
 * TODO: Доработать метод print, необходимо распечатывать директории и файлы
 */
public class Tree {

    public static void main(String[] args) {
        print(new File("."), "", true);
    }

    static void print(File file, String indent, boolean isLast) {
        System.out.print(indent);
        if (isLast) {
            System.out.print("└─");
            indent += "  ";
        } else {
            System.out.print("├─");
            indent += "│ ";
        }
        System.out.println(file.getName());

        File[] files = file.listFiles();
        if (files == null)
            return;

        int subDirTotal = 0;
        for (File value : files) {
            if (value.isDirectory())
                subDirTotal++;
        }
        int subFileTotal = 0;
        for (File value : files) {
            if (value.isFile())
                subFileTotal++;
        }

        int subDirCounter = 0;
        int subFileCounter = 0;
        for (File value : files) {
            if (value.isDirectory()) {
                print(value, indent, subDirTotal == ++subDirCounter);
            }
            if (value.isFile()) {
                print(value, indent, subFileTotal == ++subFileCounter);
            }
        }
    }
}
