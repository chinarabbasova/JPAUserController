package org.example.dao.inter;

import org.example.entity.EmploymentHistory;

import java.util.List;

public interface EmployementHistoryDaoInter {

    public List<EmploymentHistory> getAllEmploymentHistoryByUserId(int userId);

}
