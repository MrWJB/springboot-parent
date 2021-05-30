spring boot项目中的application.properties配置文件一共可以出现在4个位置：
1.项目根目录下的config文件夹中。
2.项目根目录下。
3.claspath下的config文件夹下。
4.classpath下。

加载的优先级： 1 > 2 > 3 > 4;
默认情况下按照上述优先级查找application.properties文件并加载。
如果开发者不想使用application.properties作为配置文件名，也可以使用自定义,如：app.properties。
将项目打成jar包后，可以使用如下命令运行：
eg: java -jar chapert02-2.0.1-SNAPSHOT.jar --spring.config.name=app
使用spring.config.location可以指定配置文件所在目录：
eg: java -jar chapert02-2.0.1-SNAPSHOT.jar --spring.config.name=app --spring.config.location=classpath:/

无论是properties还是yaml最终都会被加载到spring environm中。
spring提供了@Value注解以及EnvironmentAware接口来将spring environment中的数据注入到属性上。
spring boot对此进一步提出了类型安全的配置属性（Type-safe Configuration Properties）,这样即使在数据量非常庞大的情况下
也可以方便的将配置文件中的数据注入到Bean中。

yaml是json的超集，简洁而强大，是一种专门用来书写配置文件的语言，可以替代application.properties。
在引入spring-boot-starter-web依赖间接地引入了snakeaml依赖，knakeaml会实现对yaml的解析。
yaml使用非常简单，使用缩进来表示层级关系，并且大小写敏感。

位置：在resources目录下创建一个application.yml文件即可。

注意：在spring boot中使用yaml虽然方便但是yaml也有一些缺陷，例如无法使用@PropertySource注解加载yaml文件。如果项目中有这种需求，还是
需要使用Properties格式。


=======================================properties 或 yaml===============================================

Profile的使用：
一般项目中会涉及几套配置，如：开发环境，测试环境，生产环境。
spring对此提供了解决方案（@Profile注解）；
spring boot则进一步提供了更加简洁的解决方案，spring boot中约定的不同环境下配置文件名称规则为application-{profile}.properties,
profile占位符表示当前环境的名称。

四种配置方案：
    1.创建配置文件
        在resoures目录下创建application-dev.properties和application-prod.properties文件。
    2.配置application.properties
        配置spring.profiles.active=dev
    3.在代码中配置
        对于第二步在application.properties中添加的配置，我们也可以在代码中添加配置来完成。
        SpringApplicationBuilder builder = new SpringApplicationBuilder(PropertiesApplication.class);
        builder.application().setAdditionProfiles("prod");
        builder.run(args);
    4.项目启动时配置
        java -jar Properties-SNAPSHOP.jar --spring.profiles.active=prod
