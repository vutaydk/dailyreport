package model.repo.report;

import java.util.Optional;
import model.entity.ReportPart;
import model.repo.IRepository;

public interface IReportPartRepo extends IRepository<ReportPart> {
	Optional<ReportPart> findById(int id);
}
