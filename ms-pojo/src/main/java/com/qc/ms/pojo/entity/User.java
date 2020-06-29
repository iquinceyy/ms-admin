package com.qc.ms.pojo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * quincey
 * Date 2020/6/24 15:54
 */

@Data
public class User implements Serializable {
    private Long userId;
    private String name;
}
