public class BaoZiPu extends Thread{

    int count=0;
    //在成员位置创建一个包子变量
    private BaoZi bz;
    //添加一个带参数构造方法，为包子赋值

    public BaoZiPu(BaoZi bz) {
        this.bz = bz;
    }
    //继承Thread类，重写run方法，生产包子
    @Override
    public void run(){
        //使用同步技术，保证两个线程只能有一个在执行
        //死循环，生产多个包子
        while(true) {
            synchronized (bz) {
                //对包子状态进行判断
                if (bz.flag == true) {
                    //有包子，必须使用锁对象调用wait或notif对象
                    try {
                        bz.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //生产包子，唤醒之后执行
                if (count % 2 == 0) {
                    bz.pi = "薄皮";
                    bz.xian = "三鲜馅";
                } else {
                    bz.pi = "冰皮";
                    bz.xian = "牛肉大葱馅";
                }
                count++;
                System.out.println("包子铺正在生产" + bz.pi + bz.xian + "包子");
                //生产包子需3秒
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //包子生产好之后，状态为好
                bz.flag = true;
                //唤醒等待的吃货线程，使其吃包子
                bz.notify();
                System.out.println("包子已经生产好了" + bz.pi + bz.xian + "吃货可以吃了");
            }
        }
    }
}
