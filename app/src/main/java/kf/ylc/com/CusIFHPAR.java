package kf.ylc.com;

/**
 * Created By dyzn-ylc on 2019/4/12
 * 有参数，有返回的接口
 */
public abstract class CusIFHPAR<Param,Result> extends CusIF {
    public abstract Result function(Param param);
}
