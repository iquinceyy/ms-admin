package com.qc.ms.pojo.query;

import com.qc.ms.pojo.entity.User;
import lombok.Data;

import java.io.Serializable;

/**
 * quincey
 * Date 2020/6/24 15:54
 */

@Data
public class UserQuery implements Serializable {

    private String name;
    private Long userId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
