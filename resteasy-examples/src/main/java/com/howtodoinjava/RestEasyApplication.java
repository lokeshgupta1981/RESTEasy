package com.howtodoinjava;

import com.howtodoinjava.web.UserService;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/rws")
public class RestEasyApplication extends Application
{
    private Set<Class> classes = new HashSet<Class>();

    public RestEasyApplication() {
        classes.add(UserService.class);
    }

    @Override
    public Set<Class<?>> getClasses() {
        return super.getClasses();
    }
}
