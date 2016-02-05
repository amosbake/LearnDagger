##依赖注入(DI)
[参考资料-dagger源码解析](http://codekk.com/open-source-project-analysis/detail/Android/%E6%89%94%E7%89%A9%E7%BA%BF/Dagger%20%E6%BA%90%E7%A0%81%E8%A7%A3%E6%9E%90)


###什么是依赖注入
>*依赖(Dependency)*:
>  如果在ClassA中,有一个属性是ClassB的实例,则称ClassA对ClassB有一个依赖.如下:类Bird中用到一个Head对象,则可以说类Bird对类Head有一个依赖


```java
 public class Bird {
    private Head head;

    public Bird() {
        head = new Head();
    }
}
```

>*宿主(Host)* 
>如果ClassB是ClassA的依赖,则称ClassA是ClassB的宿主,在上面的例子中,BIrd类就是Head类的宿主.


由以上的例子可以看出,如果直接在宿主的构造方法里初始化依赖,则会出现以下几个问题:
1.如果想修改依赖的生成方式(添加参数,单例化等),就需要修改宿主的代码.如果在大型项目中,可能会带来连锁反应.
2.如果要测试依赖的不同实例对宿主的影响很困难,因为初始化方法已经写死.
3.如果依赖的生成非常耗时和昂贵,在测试时我们想用初始化好的对象替代它非常困难.

>*依赖注入*
>依赖注入就是把依赖对象当做构造函数的一个参数传入.这种非自己主动初始化依赖,而通过外部来传入依赖的方式,就是依赖注入.

```java
 public class Bird {
    private Head head;

    public Bird(Head head) {
        this.head = head;
    }
}
```

依赖注入的好处:
1.解耦
2.方便单元测试,尤其是Mock测试.

>*依赖注入框架*
>虽然IDE可以帮助生成一些简单的依赖注入代码,但要实现复杂多变的依赖注入,还是要借助依赖注入框架来实现.
>Java中,使用注解是最常见的方式,Java上较流行的框架有[Google Guice](https://github.com/google/guice) ,[Sping](http://projects.spring.io/spring-framework/)等.而在Android上流行的有[RoboGuice](https://github.com/roboguice/roboguice),[Dagger](http://square.github.io/dagger/)等.

###Dagger2

[参考资料1](http://stackoverflow.com/questions/27036933/how-to-set-up-dagger-dependency-injection-from-scratch-in-android-project/29943394#29943394)
[参考资料2](http://blog.csdn.net/new_abc/article/details/48199409)

下面就以一个Android项目为例，来展示一下`Dagger2`的作用
>#####应用场景:
>  应用包括一个登陆界面,用户输入用户名,密码. 弹出登陆提示
> #####基本架构
> 这个应用采用了RxJava+Dagger的技术，并实现了MVP架构 
>
>#####基本概念
>1. `Android`中的一些重要组件如 `Application`,`Activity`。他们 都是由Android系统自己负责创建和维护的，而上面说到的依赖注入的概念，本质上还是`new Host(DependA a,DependB b)`. 所以对于这类对象，我们就必须在其生命周期内(onCreate)进行手动注入，Dagger2也为我们提供了此类方法。
>2.    Dagger2的依赖注入的编写包括三个部分，一是`Module` 即提供依赖的类 ，里面有提供依赖，创建依赖的方法，它告诉Dagger在哪里找到依赖。二是`Component`,它是依赖和宿主之间的桥梁，需要列出Module以组成，也提供主要的注入类方法，Dagger会依照此接口生成注入类。 三是`@Inject` 它可以提供成员变量注入，方法注入和构造器方法注入。

应用步骤: 
####为项目导入Dagger2
1) 在项目构建配置文件`build.gradle`中添加如下代码
```gradle
    dependencies {
        classpath 'com.android.tools.build:gradle:1.5.0'
        classpath 'com.neenbedankt.gradle.plugins:android-apt:1.4'
    }
    
 allprojects {
    repositories {
        jcenter()
    }
 }
```

2) 在模块配置文件`build.gradle`中添加如下代码
```gradle
apply plugin: 'com.android.application'
apply plugin: 'com.neenbedankt.android-apt'


android {
  ......
}

dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    testCompile 'junit:junit:4.12'
    compile 'com.android.support:appcompat-v7:23.1.1'
    apt 'com.google.dagger:dagger-compiler:2.0'//用以自动生成依赖注入代码
    compile 'com.google.dagger:dagger:2.0'
    provided 'org.glassfish:javax.annotation:10.0-b28' //解决缺少 @Generated 注解的错误提示
}
```
####项目控制层
由于使用`SharedPreferences`需要`Context`，且用户数据帮助类`UserDataManager`可能在很多服务中都要用到，所以设计成一个单例对象，Dagger2中可以很方便地用注解达到这一要求。

1)用户数据帮助接口
```java
 public interface UserDataManager {
    Observable<Boolean> login(String userName, String password);
    Observable<Boolean> register(String userName, String password);
}
```

2)用户数据帮助类(使用`SharePreference`
```java
 public class UserManager_Sp implements UserDataManager {
    private Application mContext;
    private SharedPreferences mPreferences;

    public UserManager_Sp(Application application) {
        mContext = application;
        mPreferences = mContext.getSharedPreferences("user", Context.MODE_PRIVATE);
    }

    @Override
    public Observable<Boolean> login(String userName, String password) {
        if (mPreferences.contains(userName)) {
            return Observable.just(password.equals(mPreferences.getString(userName, "")));
        } else {
            return Observable.just(false);
        }
    }

    @Override
    public Observable<Boolean> register(@NonNull String userName, @NonNull String password) {
        final SharedPreferences.Editor editor = mPreferences.edit();
        return Observable.just(editor.putString(userName, password).commit());
    }
}
```
####`Application`依赖注入
4) 接下来是应用启动时要做的事: a)如果是第一次登陆，注册一个默认用户。2)加载可能需要的第三方服务。
在这一个部分，就要正式引入Dagger2的依赖注入了。



