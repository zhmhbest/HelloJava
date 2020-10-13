/*
 * 用于在Webapp生命周期的始终做一些操作
 */
package example.nutzbook;

import java.util.Date;

import org.nutz.dao.Dao;
import org.nutz.dao.util.Daos;
import org.nutz.ioc.Ioc;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;

import example.nutzbook.model.User;

public class MainSetup implements Setup {
    @Override
    public void init(NutConfig nc) {
        // 创建所有数据表
        final String scanPackageName = "example.nutzbook.model";
        Ioc ioc = nc.getIoc();
        Dao dao = ioc.get(Dao.class);
        Daos.createTablesInPackage(dao, scanPackageName, false);

        // 创建一个默认用户
        if (dao.count(User.class) == 0) {
            User user = new User();
            user.setName("admin");
            user.setPassword("admin");
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            dao.insert(user);
        }
    }
    @Override
    public void destroy(NutConfig nc) {
        // webapp销毁之前执行的逻辑
        // 这个时候依然可以从nc取出ioc, 然后取出需要的ioc 对象进行操作
    }
}