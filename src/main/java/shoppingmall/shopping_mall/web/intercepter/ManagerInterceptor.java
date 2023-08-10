package shoppingmall.shopping_mall.web.intercepter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import shoppingmall.shopping_mall.member.Grade;
import shoppingmall.shopping_mall.web.session.SessionConst;

import java.io.PrintWriter;

@Slf4j
public class ManagerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        log.info("인증 체크 인터셉터 실행 {}", requestURI);
        HttpSession session = request.getSession();
        /*
        if(session.getAttribute(SessionConst.LOGIN_GRADE)== Grade.CUSTOMER){
            log.info("로그인 grade = {}", session.getAttribute(SessionConst.LOGIN_GRADE));
        }*/
        // 매니저가 아닌 경우
        if(session == null || session.getAttribute(SessionConst.LOGIN_MEMBER)==null || session.getAttribute(SessionConst.LOGIN_GRADE)==Grade.CUSTOMER){
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();

            out.println("<script language='javascript'>");
            out.println("alert('관리자만 볼 수 있습니다.')");
            out.println("</script>");

            out.flush();
            response.sendRedirect("/members/login?redirectURL="+requestURI);

            response.sendRedirect("/");
            return false;
        }

        return true;
    }
}
