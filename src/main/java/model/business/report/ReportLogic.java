//package model.business.report;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//
//import model.business.Message;
//import model.entity.Project;
//import model.entity.Report;
//import model.entity.ReportPart;
//import model.entity.Task;
//import model.repo.project.ProjectRepoImpl;
//import model.repo.report.ReportDetailRepoImpl;
//import model.repo.report.ReportRepoImpl;
//import model.repo.task.TaskRepoImpl;
//import model.repo.user.UserRepoImpl;
//
//public class ReportLogic extends Message {
//
//	private final ReportDTO dto;
//	private Optional<Report> report = Optional.empty();
//
//	public ReportLogic(ReportDTO dto) {
//		this.dto = dto;
//	}
//
//	/**
//	 * Check exist {@link Report}
//	 * 
//	 * @param id
//	 * @return boolean
//	 */
//	public boolean isValidId(int id) {
//		report = ReportRepoImpl.model.find(id);
//		return report.isPresent();
//	}
//
//	/**
//	 * Data validate
//	 * 
//	 * @input {@link ReportDTO}
//	 * @return boolean
//	 */
//	public boolean isValidData() {
//		boolean isProcesing = false;
//		Optional<Project> project = ProjectRepoImpl.model.find(dto.getProjectId());
//		if (!project.isPresent()) {
//			setMessage("projectId", "Invalid project.");
//			isProcesing = true;
//		}
//		List<Object> list = new ArrayList<>();
//		for (PTaskDTO pt : dto.getTasks()) {
//			Map<String, String> err = new HashMap<>();
//			Optional<Task> task = TaskRepoImpl.model.find(pt.getTaskId());
//			if (!task.isPresent()) {
//				err.put("taskId", "Invalid task.");
//				isProcesing = true;
//			}
//			if (pt.getTimework() == null) {
//				err.put("timework", "Invalid time work.");
//				isProcesing = true;
//			}
//			if (pt.getNote() == null) {
//				err.put("note", "Invalid note.");
//				isProcesing = true;
//			}
//			if (!err.isEmpty())
//				list.add(err);
//		}
//		if (!list.isEmpty())
//			setMessage("tasks", list);
//
//		return !isProcesing;
//	}
//
//	/**
//	 * Insert {@link Report} to database
//	 */
//	public void insert() {
//		Report report = new Report();
//		report.setProject(ProjectRepoImpl.model.find(dto.getProjectId()).get());
//		report.setUser(UserRepoImpl.model.find(1).get());
//		boolean result = ReportRepoImpl.model.insert(report);
//		// insert report detail
//		for (PTaskDTO p : dto.getTasks()) {
//			ReportPart reportDetail = new ReportPart();
//			reportDetail.setReport(report);
//			reportDetail.setTask(TaskRepoImpl.model.find(p.getTaskId()).get());
//			reportDetail.setTimeWorked(new Float(5));
//			reportDetail.setNote(p.getNote());
//			ReportDetailRepoImpl.model.insert(reportDetail);
//		}
//		if (result)
//			setMessage("Add success new report");
//		else
//			setMessage("Add error new report");
//	}
//
//	/**
//	 * Update {@link Report} to database
//	 */
//	public void update() {
//		// boolean result = ReportRepo.model.update(report);
//		// if (result)
//		// setMessage("Edit success report");
//		// else
//		// setMessage("Edit error report");
//	}
//
//}
