var ioc = {
    dataSource : {
        type : "com.alibaba.druid.pool.DruidDataSource",
        events : {
            create : "init",
            depose : 'close'
        },
        fields : {
            url : "jdbc:mysql://localhost:3306/nutzbook",
            username : "nutz",
            password : "nutz",
            testWhileIdle : true,           // 非常重要,预防mysql的8小时timeout问题
            //validationQuery : "select 1", // Oracle的话需要改成 select 1 from dual
            maxActive : 100
        }
    },
    dao : {
        type : "org.nutz.dao.impl.NutDao",
        args : [{refer:"dataSource"}]
    }
};