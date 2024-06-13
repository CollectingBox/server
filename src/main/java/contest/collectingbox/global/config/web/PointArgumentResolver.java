package contest.collectingbox.global.config.web;

import contest.collectingbox.global.utils.GeometryUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.locationtech.jts.geom.Point;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class PointArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(Point.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

        if (request.getParameter("latitude") == null) {
            throw new MissingServletRequestParameterException("latitude", "Point");
        }
        if (request.getParameter("longitude") == null) {
            throw new MissingServletRequestParameterException("longitude", "Point");
        }

        double latitude;
        double longitude;

        try {
            latitude = Double.parseDouble(request.getParameter("latitude"));
            longitude = Double.parseDouble(request.getParameter("longitude"));
            return GeometryUtil.toPoint(longitude, latitude);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid parameter value for latitude or longitude");
        }
    }
}
