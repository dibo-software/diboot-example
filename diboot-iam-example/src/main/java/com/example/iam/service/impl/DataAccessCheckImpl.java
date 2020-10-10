package com.example.iam.service.impl;

import com.diboot.core.binding.data.CheckpointType;
import com.diboot.core.binding.data.DataAccessInterface;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * <Description>
 *
 * @author mazc
 * @version v1.0
 * @date 2020/09/29
 */
@Service
public class DataAccessCheckImpl implements DataAccessInterface {

    @Override
    public List<Long> getAccessibleIds(CheckpointType type) {
        // 当前为部门及下属部门
        if(type.equals(CheckpointType.ORG)){
            return Arrays.asList(1L, 2L);
        }
        return null;
    }

}
