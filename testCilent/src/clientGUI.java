import javax.swing.*;

/**
 * @author StarsEnd
 * @since 2021-01-10
 */
public class clientGUI {
    private JTextField portTextField;
    private JTextField a127001TextField;

    public static void main(String[] args) {
        JFrame frame = new JFrame("clientGUI");
        frame.setContentPane(new clientGUI().main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private JTextArea textArea1;
    private JButton sendButton;
    private JButton connectButton;
    private JTextField textField1;
    private JPanel port;
    private JPanel ip;
    private JPanel input;
    private JPanel main;
}
