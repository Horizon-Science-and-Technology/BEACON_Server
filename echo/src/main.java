import javax.swing.tree.FixedHeightLayoutCache;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.concurrent.*;

/**
 * @author StarsEnd
 * @since 2021-01-10
 */
public class main {
    /**
     * 服务器主程序
     */
    private static final String PASSWD="WDFGHJUYTRXDFHBFFXUJHV";

    public static void main(String[] args){
        ThreadPoolExecutor mainThraedPool = new ThreadPoolExecutor
                (2,5,1L,TimeUnit.MINUTES,
                        new LinkedBlockingQueue<Runnable>());
        //验证身份
        mainThraedPool.execute(() ->{
            ThreadPoolExecutor udpThreadPool = new ThreadPoolExecutor(3,7,1L,TimeUnit.MINUTES,
                    new LinkedBlockingQueue<Runnable>());
            try ( DatagramSocket socket = new DatagramSocket(8800);){
                while (udpThreadPool.prestartCoreThread()){
                    udpThreadPool.execute(()->{
                        //UDP
                        try {
                            byte[] data = new byte[1024];// 创建字节数组，指定接收的数据包的大小
                            DatagramPacket packet = new DatagramPacket(data, data.length);
                            // 3.接收客户端发送的数据
                            System.out.println("****服务器端已经启动，等待客户端发送数据");
                            socket.receive(packet);// 此方法在接收到数据报之前会一直阻塞
                            String info = new String(data, 0, packet.getLength());
                            System.out.println("我是服务器，客户端说：" + info);
                            if (info.equals(PASSWD)){
                                // 1.定义客户端的地址、端口号、数据
                                InetAddress address = packet.getAddress();
                                int port = packet.getPort();
                                byte[] data2 = ("密钥验证成功，烽火欢迎您!").getBytes();
                                // 2.创建数据报，包含响应的数据信息
                                DatagramPacket packet2 = new DatagramPacket(data2, data2.length, address, port);
                                // 3.响应客户端
                                socket.send(packet2);
                            }
                        } catch (SocketException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });}
            } catch (SocketException e) {
                e.printStackTrace();
            }

        });
    }

}
class DoThread implements Runnable{
    @Override
    public void run() {

    }
}
class PortDoThread extends DoThread{
    @Override
    public void run() {
        super.run();
    }
}