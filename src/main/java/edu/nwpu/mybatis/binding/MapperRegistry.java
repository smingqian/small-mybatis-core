package edu.nwpu.mybatis.binding;

import cn.hutool.core.lang.ClassScanner;
import edu.nwpu.mybatis.session.SqlSession;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapperRegistry {
    /**
     * 将已添加的映射器代理加入到 HashMap
     */
    private final Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap<>();

    public <T> T getMapper(Class<T> type, SqlSession sqlSession){
        final MapperProxyFactory<T> mapperProxyFactory =(MapperProxyFactory<T>) knownMappers.get(type);
        if (mapperProxyFactory == null){
            throw new RuntimeException("Type "+type+" is not known to MapperRegistry.");
        }
        try{
            return mapperProxyFactory.newInstance(sqlSession);
        }catch (Exception e){
            throw new RuntimeException("Error getting mapper instance.");
        }

    }

    public <T> void addMapper(Class<T> type) {
        if (type.isInterface()) {
            if (hasMapper(type)) {
                throw new RuntimeException("Type " + type + " is already known to the MapperRegistry.");
            }
            // 注册映射器代理工厂
            knownMappers.put(type, new MapperProxyFactory<>(type));
        }
    }

    private <T> boolean hasMapper(Class<T> type) {
        return knownMappers.containsKey(type);
    }
    public void addMappers(String packageName){
        Set<Class<?>> mappers = ClassScanner.scanPackage(packageName);
        for (Class<?> mapperClass : mappers){
            addMapper(mapperClass);
        }
    }
}
