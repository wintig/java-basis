package demo01;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;

public class HelloMainSimple {

    public static void main(String[] args) {
        // 管理和维护Actor系统，一般来说一个应用程序只需要一个ActorSystem就够了，ActorSystem.create()函数的第一个参数“hello”为系统名称，第二个为配置文件
        ActorSystem system = ActorSystem.create("Hello", ConfigFactory.load("sampleHello.conf"));

        // 创建一个顶级Actor
        ActorRef a = system.actorOf(Props.create(HelloWorld.class), "helloWorld");
        System.out.println("HelloWorld Actor Path:" + a.path());
    }

}
