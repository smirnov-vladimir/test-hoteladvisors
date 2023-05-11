package ru.hoteladvisors.test.view.address;

import lombok.Getter;
import lombok.Setter;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import ru.hoteladvisors.test.ejb.address.AddressBean;
import ru.hoteladvisors.test.entity.address.AddressEntity;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
public class AddressViewModel {

    @WireVariable
    private AddressBean addressBean;

    private AddressEntity selectedAddress;
    private List<AddressEntity> addresses;

    @Init
    public void init() {
        addresses = addressBean.readWholeTable(AddressEntity.class);
    }

    public static String addressToString(AddressEntity address) {
        if (Objects.isNull(address)) {
            return "";
        }

        return Stream.of(
                address.getPostIndex(),
                address.getCity(),
                address.getStreet(),
                address.getHouse(),
                address.getFlat())
                .filter(s -> s != null && !s.isEmpty())
                .collect(Collectors.joining(", "));
    }
}
