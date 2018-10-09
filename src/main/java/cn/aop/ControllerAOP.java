package cn.aop;

import cn.annotation.UserAccess;
import cn.annotation.VIPAccess;
import cn.entity.User;
import cn.service.UserService;
import cn.util.ResultBean;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;


/**
 * Des
 * Created with IntelliJ IDEA
 * Created by xuyf
 * Date 2018/3/19
 * Time 22:15
 */
public class ControllerAOP {
    @Autowired
    UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(ControllerAOP.class);
    public Object handlerControllerMethod(ProceedingJoinPoint pjp) {
        long startTime = System.currentTimeMillis();
        ResultBean<?> result;
        try {
            MethodSignature methodSignature= (MethodSignature) pjp.getSignature();
            Method method=methodSignature.getMethod();//得到对应的方法对象
            UserAccess userAccess=method.getAnnotation(UserAccess.class);//获取方法上的注解
            VIPAccess vipAccess=method.getAnnotation(VIPAccess.class);
            if(userAccess!=null)//存在这个注解
            {
                HttpServletRequest request =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
                HttpSession session=request.getSession();
                Integer uid=(Integer)session.getAttribute("uid");
                if(uid==null)
                    throw new Throwable("请先登录");
                if(vipAccess!=null){
                    User user=userService.findById(uid);
                    if(user.getShareCount()<5) throw new Throwable("权限不足");
                }
            }
            result = (ResultBean<?>) pjp.proceed();
            logger.info(pjp.getSignature() + "use time:" + (System.currentTimeMillis() - startTime));
        } catch (Throwable e) {
            result = handlerException(pjp, e);
        }
        return result;
    }
    private ResultBean<?> handlerException(ProceedingJoinPoint pjp, Throwable e) {
        ResultBean<?> result = new ResultBean();
        logger.error(pjp.getSignature() + " error ", e);
        //TODO 捕获已知异常进行特殊处理
        result.setMsg(e.getMessage());
        result.setCode(ResultBean.FAIL);
        return result;
    }
}
