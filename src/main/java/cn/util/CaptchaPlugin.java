package cn.util;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Des
 * Created with IntelliJ IDEA
 * Created by xuyf
 * Date 2018/3/20
 * Time 15:12
 */
public class CaptchaPlugin extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    static final long serialVersionUID = 1L;
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        //生成随机字串
        CaptchaUtil util=CaptchaUtil.Instance();
        String verifyCode = util.getString();
        //存入会话session
        HttpSession session = request.getSession(true);
        session.setAttribute("code", verifyCode.toLowerCase());
        //生成图片
        ImageIO.write(util.getImage(),"jpg",response.getOutputStream());
    }
}
