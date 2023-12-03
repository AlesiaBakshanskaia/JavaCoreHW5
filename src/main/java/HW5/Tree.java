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

        File[] files1 = file.listFiles();
        if (files1 == null)
            return;
        File[] files = new File[files1.length];
        int count = 0;
        for (File item : files1) {
            if (item.isDirectory()) {
                files[count] = item;
                count++;
            }
        }
        int subDirTotal = count;
        int subFileTotal = files.length-count;
        for (File item : files1) {
            if (item.isFile()) {
                files[count] = item;
                count++;
            }
        }


        int subDirCounter = 0;
        int subFileCounter = 0;
        for (File value : files) {
            if (value.isDirectory()) {
                print(value, indent, subDirTotal == ++subDirCounter && subFileTotal == 0);
            }
            if (value.isFile()) {
                print(value, indent, subFileTotal == ++subFileCounter);
            }
        }
    }
}
