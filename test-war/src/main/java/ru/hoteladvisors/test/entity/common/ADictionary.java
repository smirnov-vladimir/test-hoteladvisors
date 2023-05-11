package ru.hoteladvisors.test.entity.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@MappedSuperclass
public abstract class ADictionary extends AIdentifier {

    @Column(name = "code")
    private String code;

    @Column(name = "value_ru")
    private String valueRu;
}
