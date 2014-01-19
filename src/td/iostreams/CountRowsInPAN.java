package td.iostreams;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

/**
 * Created by mikher on 2014-01-17.
 * <p/>
 * Class used to count rows in files
 */
public class CountRowsInPAN {

    private static final String PAN_PATH = "/Users/mikher/dev/GitHub/TD/pan";
    Map<String, Integer> stats = new HashMap<>();

    public static void main(String[] args) throws IOException {
        CountRowsInPAN countRows = new CountRowsInPAN();
        countRows.getFiles(Paths.get(PAN_PATH));
        countRows.printStats();
    }

    private List<Map.Entry<String, Integer>> sortEntries(Map<String, Integer> map) {

        List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());

        Collections.sort(sortedEntries,
                new Comparator<Map.Entry<String, Integer>>() {
                    @Override
                    public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                        return e2.getValue().compareTo(e1.getValue());
                    }
                }
        );

        return sortedEntries;
    }

    private void addStats(String fileName, Integer count) {
        String[] splitFileName = fileName.split("\\.");
        String fileExtension;
        int numOfTokens = splitFileName.length;
        if (numOfTokens >= 1) {
            fileExtension = splitFileName[numOfTokens - 1];
            Integer countPerExtension = stats.get(fileExtension);
            if (countPerExtension == null) {
                countPerExtension = 0;
            }
            if (countPerExtension != null) {
                stats.put(fileExtension, countPerExtension + count);
            }
        }
    }

    public void printStats() {
        List<Map.Entry<String, Integer>> entries = sortEntries(stats);

        for (Map.Entry entry : entries) {
            System.out.print("File type=" + entry.getKey());
            System.out.println(", rowCount=" + entry.getValue());
        }

        System.out.println("-----------All stats done-----------");
    }

    /**
     * Count the number of rows in a file
     *
     * @param fileName
     * @return number of rows
     * @throws IOException
     */
    public Integer countRows(String fileName) throws IOException {
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

    /**
     * List all files under the root provided. Exclude .git directory if exist.
     *
     * @param rootPath
     * @throws IOException
     */
    void getFiles(Path rootPath) throws IOException {

        Collection<Path> allFiles = new ArrayList<>();
        addTree(rootPath, allFiles);

        int rows = 0;
        for (Path filePath : allFiles) {
            //if (!filePath.toString().contains("/.git")) {
            rows = countRows(filePath.toString());
            addStats(filePath.toString(), rows);
            //}
        }
    }


    /**
     * Visit all files with Java 7 support through SimpleFileVisitor
     * Excludes all files with a path that contains .git
     *
     * @param rootDir
     * @param allFiles
     * @throws IOException
     */
    static void addTree(Path rootDir, final Collection<Path> allFiles) throws IOException {

        // final PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("regex:\\.git+");

        Files.walkFileTree(rootDir, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (attrs.isRegularFile() && !attrs.isDirectory() && !file.toString().contains(".git")) {
                    allFiles.add(file);
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
