package model.repo.report;

import java.util.Optional;
import model.entity.Report;
import model.repo.IRepository;

public interface IReportRepo extends IRepository<Report> {
	Optional<Report> findById(int id);
}
