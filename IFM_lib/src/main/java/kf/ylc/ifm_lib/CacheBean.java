package kf.ylc.ifm_lib;

/**
 * Created By dyzn-ylc on 2019/4/12
 */
public class CacheBean {
    private CustomResultInterface resultInterface;
    private Object object;
    private Class aClass;

    public CacheBean(CustomResultInterface resultInterface, Object object, Class aClass) {
        this.resultInterface = resultInterface;
        this.object = object;
        this.aClass = aClass;
    }

    public CustomResultInterface getResultInterface() {
        return resultInterface;
    }

    public void setResultInterface(CustomResultInterface resultInterface) {
        this.resultInterface = resultInterface;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }

    public CacheBean() {
    }
}
