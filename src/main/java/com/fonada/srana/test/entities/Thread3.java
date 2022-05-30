package com.fonada.srana.test.entities;

import lombok.Data;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * @author sandeep.rana
 */
@Entity
@Table(name = "thread3")
@Data
public class Thread3 implements Serializable {

    @Id
    private String vendorId;

    private Integer number;

    private String message;


  public Thread3 setVendorId(String vendorId) {
        this.vendorId = vendorId;
        return this;
    }

    public Thread3 setNumber(Integer number) {
        this.number = number;
        return this;
    }

    public Thread3 setMessage(String message) {
        this.message = message;
        return this;
    }

    public Thread3() {
        if(!StringUtils.hasText(this.getVendorId())) {
            this.setVendorId(UUID.randomUUID().toString().substring(0,24));
        }
    }
}
