package model.business.report;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import controller.service.report.ReportEn;
import model.entity.ReportPart;
import model.repo.report.IReportPartRepo;
import model.repo.report.IReportRepo;

@RequestScoped
@Transactional
public class AddReportHandler {
	@Inject
	private IReportRepo reportRepo;
	@Inject
	private IReportPartRepo reportPartRepo;

	public int execute(ReportEn input) {
		input.getReport();
		for (ReportPart r : input.getReportParts()) {
			System.out.println(r);
		}

		reportRepo.insert(input.getReport());
		for (ReportPart r : input.getReportParts()) {
			r.setReport(input.getReport());
			reportPartRepo.insert(r);
		}

		return input.getReport().getId();
	}
}
