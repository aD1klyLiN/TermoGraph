import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {

    private JFileChooser fc = new JFileChooser();

    public MainWindow() throws HeadlessException {
        super("Main Window");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JButton open = new JButton("Открыть...");
        open.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fc.setDialogTitle("Выберите каталог");
                // настроим для выбора каталога
                fc.setFileSelectionMode(
                        JFileChooser.FILES_AND_DIRECTORIES);
                int res = fc.showOpenDialog(
                        MainWindow.this);
                // если файл выбран, покажем его
                if ( res == JFileChooser.APPROVE_OPTION )
                    JOptionPane.showMessageDialog(
                            MainWindow.this,
                            fc.getSelectedFile());
            }
        });

        setLayout(new FlowLayout());
        add(open);
        //add(save);

        setSize(400, 300);
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
