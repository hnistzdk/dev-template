package top.zaiolos.dev.template.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test/caffeine/{id}")
    @Cacheable(key = "#id")
    public String caffeine(@PathVariable("id") String id){
        System.out.println("没走缓存");
        return id;
    }

}
