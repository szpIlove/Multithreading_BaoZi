public class DemoTest {
    public static void main(String[] args) {
        BaoZi bz=new BaoZi();
        //调用构造函数
        new BaoZiPu(bz).start();
        new ChiHuo(bz).start();

    }
}
