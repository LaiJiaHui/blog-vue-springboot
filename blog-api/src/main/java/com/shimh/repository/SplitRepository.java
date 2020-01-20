package com.shimh.repository;
import com.shimh.entity.Split;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author linguochao
 * @Description TODO
 * @Date 2020/1/19 14:33
 */
public interface SplitRepository extends JpaRepository<Split,Integer> {

    @Query(value = "UPDATE split SET thumbup=IFNULL(thumbup,0)+1 WHERE id = ?1",nativeQuery = true)
    void thumbsSplit(Integer id);

    @Query(value = "SELECT * FROM split where parent_id=0 ORDER BY create_date DESC LIMIT ?1,?2",nativeQuery = true)
    List<Split> getSplitPage(int i, Integer pageSize);

    List<Split> findByParentId(Integer id);
}
