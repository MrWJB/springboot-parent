=============================HTTPS配置 开始==================================

## HTTPS配置：
通过Java工具生成数字证书 》 jdk\bin目录下；
生成命令：keytool -genkey -alias tomcathttps -keyalg RSA -keysize 2048 -keystore wang.p12 -validity 365

命令解释：
-genkey：表示要创建一个新的密钥；
-alias：表示keystore的别名；
-keyalg：表示使用的加密算法是RSA，这是一种非对称加密算法；
-keysize: 表示密钥的长度；
-keystore：表示生成的密钥存放位置；
-validity：表示密钥的有效时间，单位为天；

在cmd窗口执行如上命令，在执行的过程中需要输入密钥口令等信息，根据提示输入即可。
命令执行后会在当前目录下生成一个名为wang.p12的文件，将这个文件复制到项目的根目录下，然后再application.properties中做如下配置：
#表示密钥文件名
server.ssl.key-store=wang.p12
#表示密钥别名
server.ssl.key-alias=tomcathttps
#就是在cmd命令执行过程中输入的密码
server.ssl.key-store-password=wangjibin

此时如果已HTTP访问会提示：http://localhost:8081/chapter02/hello
               Bad Request
               This combination of host and port requires TLS.
应以HTTPS访问：https://localhost:8081/chapter02/hello

原因：springboot不支持同时在配置文件中启动HTTP和HTTPS。这个时候可以配置请求重定向，将HTTP请求重定向为HTTPS请求。

==============================HTTPS配置 结束==================================


==========================json============================

## 返回json数据
JSON是目前主流的前后端数据传输方式，Spring MVC中使用消息转换器HttpMessageConverter对JSON的转换提供了很好的支持，
在spring boot 中更进一步，对相关配置做了进一步的简化。
## 添加web依赖
这里面就包含了jackson-databind作为JSON处理器，此时不需要添加额外的JSON处理器就能返回一段JSON了。
这是通过spring 中默认提供的MappingJackson2HttpMessageConverter来实现的。当然开发者在这里也可以根据实际需求来自定义JSON转换器。

==========================json============================

## 自定义转换器
常见的JSON处理器除了jackson-databind之外，还有Gson 和 fastjson，这里针对常见用法分别举例。
1.使用Gson
    1>添加Gson依赖
        由于Spring boot中默认提供了Gson的自动转换类GsonHttpMessageConvertersConfigration,因此Gson
        的依赖添加成功后，可以像使用jackson-databind那样直接使用GSON。但是在Gson进行转换时，如果想对日期数据进行格式化，
        那么还需要开发者自定义HttpMessageConverter。
    2>
2.使用fastjson
    fastjson是阿里巴巴的一个开源JSON解析框架，是目前JSON解析速度最快的开源框架。
    该框架也可以集成到spring boot中。不同于GSON，fastjson继承完成之后并不能立马使用，需要开发者提供相应的
    HttpMessageConverter后才能使用，集成fastjson的步骤如下：
    1》首先在web依赖里除去jackson-databind依赖，引入fastjson依赖。
    2》配置fastjson的HttpMessageConverter;
对于FastJsonHttpMessageConverter的配置，除了上面这种方式外，还有另一种方式。
在spring boot项目中，当开发者引入spring-boot-starter-web依赖后，该依赖又依赖了spring-boot-autoconfigure，
在这个自动化配置中，有一个WebMvcAutoConfigration类提供了对spring mvc最基本的配置，如果某一项自动化配置不满足开发需求，开发者可以
针对该项自定义配置，只需要实现WebMvcConfigure接口即可（在spring 5.0之前是通过继承WebMvcConfigurerAdapter类来实现的）；

## 静态资源访问
在spring mvc中，对于静态资源都需要开发者手动配置静态资源过滤。spring boot中对此也提供了自动化配置，可以简化静态资源过滤配置。
在springboot中对于spring MVC的自动化配置都在WebMvcAutoConfigration类中，因此对于默认的静态资源过滤策略可以从这个类中一窥究竟。
在WebMvcAutoConfigration类中有一个静态内部类WebMvcAutoConfigurerAdapter,实现了WebMvcConfigurer接口。
WebMvcConfigurer接口中有一个方法addResourceHandlers,是用来配置静态资源过滤的。该方法在WebMvcAutoConfigurerAdapter类中
得到了实现。

