package com.fonada.srana.test.entities;

import lombok.Data;
import org.springframework.util.StringUtils;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

/**
 * @author sandeep.rana
 */
@Entity
@Table(name = "thread2")
@Data
public class Thread2 implements Serializable {

    @Id
    private String vendorId;

    private Integer number;

    private String message;

    public Thread2 setVendorId(String vendorId) {
        this.vendorId = vendorId;
        return this;
    }

    public Thread2 setNumber(Integer number) {
        this.number = number;
        return this;
    }

    public Thread2 setMessage(String message) {
        this.message = message;
        return this;
    }

    public Thread2() {
        if(!StringUtils.hasText(this.getVendorId())) {
            this.setVendorId(UUID.randomUUID().toString().substring(0,24));
        }
    }
}
