package restproxy.controller;

import com.google.common.cache.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
public class RestProxyController {
    private static Cache<String, String> cache = null;

    @PostConstruct
    public void init() {
        buildCache();
    }


    // Store a Value
    @RequestMapping(value="/store/{requestID}", method= RequestMethod.POST)
    @ResponseBody
    public String store(@PathVariable String requestID,@RequestBody String requestString) throws IOException {
        System.out.println(URLDecoder.decode(requestString,"UTF-8"));
        cache.put(requestID,URLDecoder.decode(requestString,"UTF-8"));
        return "Saved";
    }

    // Restore a value
    @RequestMapping(value="/restore/{requestID}",method= RequestMethod.GET)
    @ResponseBody
    public String restore(@PathVariable String requestID) throws IOException {

        return cache.getIfPresent(requestID);
    }

    private void buildCache() {
        cache = CacheBuilder.newBuilder()
                .maximumSize(100)
                .expireAfterAccess(2, TimeUnit.HOURS)
                .recordStats()
                .build();
    }

    private String generateUUID(String key) {
        if ((key=="") || (key==null)){
            // Use timestamp as key
        }
        return UUID.fromString(key).toString();
    }

}
