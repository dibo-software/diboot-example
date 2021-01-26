package com.example.service.impl;

import com.diboot.core.binding.data.CheckpointType;
import com.diboot.core.binding.data.DataAccessInterface;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 数据权限可访问范围ids
 * @author www.dibo.ltd
 * @version v1.0
 * @date 2020/09/29
 */
@Service
public class DataAccessPermissionImpl implements DataAccessInterface {

    @Override
    public List<Long> getAccessibleIds(CheckpointType type) {
        if(type.equals(CheckpointType.ORG)){
            // 当前用户可访问组织id
            return Arrays.asList(100001L, 100002L);
        }
        return Arrays.asList(-1L);
    }

}
