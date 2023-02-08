package org.example;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BuddyInfoRepository extends CrudRepository<BuddyInfo, Long> {
    BuddyInfo findById(long id);
    List<BuddyInfo> findByName(String name);
}
