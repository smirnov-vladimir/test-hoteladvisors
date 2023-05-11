package ru.hoteladvisors.test.entity.dictionary;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.hoteladvisors.test.entity.common.ADictionary;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "dic_legal_form")
public class DicLegalFormEntity extends ADictionary {
}
