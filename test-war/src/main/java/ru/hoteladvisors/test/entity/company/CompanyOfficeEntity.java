package ru.hoteladvisors.test.entity.company;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.hoteladvisors.test.entity.address.AddressEntity;
import ru.hoteladvisors.test.entity.common.AIdentifier;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true, exclude = "company")
@ToString(callSuper = true)
@Entity
@Table(name = "company_office")
public class CompanyOfficeEntity extends AIdentifier {

    @Column(name = "office_name")
    private String officeName;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyEntity company;
}
