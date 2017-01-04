package io.expanse.rxboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.Collections;
import java.util.Map;

/**
 * Example controller that contain endpoints that return straight {@link Observable} types.
 */
@RestController
public class ObsrvController {

    private static final Logger LOG = LoggerFactory.getLogger(ObsrvController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Observable<Map<String, String>> getMessage() {
        return Observable.fromCallable(() -> {
            LOG.info("Creating message");

            return Collections.singletonMap(
                    "message",
                    "Hello World!"
            );
        }).subscribeOn(Schedulers.computation());
    }
}
