import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author StarsEnd
 * @since 2021-01-10
 */
public class main {
    /**
     * 服务器主程序
     */
    private ThreadPoolExecutor MainThraedPool = new ThreadPoolExecutor
            (5,10,1000L,TimeUnit.MINUTES,
                    new ArrayBlockingQueue<Runnable>(10));
    public static void main(String[] args){
    }

}
