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
@Table(name ="thread1")
@Data
public class Thread1 implements Serializable {

    @Id
    private String vendorId;

    private String tps;

    public Thread1 setVendorId(String vendorId) {
        this.vendorId = vendorId;
        return this;
    }

    public Thread1 setTps(String tps) {
        this.tps = tps;
        return this;
    }

    public Thread1() {
        if(!StringUtils.hasText(this.getVendorId())) {
            this.setVendorId(UUID.randomUUID().toString().substring(0,24));
        }
    }
}
