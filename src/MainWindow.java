import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {

    private int width = 640;
    private int height = 480;
    private JFileChooser fileChooser = new JFileChooser();
    private String filePath;

    public MainWindow() throws HeadlessException {
        super("Main Window");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JButton open = new JButton("Открыть...");
        open.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fileChooser.setDialogTitle("Выберите каталог");
                // настроим для выбора каталога
                fileChooser.setFileSelectionMode(
                        JFileChooser.FILES_AND_DIRECTORIES);
                int res = fileChooser.showOpenDialog(
                        MainWindow.this);
                // если файл выбран, покажем его
                if ( res == JFileChooser.APPROVE_OPTION ) {
                    JOptionPane.showMessageDialog(
                            MainWindow.this,
                            fileChooser.getSelectedFile());
                    filePath = fileChooser.getSelectedFile().getAbsolutePath();
                    System.out.println(filePath);
                }
            }
        });

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

}
