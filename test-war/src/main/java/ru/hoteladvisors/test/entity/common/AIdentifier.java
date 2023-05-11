package ru.hoteladvisors.test.entity.common;

import lombok.Data;

import javax.persistence.*;

@Data
@MappedSuperclass
public abstract class AIdentifier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
}
