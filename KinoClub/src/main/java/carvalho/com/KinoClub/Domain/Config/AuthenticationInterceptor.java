package carvalho.com.KinoClub.Domain.Config;

import java.util.Arrays;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import carvalho.com.KinoClub.Domain.Models.Users.User;
import carvalho.com.KinoClub.Domain.Services.AuthenticationServices;

@Component
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			Optional<Cookie> Auth = Arrays.stream(cookies).filter(x -> x.getName().equalsIgnoreCase(KinoConstants.AuthenticationCookieName))
					.findFirst();
			if (Auth.isPresent()) {
				String JWT = Auth.get().getValue();
				AuthenticationServices s = new AuthenticationServices();
				request.setAttribute(KinoConstants.AuthenticatedUser, s.GetUserFromToken(JWT));
			}

		}
		return true;
	}
//
//	@Override
//	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//			ModelAndView modelAndView) {
//		System.out.println("Entered postHandle interceptor.");
//	}
//
//	@Override
//	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
//			Exception exception) {
//		System.out.println("Entered afterCompletion iterceptor");
//	}
}