package demo01;


import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class HelloWorld extends UntypedActor {

    ActorRef greeter;

    /**
     * Akka的回调方法，在Actor启动前，会被Akka框架调用，完成一些初始化工作
     */
    @Override
    public void preStart() {
        // 创建Greeter实例
        greeter = getContext().actorOf(Props.create(Greeter.class), "greeter");
        System.out.println("Greeter Actor Path: " + greeter.path());
        // 发生Greet消息
        greeter.tell(Greeter.Msg.GREET, getSelf());
    }

    /**
     * 消息处理函数
     * @param msg
     * @throws Exception
     */
    @Override
    public void onReceive(Object msg) throws Exception {

        // 这个消息处理函数，只处理Done的消息
        if (msg == Greeter.Msg.DONE) {
            greeter.tell(Greeter.Msg.GREET, getSelf());
            getContext().stop(getSelf());
        } else {
            unhandled(msg);
        }

    }
}
