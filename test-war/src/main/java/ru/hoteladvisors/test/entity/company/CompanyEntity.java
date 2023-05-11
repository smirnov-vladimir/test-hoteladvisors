package ru.hoteladvisors.test.entity.company;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.hoteladvisors.test.entity.address.AddressEntity;
import ru.hoteladvisors.test.entity.common.AIdentifier;
import ru.hoteladvisors.test.entity.dictionary.DicLegalFormEntity;

import javax.persistence.*;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true, exclude = "offices")
@ToString(callSuper = true)
@Entity
@Table(name = "company")
public class CompanyEntity extends AIdentifier {

    @Column(name = "company_name")
    private String companyName;

    @ManyToOne
    @JoinColumn(name = "legal_form_id")
    private DicLegalFormEntity legalForm;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private Set<CompanyOfficeEntity> offices;
}
