
public class ChiHuo extends Thread {

    private BaoZi bz;
    //添加一个带参数构造方法，为包子赋值

    public ChiHuo(BaoZi bz) {
        this.bz = bz;
    }
    @Override
    public void run(){
        //使用同步技术，保证两个线程只能有一个在执行
        while(true) {
            synchronized (bz) {
                //无包子
                if(bz.flag==false){
                    try {
                        bz.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //唤醒之后执行的代码，吃包子
                System.out.println("吃货正在吃"+bz.pi+bz.xian+"包子");
                //吃货吃完包子，修改状态
                bz.flag=false;
                bz.notify();
                System.out.println("吃货已经吃完了"+bz.pi+bz.xian+"包子,包子铺开始生产包子了");
                System.out.println("------------------------------------");
            }

        }
        }
}
