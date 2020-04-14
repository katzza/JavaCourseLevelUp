package application.dao;

import application.domain.JobListEntity;

import java.time.LocalDate;

public interface JobListDao {
    JobListEntity createJobRecord (Integer companyId, Integer userId, Integer positionId,
                                   LocalDate startDate, LocalDate endDate);
}
