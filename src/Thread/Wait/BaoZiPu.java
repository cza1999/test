package Thread.Wait;
/*
生产者(包子铺)类:是一个线程类,可以继承Thread设置线程任务(run):生产包子
        对包子的状态进行判断
        true:有包子
        包子铺调用wait方法进入等待状态false:没有包子
        包子铺生产包子
        增加一些趣味性:交替生产两种包子
        有两种状态(i2%2==)
        包子铺生产好了包子
        修改包子的状态为true有
        唤醛吃货线程,让吃货线程吃包子
        注意;
        包子铺线程和包子线程关系-->通信(互斥)
 必须同时同步技术保证两个线程只能有一个在执行锁对象必须保证唯一,
 可以使用包子对象作为锁对象
        包子铺类和吃货的类就需要把包子对象作为参数传递进来
        1.需要在成员位置创建一个包子变量
        2使用带参数构造方法,为这个包子变量赋值


*/

public class BaoZiPu extends Thread{
    private  BaoZi bz;
    public  BaoZiPu(BaoZi bz){
        this.bz=bz;
    }


    @Override
public  void run(){
    int count =0;
    while(true){
        synchronized (bz){
            if(bz.flag==true){
                try {
                    bz.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (count%2==0){
                bz.pi="薄皮";
                bz.xian="三鲜";
            }else {
                bz.pi="冰皮";
                bz.xian="牛肉";
            }
            count++;
            System.out.println("包子铺正在生产"+bz.pi+" "+bz.xian+"包子");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            bz.flag=true;
            bz.notify();
            System.out.println("包子铺已经生产好了"+bz.pi+" "+bz.xian+"包子");
        }

    }

    }
}
