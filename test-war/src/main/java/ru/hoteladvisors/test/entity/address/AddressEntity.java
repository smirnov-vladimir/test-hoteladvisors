package ru.hoteladvisors.test.entity.address;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.hoteladvisors.test.entity.common.AIdentifier;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "address")
public class AddressEntity extends AIdentifier {

    @Column(name = "post_index")
    private String postIndex;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "house")
    private String house;

    @Column(name = "flat")
    private String flat;
}
