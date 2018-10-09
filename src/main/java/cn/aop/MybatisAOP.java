package cn.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Date;

/** TODO 目前切点切不进来，以后研究
 * Des
 * Created with IntelliJ IDEA
 * Created by xuyf
 * Date 2018/3/20
 * Time 12:17
 */
public class MybatisAOP  implements MethodInterceptor{
    private static final Logger logger = LoggerFactory.getLogger(MybatisAOP.class);
    public Object insertCutMethod(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        Method method= null;
        for (Object arg : args) {
            logger.debug("[insert]"+arg);
            try {
                Date temp=new Date();
                method = arg.getClass().getDeclaredMethod("setCreatedat");
                method.invoke(arg,temp);
                method = arg.getClass().getDeclaredMethod("setUpdatedat");
                method.invoke(arg,temp);
            } catch (Exception e) {
                //参数没有此方法不做处理
                //e.printStackTrace();
            }
        }
        Object o = pjp.proceed();
        return o;
    }
    public Object updateCutMethod(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        Method method= null;
        for (Object arg : args) {
            logger.debug("[update]"+arg);
            try {
                method = arg.getClass().getDeclaredMethod("setUpdatedat");
                method.invoke(arg,new Date());
            } catch (Exception e) {
                //参数没有此方法不做处理
                //e.printStackTrace();
            }
        }
        Object o = pjp.proceed();
        return o;
    }
    public Object deleteCutMethod(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        Method method= null;
        for (Object arg : args) {
            logger.debug("[delete]"+arg);
            try {
                method = arg.getClass().getDeclaredMethod("setDeletedat");
                method.invoke(arg,new Date());
            } catch (Exception e) {
                //参数没有此方法不做处理
                //e.printStackTrace();
            }
        }
        Object o = pjp.proceed();
        return o;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        logger.info("yea");
        return null;
    }
}
