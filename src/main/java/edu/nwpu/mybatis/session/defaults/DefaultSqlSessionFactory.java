package edu.nwpu.mybatis.session.defaults;

import edu.nwpu.mybatis.binding.MapperRegistry;
import edu.nwpu.mybatis.session.SqlSession;
import edu.nwpu.mybatis.session.SqlSessionFactory;

public class DefaultSqlSessionFactory implements SqlSessionFactory {
    private final MapperRegistry mapperRegistry;

    public DefaultSqlSessionFactory(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(mapperRegistry);
    }
}
