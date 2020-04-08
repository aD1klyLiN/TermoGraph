import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainRepository {

    private static final MainRepository INSTANCE = new MainRepository();

    private ArrayList<File> listSourceFiles;

    private MainRepository() {}

    public static MainRepository getInstance() {
        return INSTANCE;
    }

    public ArrayList<File> getListSourceFiles() {
        return listSourceFiles;
    }

    public void setListSourceFiles(ArrayList<File> listSourceFiles) {
        this.listSourceFiles = listSourceFiles;
    }

    public void loadData (List<File> listSourceFiles) throws IOException {
        ArrayList<String> listSourceStrings = new ArrayList<>();
        for (File sourceFile : listSourceFiles) {
            if (!checkFile(sourceFile)) throw new IllegalArgumentException("No data records found");

            BufferedReader br = new BufferedReader(new FileReader(sourceFile));
            String line;
            line = br.readLine();
            line = br.readLine();
            line = br.readLine();
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                listSourceStrings.add(line);
            }
        }
        System.out.println(listSourceStrings.size());
    }

    public boolean checkFile (File filepath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filepath));
        String line;
        while ((line = br.readLine()) != null) {
            if (line.contains("PASTEUR TEMPER") && line.contains("OUT PRODUCT TEMP")) {
                return true;
            }
        }

        return false;
    }

}
