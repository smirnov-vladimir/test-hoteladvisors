package ru.hoteladvisors.test.view.company;

import lombok.Getter;
import lombok.Setter;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import ru.hoteladvisors.test.ejb.company.CompanyOfficeBean;
import ru.hoteladvisors.test.entity.company.CompanyOfficeEntity;

import java.util.List;
import java.util.Objects;

import static ru.hoteladvisors.test.view.address.AddressViewModel.addressToString;

@SuppressWarnings("unused")
@Getter
@Setter
public class CompanyOfficeViewModel {

    @WireVariable
    private CompanyOfficeBean companyOfficeBean;

    private CompanyOfficeEntity selectedOffice;
    private List<CompanyOfficeEntity> offices;

    @Init
    public void init() {
        offices = companyOfficeBean.readWholeTable(CompanyOfficeEntity.class);
    }

    @NotifyChange({"selectedAddress", "selectedOffice"})
    public void setSelectedOffice(CompanyOfficeEntity selectedOffice) {
        this.selectedOffice = selectedOffice;
    }

    public String getSelectedAddress() {
        if (Objects.isNull(selectedOffice)) {
            return "";
        }

        return addressToString(selectedOffice.getAddress());
    }
}
