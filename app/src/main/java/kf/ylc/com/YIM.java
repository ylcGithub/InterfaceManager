package kf.ylc.com;

import java.util.HashMap;

/**
 * Created By dyzn-ylc on 2019/4/12
 * 接口 管理工具，用于 类和类之间通信，
 * EventBus 没有 返回，这个有消息返回
 */
public class YIM {

    private YIM() {
    }

    private static class InterfaceManagerHolder {
        private static final YIM INSTANCE = new YIM();
    }

    public static YIM instance() {
        return InterfaceManagerHolder.INSTANCE;
    }

    /**
     * 用于保存已注册的接口
     */
    private HashMap<String, CusIF> hashMap = new HashMap<>();
    private HashMap<String, CacheBean> cacheMap = new HashMap<>();

    /**
     * 将接口添加到容器中，然后可以通过invokeInterface查找对应接口，并触发其里面的函数
     *
     * @param interfaceKey    接口的唯一标识
     * @param customInterface 接口
     * @return 返回OmnipotentManager实例（作用：方便链式调用）
     */
    public YIM add(String interfaceKey, CusIF customInterface) {
        hashMap.put(interfaceKey, customInterface);
        CacheBean cacheBean = cacheMap.get(interfaceKey);
        if(cacheBean != null){
            invoke(interfaceKey,cacheBean.getObject(),cacheBean.getaClass(),cacheBean.getResultInterface());
        }
        return InterfaceManagerHolder.INSTANCE;
    }

    /**
     * 根据唯一标识去除对应的接口
     *
     * @param interfaceKey 抽象类的唯一标识key
     */
    public void remove(String interfaceKey) {
        hashMap.remove(interfaceKey);
    }

    /**
     * 通过唯一标识获取对应的无参无返回的接口，并触发其里面的函数，实现回调的功能
     *
     * @param interfaceKey 接口的唯一标识
     */
    public void invoke(String interfaceKey) {
        invoke(interfaceKey, null);
    }

    /**
     * 通过唯一标识获取对应的无参有返回的接口，并触发其里面的函数，实现回调的功能
     *
     * @param interfaceKey 接口的唯一标识
     */
    public <Result> Result invoke(String interfaceKey, Class<Result> resultClass) {
        return invoke(interfaceKey, null, resultClass);
    }

    /**
     * 通过唯一标识获取对应的有参无返回的接口，并触发其里面的函数，实现回调的功能
     *
     * @param interfaceKey 接口的唯一标识
     * @param param        泛型传入参数
     */
    public <Param> void invoke(String interfaceKey, Param param) {
        invoke(interfaceKey, param, null);
    }


    /**
     * 通过唯一标识获取对应的有参有返回的接口，并触发其里面的函数，实现回调的功能
     * 添加接口在调用接口之前已经添加
     *
     * @param interfaceKey 接口的唯一标识
     * @param param        泛型传入参数
     * @param resultClass  泛型返回参数
     */
    public <Param, Result> Result invoke(String interfaceKey, Param param, Class<Result> resultClass) {
        CusIF customInterface = hashMap.get(interfaceKey);
        if (customInterface == null) {//只是缓存有参数无返回的
            throw new NullPointerException("please add interface with right key");
        } else {
            //判断接口对象属于那种类型
            if (customInterface instanceof CusIFNPNR) {
                ((CusIFNPNR) customInterface).function();
            } else if (customInterface instanceof CusIFNPHR) {
                if (resultClass != null) {
                    //转换类型
                    return resultClass.cast(((CusIFNPHR) customInterface).function());
                } else {
                    return (Result) ((CusIFNPHR) customInterface).function();
                }
            } else if (customInterface instanceof CusIFHPNR) {
                ((CusIFHPNR) customInterface).function(param);
            } else if (customInterface instanceof CusIFHPAR) {
                if (resultClass != null) {
                    return resultClass.cast(((CusIFHPAR) customInterface).function(param));
                } else {
                    return (Result) ((CusIFHPAR) customInterface).function(param);
                }
            }
        }
        return null;
    }

    /**
     * 通过唯一标识获取对应的有参有返回的接口，并触发其里面的函数，实现回调的功能
     * 调用接口 在添加接口 之前调用
     *
     * @param interfaceKey 接口的唯一标识
     * @param param        泛型传入参数
     * @param resultClass  泛型返回参数
     */
    public <Param, Result> void invoke(String interfaceKey, Param param, Class<Result> resultClass, CusRIF<Result> resultInterface) {
        CusIF customInterface = hashMap.get(interfaceKey);
        if (customInterface == null) {
            cacheMap.put(interfaceKey, new CacheBean(resultInterface,param,resultClass));
        } else {
            //判断接口对象属于那种类型
            if (customInterface instanceof CusIFNPNR) {
                ((CusIFNPNR) customInterface).function();
            } else if (customInterface instanceof CusIFNPHR) {
                if (resultClass != null) {
                    //转换类型
                    resultInterface.function(resultClass.cast(((CusIFNPHR) customInterface).function()));
                }
            } else if (customInterface instanceof CusIFHPNR) {
                ((CusIFHPNR) customInterface).function(param);
            } else if (customInterface instanceof CusIFHPAR && resultInterface != null) {
                if (resultClass != null) {
                    resultInterface.function(resultClass.cast(((CusIFHPAR) customInterface).function(param)));
                }
            }
        }
    }
}
