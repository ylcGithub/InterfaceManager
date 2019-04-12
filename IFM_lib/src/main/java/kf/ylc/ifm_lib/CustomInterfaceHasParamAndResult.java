package kf.ylc.ifm_lib;

/**
 * Created By dyzn-ylc on 2019/4/12
 * 有参数，有返回的接口
 */
public abstract class CustomInterfaceHasParamAndResult<Param,Result> extends CustomInterface {
    public abstract Result function(Param param);
}
