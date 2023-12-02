package HW5;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        checkDir(new File("."), ".\\backup");
    }

    static void copyFile(String fileNameIn, String fileNameOut) throws IOException {
        // На запись
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileNameOut)) {
            int c;
            // На чтение
            try (FileInputStream fileInputStream = new FileInputStream(fileNameIn)) {
                while ((c = fileInputStream.read()) != -1) {
                    fileOutputStream.write(c);
                }
            }
        }
    }

//    private static void copyDirectory(File sourceDirectory, File destinationDirectory) throws IOException {
//        if (!destinationDirectory.exists()) {
//            destinationDirectory.mkdir();
//        }
//        for (String f : sourceDirectory.list()) {
//            copyDirectoryCompatibityMode(new File(sourceDirectory, f), new File(destinationDirectory, f));
//        }
//    }
//
//    public static void copyDirectoryCompatibityMode(File source, File destination) throws IOException {
//        if (source.isDirectory()) {
//            copyDirectory(source, destination);
//        } else {
//            copyFile(source, destination);
//        }
//    }


    static void checkDir(File filein, String fileout) throws IOException {

        File[] files = filein.listFiles();
        if (files == null)
            return;

        for (int i = 0; i < files.length; i++) {

            String str = files[i].getPath();

            if (files[i].isDirectory()) {
                File distination = new File(fileout + str.substring(1));
                distination.mkdir();

//                checkDir(files[i], fileout + str.substring(1));
            }
            if (files[i].isFile()) {
                copyFile(files[i].getName(), fileout + str.substring(1));
            }
        }
    }
}

