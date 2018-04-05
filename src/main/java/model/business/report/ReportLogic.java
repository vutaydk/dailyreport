package model.business.report;

import java.util.Optional;

import model.business.Message;
import model.entity.Report;
import model.repo.ReportRepo;

public class ReportLogic extends Message {

	private final ReportDTO dto;
	private Optional<Report> report = Optional.empty();
	private boolean isProcesing = true;

	public ReportLogic(ReportDTO dto) {
		this.dto = dto;
	}

	/**
	 * Check exist {@link Report}
	 * 
	 * @param id
	 * @return boolean
	 */
	public boolean isValidId(int id) {
		report = ReportRepo.model.find(id);
		return report.isPresent();
	}

	/**
	 * Handling {@link ReportDTO}
	 * 
	 * @return {@link ReportLogic}
	 */
	public ReportLogic isValidData() {

		return this;
	}

	/**
	 * Merge {@link ReportDTO} to {@link Report}
	 * 
	 * @return {@link Report}
	 */
	private Report megerData() {
		return new Report();
	}

	/**
	 * Add {@link Report} to database
	 */
	public void insert() {
		if (!isProcesing)
			return;
		Report report = new Report();
		boolean result = ReportRepo.model.insert(report);
		if (result)
			setMessage("success", "Add success new report");
		else
			setMessage("errror", "Add error new report");
	}

	/**
	 * Update {@link Report} to database
	 */
	public void update() {
		if (!isProcesing)
			return;
		Report report = this.report.get();
		boolean result = ReportRepo.model.update(report);
		if (result)
			setMessage("success", "Edit success report");
		else
			setMessage("errror", "Edit error report");
	}

}
