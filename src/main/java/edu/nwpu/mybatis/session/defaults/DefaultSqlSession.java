package edu.nwpu.mybatis.session.defaults;

import edu.nwpu.mybatis.binding.MapperRegistry;
import edu.nwpu.mybatis.session.SqlSession;

public class DefaultSqlSession implements SqlSession {
    private MapperRegistry mapperRegistry;

    public DefaultSqlSession(MapperRegistry mapperRegistry){
        this.mapperRegistry= mapperRegistry;
    }

    @Override
    public <T> T selectOne(String statement) {
        return (T) ("你被代理了"+statement);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        return  (T) ("你被代理了"+statement+" 入参："+parameter);
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return (T) mapperRegistry.getMapper(type,this);
    }
}
