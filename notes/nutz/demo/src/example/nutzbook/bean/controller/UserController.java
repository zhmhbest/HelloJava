package example.nutzbook.bean.controller;

import javax.servlet.http.HttpSession;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import example.nutzbook.model.User;

@IocBean
@At("/user")
@Ok("json")
@Fail("http:500")
public class UserController {
    @Inject
    protected Dao dao;

    @At
    public int count() {
        return dao.count(User.class);
    }

    @At
    public Object login(
            @Param("username") String name,
            @Param("password") String password,
            HttpSession session
    ) {
        User user = dao.fetch(User.class, Cnd.where("name", "=", name).and("password", "=", password));
        if (user == null) {
            session.setAttribute("id", null);
            return false;
        } else {
            session.setAttribute("id", user.getId());
            session.setAttribute("name", user.getName());
            return true;
        }
    }

    @At
    @Ok(">>:/") //重定向到 /
    public void logout(HttpSession session) {
        session.invalidate();
    }
}