package io.expanse.rxboot.config;

import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncUtils;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import rx.Observable;

/**
 * Handler that ties the {@link Observable} returned from a controller to a {@link DeferredResult}.<br>
 * This is used in conjunction with {@link RxbootConfiguration} to allow controllers to
 * return straight {@link Observable} types.
 */
public class ObservReturnValueHandler implements HandlerMethodReturnValueHandler {

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        Class parameterType = returnType.getParameterType();

        return Observable.class.isAssignableFrom(parameterType);
    }

    @Override
    public void handleReturnValue(Object returnValue,
                                  MethodParameter returnType,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest) throws Exception {
        if (returnType == null) {
            mavContainer.setRequestHandled(true);
            return;
        }

        final DeferredResult<Object> deferredResult = new DeferredResult<>();
        Observable observable = (Observable) returnValue;
        observable.subscribe(
                result -> deferredResult.setResult(result)
        );

        WebAsyncUtils.getAsyncManager(webRequest)
                .startDeferredResultProcessing(deferredResult, mavContainer);
    }
}
