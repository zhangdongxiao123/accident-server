package com.coding.domain;

import java.io.Serializable;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Table(name="type")
public class Type implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "typeid")
    private String typeid;

    @Column(name = "typename")
    private String typename;

    @Column(name = "typehow")
    private String typehow;


}
