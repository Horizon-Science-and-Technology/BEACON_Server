import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
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
                                //textArea1.append("套接字异常关闭"+"\n");
                                appendTextArea("套接字异常关闭"+"\n");
                            }
                            if(msg!=null){
                                //textArea1.append(msg+"\n");
                                appendTextArea(msg+"\n");
                            }
                            else{
                                //textArea1.append("你的对话已关闭！"+"\n");
                                appendTextArea("你的对话已关闭！"+"\n");
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
                var msg =textField3.getText();
                //textArea1.append("Client:"+msg+"\t\n");
                appendTextArea("Client:"+msg+"\t\n");
            udpClient.send(msg);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("clientGUI");
        frame.setContentPane(new clientGUI().main);
        frame.setSize(500,350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            Image img = ImageIO.read(new FileInputStream("C:\\Users\\StarsEnd\\Documents\\GitHub\\BEACON_Server\\testCilent\\src\\1.png"));
            frame.setIconImage(img);
        } catch (IOException e) {
            e.printStackTrace();
        }
        frame.setVisible(true);
    }

    //每次需要在JTextArea增加String时,调用如下方法
    private void appendTextArea(String text){
        textArea1.append(text);

        //在更新logArea后，稍稍延时，否则getMaximum（）获得的数据可能不是最后的最大值，无法滚动到最后一行
        try {
            Thread.sleep(70);
        } catch (InterruptedException ex) {

        }
        scroll.getVerticalScrollBar().setValue(scroll.getVerticalScrollBar().getMaximum());
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
    private JScrollPane scroll;

}