springboot默认会过滤所有的静态资源，而静态资源的位置一共有5个，分别是：
    1. classpath:/META-INF/resources/、
    2. classpath:/resources/、
    3. classpath:/static/、
    4. classpath:/public/、
    5. 以及 "/";
优先级：1》2》3》4》5

## 自定义策略
如果默认的静态资源过滤策略不能满足开发需求，也可以自定义静态资源过滤策略，自定义静态资源过滤策略有以下两种方式：
1. 在配置文件中定义
    #配置静态资源
    spring.mvc.static-path-pattern=/static/**
    spring.web.resources.static-locations=classpath:/static/
2. Java编码定义
       /**
        * 添加静态资源配置信息
        * @param registry
        */
          @Override
          public void addResourceHandlers(ResourceHandlerRegistry registry) {
          registry.addResourceHandler("/static/**")
          .addResourceLocations("classpath:/static/");
        }
   
## 文件上传
Java中的文件上传一共涉及两个组件，一个是CommonsMultipartResolver，另一个是StandardServletMultipartResolver，其中
CommonsMultipartResolver使用commons-fileupload来处理multipart请求，而StandardServletMultipartResolver则是基于
Servlet3.0来处理multipart请求的，因此若使用StandardServletMultipartResolver，则不需要添加额外的jar包。
Tomcat7.0开始就支持Servlet3.0了，而spring boot 2.0.4内嵌的Tomcat为Tomcat8.5.32,因此可以直接使用StandardServletMultipartResolver。
而springboot提供了文件上传自动化配置类MultipartAutoConfigration中，默认也是采用StandardServletMultipartResolver。

## @ControllerAdvice
故名思意，@ControllerAdvice就是@Controller的增强版。@ControllerAdvice主要用来处理全局数据，一般搭配@ExceptionHandler定义
、@ModelAttribute以及@InitBinder使用。

@ControllerAdvice最常见的使用场景就是全局异常处理。

@ControllerAdvice结合@InitBinder还能实现请求参数预处理，即将表单中的数据绑定到实体类上时进行一些额外处理。


## 自定义错误页
根据源码分析可知spring boot中的错误默认是由BaseErrorController类来处理的。该类中的核心方法主要有两个：errorHtml,error;
其中errorHtml返回错误Html页面；error用来返回错误JSON；具体返回的是HTML还是json，则要看请求头的Accept参数。
spring boot默认是在error目录下查找4XX,5XX的文件作为错误视图。如果找不到会使用error作为默认的错误视图名。如果名为error的视图也
找不到，用户就会看到一开始提示的那个错误提示页面。

### 复杂配置
新建类继承DefaultErrorAttributes即可。
定义错误数据：继承DefaultErrorAttributes；
定义错误视图：实现ErrorViewResolver；
完全自定义：继承BasicErrorController


## CORS支持
1. 添加web依赖
2. 创建控制器
3. 配置跨域
    在controller类的方法上添加@CrossOrigin注解；
    @CrossOrigin 中的value表示支持的域，这里表示来自http://localhost:8081域的请求是支持跨域的
    * maxAge表示探测请求的有效期，对于DELETE\PUT请求或者有自定义头信息的请求，在执行过程中会先发送探测请求，探测请求不用每次
    * 都发送，可以配置一个有效期，有效期过了之后才会方送探测请求。这个属性默认是1800秒，即30分钟。
    * allowedHeaders表示允许的请求头，*表示所有的请求头都被允许。
    这种配置方式是一种细粒度的配置，可以控制到每一个方法上面。当然，也可以不在每个方法上添加@CrossOrigin注解，而是采用一种全局配置。
      
4. 测试
    新建一个boot项目，端口是8081，添加web依赖，然后再resources/static目录下添加jquery.js，再创建一个index.html文件，内容是
   Ajax请求信息；
   
## 配置类与XML配置
springboot推荐使用Java来完成相关的配置工作。
如果需要引入xml配置文件，只需要在resources目录下提供配置文件，然后通过@ImportResource加载配置文件即可。


