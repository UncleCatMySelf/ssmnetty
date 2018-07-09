package com.myself.netty.db;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * 访问数据库类
 * @Author:AwakeningCode
 * @Date: Created in 10:45 2018\3\12 0012
 */
public class DbAccess {

    public SqlSession getSqlSession() throws IOException{
        //1、通过配置文件获取数据库连接的相关信息
        Reader reader = Resources.getResourceAsReader("config/Configuration.xml");
        //2、通过配置信息构建SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        //3、通过SqlSessionFactory打开数据库会话
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession;
    }

}
