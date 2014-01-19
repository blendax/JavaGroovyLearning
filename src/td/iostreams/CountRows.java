package td.iostreams;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by mikher on 2014-01-17.
 * <p/>
 * Class used to count rows in files
 */
public class CountRows {

    public static void main(String[] args) throws IOException {
        CountRows countRows = new CountRows();
        System.out.println(countRows.countRows("xanadu.txt"));
        countRows.getPANJavaFiles();

    }

    /**
     * Count the number of rows in a file
     *
     * @param fileName
     * @return number of rows
     * @throws IOException
     */
    public int countRows(String fileName) throws IOException {
        BufferedReader bufferedReader = null;
        int numberOfLines = 0;
        try {
            bufferedReader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                numberOfLines++;
            }
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }

        return numberOfLines;
    }

    void getPANJavaFiles() throws IOException {

        final String PAN_PATH = "/Users/mikher/dev/GitHub/TD/pan";
        // Get PAN Path
        Path path = FileSystems.getDefault().getPath(PAN_PATH);

        Collection<Path> allFiles = new ArrayList<>();
        addTree(path, allFiles);

        int rows = 0;
        for(Path filePath : allFiles){
            rows = countRows(filePath.toString()) + rows;
        }

        System.out.println("allFiles.size()=" + allFiles.size());
        System.out.println("Rows=" + rows);

    }

    /**
     * Visit all files with Java 7 support through SimpleFileVisitor
     *
     * @param directory
     * @param all
     * @throws IOException
     */
    static void addTree(Path directory, final Collection<Path> all) throws IOException {

        Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (attrs.isRegularFile()) {
                    if (file.toString().endsWith(".java")) {
                        all.add(file);
                    }
                }
                return FileVisitResult.CONTINUE;
            }
        });
    }

   /* static void addTree(Path directory, Collection<Path> all)
            throws IOException {
        try (DirectoryStream<Path> ds = Files.newDirectoryStream(directory)) {
            for (Path child : ds) {
                all.add(child);
                if (Files.isDirectory(child)) {
                    addTree(child, all);
                }
            }
        }
    }*/

}
