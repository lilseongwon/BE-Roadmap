package com.example.jpabook.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
public class BaseEntity {

    @CreatedDate
    private Date createdDate; //todo 등록일

    @LastModifiedDate
    private Date lastModifiedDate; //todo 수정일
}
