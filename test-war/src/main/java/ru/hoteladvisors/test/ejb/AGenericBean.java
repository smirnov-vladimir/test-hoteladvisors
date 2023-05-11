package ru.hoteladvisors.test.ejb;

import ru.hoteladvisors.test.entity.common.AIdentifier;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

public class AGenericBean implements Serializable {

    @PersistenceContext(unitName = "testAppUnit")
    protected EntityManager em;

    /**
     * Метод применяет EntityManager.persist или EntityManager.merge в зависимости от наличия ID у сущности
     */
    public <T extends AIdentifier> T save(T record) {
        if (record.getId() == null) {
            em.persist(record);
        } else {
            record = em.merge(record);
        }

        return record;
    }

    /**
     * Сохранение пакета записей в одной транзакции
     */
    public void save(List<? extends AIdentifier> records) {
        save(records.toArray(AIdentifier[]::new));
    }

    /**
     * Сохранение пакета записей в одной транзакции
     */
    public void save(AIdentifier... records) {
        for (AIdentifier record : records) {
            if (record.getId() == null) {
                em.persist(record);
            } else {
                em.merge(record);
            }
        }
    }

    public <T extends AIdentifier> List<T> readWholeTable(Class<T> tClass) {
        return em.createQuery("FROM " + tClass.getName(), tClass)
                .getResultList();
    }

    public <T extends AIdentifier> List<T> readWholeTable(Class<T> tClass, String sqlSuffix) {
        return em.createQuery("FROM " + tClass.getName() + sqlSuffix, tClass)
                .getResultList();
    }

    public <T extends AIdentifier> T getSingleResult(Class<T> tClass, Number id) {
        if (id == null) {
            throw new RuntimeException("id must be not null!");
        }

        return em.createQuery("FROM " + tClass.getName() + " WHERE id = :id", tClass)
                .setParameter("id", id)
                .getSingleResult();
    }
}
