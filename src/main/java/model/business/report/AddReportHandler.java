package model.business.report;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import model.entity.Report;
import model.repo.report.IReportRepo;

@RequestScoped
@Transactional
public class AddReportHandler {

	@Inject
	private IReportRepo reportRepo;

	public int execute(Report input) {

		reportRepo.insert(input);

		return input.getId();
	}
}
