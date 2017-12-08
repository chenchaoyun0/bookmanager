package com.sttx.bookmanager.util.callable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.mapping.StatementType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sttx.bookmanager.util.callable.handler.CallableResultHandler;
import com.sttx.bookmanager.util.callable.handler.SimpleResultHandler;
import com.sttx.bookmanager.util.callable.support.PropertiesHelper;

/**
 * 存储过程调用规则 Created by limiemie on 2014/3/26.
 */
@Intercepts({ @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
        RowBounds.class, ResultHandler.class }) })
public class CallableConvertInterceptor implements Interceptor {
    private static Logger logger = LoggerFactory.getLogger(CallableConvertInterceptor.class);
    static int MAPPED_STATEMENT_INDEX = 0;
    static int PARAMETER_INDEX = 1;
    static int ROWBOUNDS_INDEX = 2;
    static int RESULT_HANDLER_INDEX = 3;

    CallableResultHandler callableResultHandler = new SimpleResultHandler();

    public Object intercept(Invocation invocation) throws Throwable {
        final Object[] queryArgs = invocation.getArgs();
        final MappedStatement ms = (MappedStatement) queryArgs[MAPPED_STATEMENT_INDEX];
        final Object parameter = queryArgs[PARAMETER_INDEX];

        if (!ms.getStatementType().equals(StatementType.CALLABLE)) {
            return invocation.proceed();
        }

        BoundSql boundSql = ms.getBoundSql(parameter);
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();

        Map<String, Object> parameterMap = new HashMap<String, Object>();
        if (parameter instanceof Map) {
            parameterMap = (Map) parameter;
        } else {
            for (ParameterMapping parameterMapping : parameterMappings) {
                if (parameterMapping.getMode().equals(ParameterMode.IN)
                        || parameterMapping.getMode().equals(ParameterMode.INOUT)) {
                    parameterMap.put(parameterMapping.getProperty(), parameter);
                }
            }
        }

        for (ParameterMapping parameterMapping : parameterMappings) {
            if (parameterMapping.getMode().equals(ParameterMode.OUT)) {
                parameterMap.put(parameterMapping.getProperty(), null);
            }
        }

        queryArgs[PARAMETER_INDEX] = parameterMap;

        invocation.proceed();

        return callableResultHandler.proceed(parameterMappings, parameterMap);
    }

    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    public void setProperties(Properties properties) {
        PropertiesHelper propertiesHelper = new PropertiesHelper(properties);
        String dialectClass = propertiesHelper.getProperty("resultHandler",
                "com.sttx.bookmanager.util.callable.handler.SimpleResultHandler");
        try {
            setResultHandler((CallableResultHandler) Class.forName(dialectClass).newInstance());
        } catch (Exception e) {
            throw new RuntimeException("cannot create resultHandler instance by resultHandler:" + dialectClass, e);
        }
    }

    public void setResultHandler(CallableResultHandler resultHandler) {
        logger.debug("resultHandler: {} ", resultHandler.getClass().getName());
        this.callableResultHandler = resultHandler;
    }
}
