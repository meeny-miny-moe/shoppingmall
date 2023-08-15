package shoppingmall.shopping_mall;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import shoppingmall.shopping_mall.web.intercepter.LoginCheckInterceptor;
import shoppingmall.shopping_mall.web.intercepter.LoginInterceptor;
import shoppingmall.shopping_mall.web.intercepter.ManagerInterceptor;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**", "/*.ico", "/error/**");
        // 필수로 로그인을 해야 들어갈 수 있는 부분들
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(2)
                .addPathPatterns("/myshop/index","/members/logout", "/board/product/write", "/item/add");
        // 관리자만 들어갈 수 있는 부분
        registry.addInterceptor(new ManagerInterceptor())
                .order(3)
                .addPathPatterns("/item/add");
    }
}
