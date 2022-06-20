package com.framework.cloud.mybatis.hander;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.function.Supplier;

/**
 * 自动填充数据
 *
 * @author wusiwei
 */
public class MybatisMetaObjectHandler implements MetaObjectHandler {

    private static final String CREATE_ID = "createId";
    private static final String UPDATE_ID = "updateId";
    private static final String CREATE_TIME = "createTime";
    private static final String UPDATE_TIME = "updateTime";

    @Override
    public void insertFill(MetaObject metaObject) {
        insertFillValue(metaObject, CREATE_ID, () -> getUserIdValue(metaObject.getSetterType(CREATE_ID)));
        insertFillValue(metaObject, CREATE_TIME, () -> getDateValue(metaObject.getSetterType(CREATE_TIME)));
        insertFillValue(metaObject, UPDATE_ID, () -> getUserIdValue(metaObject.getSetterType(CREATE_ID)));
        insertFillValue(metaObject, UPDATE_TIME, () -> getDateValue(metaObject.getSetterType(UPDATE_TIME)));
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        updateFillValue(metaObject, UPDATE_ID, () -> getUserIdValue(metaObject.getSetterType(CREATE_ID)));
        updateFillValue(metaObject, UPDATE_TIME, () -> getDateValue(metaObject.getSetterType(UPDATE_TIME)));
    }

    private void insertFillValue(MetaObject metaObject, String fieldName, Supplier<Object> valueSupplier) {
        if (!metaObject.hasGetter(fieldName)) {
            return;
        }
        Object sidObj = metaObject.getValue(fieldName);
        if (sidObj == null && metaObject.hasSetter(fieldName) && valueSupplier != null) {
            setFieldValByName(fieldName, valueSupplier.get(), metaObject);
        }
    }

    private void updateFillValue(MetaObject metaObject, String fieldName, Supplier<Object> valueSupplier) {
        if (!metaObject.hasGetter(fieldName)) {
            return;
        }
        if (metaObject.hasSetter(fieldName) && valueSupplier != null) {
            setFieldValByName(fieldName, valueSupplier.get(), metaObject);
        }
    }

    private Object getDateValue(Class<?> setterType) {
        if (Date.class.equals(setterType)) {
            return new Date();
        } else if (LocalDateTime.class.equals(setterType)) {
            return LocalDateTime.now();
        } else if (Long.class.equals(setterType)) {
            return System.currentTimeMillis();
        }
        return null;
    }

    private Object getUserIdValue(Class<?> setterType) {
        if (Long.class.equals(setterType)) {
            return 1L;
        }
        return null;
    }

}
