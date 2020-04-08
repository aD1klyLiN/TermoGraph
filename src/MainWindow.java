import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MainWindow extends JFrame {

    private int width = 640;
    private int height = 480;
    private JFileChooser fileChooser = new JFileChooser();
    private File filePath;
    private MainRepository mainRepository;

    public MainWindow() throws HeadlessException {
        super("Main Window");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        mainRepository = MainRepository.getInstance();

        JButton open = new JButton("Открыть...");
        open.addActionListener(new OpenButtonListener());

        setLayout(new FlowLayout());
        add(open);
        //add(save);

        Rectangle bounds = getGraphicsConfiguration().getBounds();
        setLocation(bounds.width/2 - width/2, bounds.height/2 - height/2);
        setSize(width, height);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                new Runnable() {
                    @Override
                    public void run() {
                        new MainWindow();
                    }
                }
        );
    }

    class OpenButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            fileChooser.setDialogTitle("Выберите каталог");
            // настроим для выбора каталога
            fileChooser.setFileSelectionMode(
                    JFileChooser.DIRECTORIES_ONLY);
            int res = fileChooser.showOpenDialog(
                    MainWindow.this);
            // если файл выбран, покажем его
            if ( res == JFileChooser.APPROVE_OPTION ) {
                File file = fileChooser.getSelectedFile();
                File[] listFiles = file.listFiles();
                if (listFiles == null) throw new NullPointerException("Array of source files not found");
                ArrayList<File> listSourceFiles = new ArrayList<File>();
                for (File listFile : listFiles) {
                    if (listFile.getName().contains("GROUP03")) listSourceFiles.add(listFile);
                }
                Collections.sort(listSourceFiles);
                JOptionPane.showMessageDialog(
                        MainWindow.this,
                        "Найдено " + listSourceFiles.size() + " файлов");

                try {
                    mainRepository.loadData(listSourceFiles);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
