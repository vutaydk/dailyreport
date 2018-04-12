package model.business.report;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import model.entity.Report;
import model.repo.report.IReportRepo;

@RequestScoped
public class ReportSelector {
	@Inject
	private IReportRepo reportRepo;

	public List<Report> getAllProject() {
		return reportRepo.getAll();
	}

	public Optional<Report> getProjectDetailById(int id) {
		return reportRepo.findById(id);
	}
}