所以我们首先编写`Module`

4.1) `AppModule` 它用来提供`Application`.
`@Module`--标明这个类是一个`Module`
`@Provides`--标明这是一个提供依赖的方法，如果忘记标明，会在之后的编译过程中抛出如下异常
```java
com.lexing.learndagger.App cannot be provided without an @Inject constructor or from an @Provides-annotated method. This type supports members injection but cannot be implicitly provided.
```
`@Singleton`--标明这个依赖是单例的
```
Module
public class AppModule {
    private final App application;

    public AppModule(App application) {
        this.application = application;
    }
    @Provides
    @Singleton
    public App provideApplication() {
        return this.application;
    }
}
```

4.2)`DomainModule` 它用来提供 `UserManager`和一个空的第三方管理类(初始化第三方)
`@Module(includes = AppModule.class)` 表示此Module依赖另一个Module，如果不声明，会在编译过程中抛出4.1中的错误。即使在`Component`中列出相关`Module`，它们之间也不会自动依赖。

```java
@Module(includes = AppModule.class)
public class DomainModule {

    @Provides
    @Singleton
    public Analyticsmanager provideAnalyticsmanager(App app) {
        return new Analyticsmanager(app);
    }

    @Provides
    @Singleton
    public UserDataManager provideUserDatamanager(App app) {
        return new UserManager_Sp(app);
    }
}
```
如果Module中提供有相互依赖关系的两个类，也可以写成这样.
```java
    @Provides
    public OtherClass provideOtherClass() {
        return new OtherClass();
    }
    @Provides
    public AnotherClass provideAnotherClass(OtherClass clazz) {
        return new AnotherClass(clazz);
    }
```

4.3)`AppComponent` 由于在前面，我们已经将`AppModule`注入了`DomainModule`，在这里我们只列出`DomainModule`即可，如果还有其他依赖，则必须添加。
`@Singleton` 如果其中的Module是单例的话，这必须添加这一注释
```java
 @Singleton
 @Component(
        modules = {
                DomainModule.class
        }
)
public interface AppComponent {
    void inject(App app);

    UserDataManager getUserDataManager();

    GitManager getGitManager();
}
```

4.4) 'App'的依赖注入.
a) 先编译一次 Dagger会在编译时检查整个依赖图并生成相关依赖注入类,
b)在`onCreate`方法中手动注入，在这里，需要手动依赖`AppModule`因为它是`DomainModule`的依赖
```java
public class App extends Application {
    private static final String TAG = "App";
    @Inject
    Analyticsmanager mAnalyticsmanager;
    @Inject
    UserDataManager mUserDataManager;

    @Override
    public void onCreate() {
        super.onCreate();
        setUpAppCompenont();//手动注入依赖
        mAnalyticsmanager.registerAppEnter();//第三方初始化
        preRegisterUser();//预先注册用户
    }

    private void preRegisterUser() {
        final SharedPreferences loginSp = getSharedPreferences("login", MODE_PRIVATE);
        if (loginSp.getBoolean("First", true)){
            mUserDataManager.register("default","123456")
                    .subscribe(new Action1<Boolean>() {
                        @Override
                        public void call(Boolean aBoolean) {
                            if (aBoolean){
                                Log.i(TAG, "first enter register default");
                                loginSp.edit().putBoolean("First",false);
                            }
                        }
                    });
        }
    }

    private void setUpAppCompenont() {
        DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .domainModule(new DomainModule())
                .build().inject(this);
    }
}
```

到这，我们对`Application`的依赖注入就结束了。
