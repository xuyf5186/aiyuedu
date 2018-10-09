package cn.util;

import lombok.Data;

import java.io.Serializable;

/**
 * Des
 * Created with IntelliJ IDEA
 * Created by xuyf
 * Date 2018/3/19
 * Time 16:37
 */
@Data
public class ResultBean<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int SUCCESS = 1;

    public static final int FAIL = 0;

    private String msg = "success";

    private int code = SUCCESS;

    private T data;

    public ResultBean() {
        super();
    }

    public ResultBean(T data) {
        super();
        this.data = data;
    }

    public ResultBean(Throwable e) {
        super();
        this.msg = e.toString();
        this.code = FAIL;
    }
}
