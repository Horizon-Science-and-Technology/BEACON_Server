import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author StarsEnd
 * @since 2021-01-10
 */
public class clientGUI {
    private JTextField portTextField;
    private JTextField ipTextField;
    private static UdpClient udpClient=new UdpClient();

    public clientGUI() {
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var ip = ipTextField.getText();
                var port=portTextField.getText();
                var tle="ip:"+ip+"  "+"port:"+port;
                System.out.println(tle+"\n");
                try {
                    udpClient.setRemoteIP(InetAddress.getByName(ip));
                    udpClient.setRemotePort(Integer.valueOf(port));
                } catch (UnknownHostException unknownHostException) {
                    unknownHostException.printStackTrace();
                }
                textArea1.append("UDP创建成功."+tle+"\r\n");
                Thread receiver = new Thread(()->{
                        String msg =null;
                    while(true){
                            try{
                                msg = udpClient.receive();
                            } catch(Exception ex){
                                textArea1.append("套接字异常关闭"+"\n");
                            }
                            if(msg!=null){
                                textArea1.append(msg+"\n");
                            }
                            else{
                                textArea1.append("你的对话已关闭！"+"\n");
                                break;
                            }
                        }
                    });
                receiver.start();
            }
        });
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("clientGUI");
        frame.setContentPane(new clientGUI().main);
        frame.setSize(500,350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private JTextArea textArea1;
    private JButton sendButton;
    private JButton connectButton;
    private JPanel main;
    private JTextField textField3;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;

}
