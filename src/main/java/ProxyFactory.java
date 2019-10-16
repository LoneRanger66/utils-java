import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory {
    private Object target;// 维护一个目标对象

    public ProxyFactory(Object target) {
        this.target = target;
    }

    public Object getProxyInstance() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("开启事务");
                Object returnValue = method.invoke(target, args);
                System.out.println("提交事务");
                return returnValue;
            }
        });
    }

    public static void main(String[] args) {
        MyInterface obj = new MyInterfaceImpl();
        MyInterface proxy = (MyInterface) new ProxyFactory(obj).getProxyInstance();
        proxy.doSomething();
    }
}

interface MyInterface {
    void doSomething();
}

class MyInterfaceImpl implements MyInterface {

    @Override
    public void doSomething() {
        System.out.println("保存数据");
    }
}