package com.example.iam.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/***
 * mybatis-plus 自动填充
 * @author www.dibo.ltd
 * @version v2.0
 * @date 2020/09/27
 */
@Component
public class CustomMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        //this.strictInsertFill(metaObject, Cons.FieldName.createBy.name(), Long.class, currentUser.getId());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //this.strictInsertFill(metaObject, "description", String.class, currentUser.getDisplayName());
    }

}