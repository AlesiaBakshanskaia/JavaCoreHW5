package HW5;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        //создаем папку для резервной копии
        File backupCopy = new File(".\\backup");
        backupCopy.mkdir();

        createCopy(new File("."), ".\\backup");
    }

    /**
     * метод побитового копирования файла
     * @param originalFile путь к копируемому файлу
     * @param copyFile путь для создания копии
     * @throws IOException искоючение пробрасываем выше
     */
    static void copyFile(String originalFile, String copyFile) throws IOException {
        // На запись
        try (FileOutputStream fileOutputStream = new FileOutputStream(copyFile)) {
            int c;
            // На чтение
            try (FileInputStream fileInputStream = new FileInputStream(originalFile)) {
                while ((c = fileInputStream.read()) != -1) {
                    fileOutputStream.write(c);
                }
            }
        }
    }

    /**
     * метод прохождения дерева файлов
     * @param originalFile копируемый объект
     * @param pathForCopy путь для размещения копии
     * @throws IOException исключение пробрасываем выше
     */
    static void createCopy(File originalFile, String pathForCopy) throws IOException {

        File[] files = originalFile.listFiles();
        if (files == null)
            return;

        for (File file : files) {


            if (file.isDirectory()) {

                if (!(file.getPath().equals(pathForCopy))) {   //пропускать папку, в которую мы все копируем
                    File copyDir = new File(pathForCopy + file.getPath().substring(1));
                    copyDir.mkdir();
                    createCopy(file, pathForCopy);
                }

            }
            if (file.isFile()) {
                copyFile(file.getPath(), pathForCopy + file.getPath().substring(1));
            }
        }
    }
}

