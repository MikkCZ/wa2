package cz.cvut.fel.stankmic.wa2.data;

import cz.cvut.fel.stankmic.wa2.data.config.DataConfig;
import cz.cvut.fel.stankmic.wa2.data.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.PrintStream;

public class Main {

    public static void main(String[] args) {
        Main m = new Main();
        m.test(System.out);
    }

    private void test(PrintStream out) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(DataConfig.class);
        UserService userService = ctx.getBean(UserService.class);
    }

}