## 注册拦截器
spring mvc中提供了AOP风格的拦截器，拥有更加精细的拦截处理能力。
spring boot中拦截器的注册更加方便。
1. 添加web依赖
2. 创建拦截器 实现 HandlerInterceptor接口。
    拦截器中的方法按照preHandler -> Controller -> postHandler -> afterCompletion的顺序执行。
    注意：只有preHandler方法返回true时后面的方法才会执行。当拦截器链内存在多个拦截器时，postHandler在拦截器链内的所有
    拦截器返回成功时才调用，而afterCompletion只有preHandle返回true才调用，但若拦截器链内的第一个拦截器的preHandle方法
    返回false，则后面的方法都不会执行。
   
3. 配置拦截器
4. 测试

## 启动系统任务
有一些特殊的任务需要在系统启动时执行，例如：配置文件加载、数据库初始化等操作。如果没有使用springboot，这些问题可以在
listener中解决。springboot对此提供了两种解决方案：CommandLineRunner 和 ApllicationRunner。
CommandLineRunner和 ApllicationRunner基本一致，差别主要体现在参数上。

springboot项目在启动时会遍历所有的CommandLineRunner的实现类并调用其中的run方法，如果整个系统中有多个CommandLineRunner的
实现类，那么可以使用Order注解对这些实现类的调用进行排序。
@Order注解用来描述CommandLineRunner的执行顺序，数字越小越先执行。
run方法中是调用的核心逻辑，参数是系统启动时传入的参数，即入口类中main方法的参数（在调用SpringApplication.run方法时被传入springboot项目中）；


## 整合Servlet、Filter、Listener
一般情况下使用spring、springmvc、这些框架之后，基本上就告别了Servlet、Filter以及Listener了，但是
有时候在整合一些第三方框架时，可能还是不得不使用Servlet，比如在整合某报表插件时就需要使用Servlet。
springboot中对于整合这些基本的Web组件也提供了很好的支持。

1. @WebServlet
2. @WebFilter
3. @WebListener

在项目入口类上添加@ServletComponentScan注解，实现对Servlet、Filter以及Listener的扫描。


## 路径映射
一般情况下，使用了页面模板后，用户需要通过控制器才能访问页面。有一些页面需要在控制器中加载数据，然后渲染，才能显示出来。
还有一些页面在控制器中不需要加载数据，只是完成简单的跳转，对于这种页面，可以直接配置路径映射，提高访问速度。


## 配置AOP
场景：如果能够在系统运行过程中动态添加代码，就能很好地解决问题。这种动态添加代码的方式称为面向切面编程（AOP）.
spring 框架对AOP的提供了很好的支持。
AOP常用概念：
1. Joinpoint(连接点)：类里面可以被增强的方法即为连接点。例如：想修改哪个方法的功能，那么该方法就是一个连接点。
2. Pointcut(切入点)：对Joinpoint进行拦截的定义即为切入点。例如：拦截所有以insert开始的方法，这个定义即为切入点。
3. Advice(通知)：拦截到Jointpoint之后所要做的事情就是通知。例如：就是需要做的事情，如打印日志。通知分为前置通知、后置通知、异常通知
   最终通知、环绕通知。
4. Aspect(切面)： Pointcut和Advice的结合。就是具体的切面逻辑，切面代码；
5. Target(目标对象)：要增强的类称为Target；


## 自定义欢迎页
spring boot项目启动后，首先会去静态资源路径下查找index.html作为首页文件，若查找不到，则会去查找动态的index文件作为首页文件。
若想使用静态的index.html页面作为项目首页，那么只需要在resources/static目录下创建index.html文件即可。
若想使用动态页面作为项目首页，则需要在resources/templates目录下创建index.html或者index.ftl，然后再controller中返回逻辑视图名即可。


## 自定义favicon
favicon 是浏览器选项卡左上角的图标，可以放在静态资源路径下或者类路径下，静态资源路径下的favicon.ico优先级高于类路径下的favicon.ico。


## 除去某个自动配置
springboot中提供了大量的自动化配置类，，这些自动配置化配置可以减少相应操作的配置，达到开箱即用的效果。
@EnableAutoConfiguration注解开启自动化配置，相关的自动化配置类就会被使用。如果开发者不想使用某个自动化配置，则是exclude={}排除掉。