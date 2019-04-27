package kf.ylc.com;

/**
 * Created By dyzn-ylc on 2019/4/12
 */
public class CacheBean {
    private CusRIF resultInterface;
    private Object object;
    private Class aClass;

    public CacheBean(CusRIF resultInterface, Object object, Class aClass) {
        this.resultInterface = resultInterface;
        this.object = object;
        this.aClass = aClass;
    }

    public CusRIF getResultInterface() {
        return resultInterface;
    }

    public void setResultInterface(CusRIF resultInterface) {
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
