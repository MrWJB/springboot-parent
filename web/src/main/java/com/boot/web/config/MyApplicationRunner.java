package com.boot.web.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@Order(1)
public class MyApplicationRunner implements ApplicationRunner {

    /**
     * 如果想从ApplicationArguments对象中获取入口类中main方法接收的参数，调用ApplicationArguments中的
     * getNonOptionsArgs方法即可。
     * ApplicationArguments中的getOptionNames方法用来获取项目启动命令行参数的key,例如：
     * 将项目打成jar包，运行Java -jar xxx.jar -name=Michael 命令来启动项目，此时getOptionsNames方法获取到的就时name,
     * 而getOptionsValues方法则是获取相应的value.
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<String> nonOptionArgs = args.getNonOptionArgs();
        Set<String> optionNames = args.getOptionNames();
        for (String key: optionNames){
            List<String> values = args.getOptionValues(key);
        }
    }
}
