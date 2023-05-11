package ru.hoteladvisors.test.view.company;

import lombok.Getter;
import lombok.Setter;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import ru.hoteladvisors.test.ejb.company.CompanyBean;
import ru.hoteladvisors.test.entity.company.CompanyEntity;
import ru.hoteladvisors.test.entity.company.CompanyOfficeEntity;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static ru.hoteladvisors.test.view.address.AddressViewModel.addressToString;

@SuppressWarnings("unused")
@Getter
@Setter
public class CompanyViewModel {

    @WireVariable
    private CompanyBean companyBean;

    private CompanyEntity selectedCompany;
    private List<CompanyEntity> companies;

    @Init
    public void init() {
        companies = companyBean.readWholeTable(CompanyEntity.class);
    }

    @NotifyChange({"selectedAddress", "selectedCompany", "selectedOffices"})
    public void setSelectedCompany(CompanyEntity selectedCompany) {
        this.selectedCompany = selectedCompany;
    }

    public String getSelectedAddress() {
        if (Objects.isNull(selectedCompany)) {
            return "";
        }


        return addressToString(selectedCompany.getAddress());
    }

    public String getSelectedOffices() {
        if (Objects.isNull(selectedCompany)) {
            return "";
        }

        return selectedCompany.getOffices()
                .stream()
                .map(CompanyOfficeEntity::getOfficeName)
                .collect(Collectors.joining(", "));
    }
}
