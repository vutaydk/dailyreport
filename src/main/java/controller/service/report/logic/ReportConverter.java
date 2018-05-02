package controller.service.report.logic;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.inject.Inject;
import model.business.project.ProjectSelector;
import model.business.task.TaskSelector;
import model.business.user.UserSelector;
import model.entity.Project;
import model.entity.Report;
import model.entity.ReportPart;
import model.entity.Task;
import model.entity.User;

public class ReportConverter {

	@Inject
	UserSelector userSelector;
	@Inject
	ProjectSelector projectSelector;
	@Inject
	TaskSelector taskSelector;

	public Report fromAddDtoToEntity(ReportDTO dto) {
		Report e = new Report();
		e.setId(dto.getProjectId());
		e.setUserId(1);
		Set<ReportPart> reportParts = new HashSet<>();
		for (PTaskDTO p : dto.getTasks()) {
			ReportPart r = new ReportPart();
			r.setId(p.getTaskId());
			r.setTimeWorked(p.getTimeWork());
			r.setNote(p.getNote());
			reportParts.add(r);
		}
		e.setReportDetails(reportParts);
		return e;
	}

	public ReportJSON fromEntityToJSON(Report e) {
		ReportJSON j = new ReportJSON();
		j.setId(e.getId());
		Optional<User> user = userSelector.getUserDetailById(e.getUserId());
		user.ifPresent(u -> {
			j.setEmployeeCode(u.getEmployeeCode());
			j.setEmployeeName(u.getName());
		});
		Optional<Project> project = projectSelector.getProjectDetailById(e.getProjectId());
		project.ifPresent(p -> {
			j.setStartAt(p.getStartAt());
			j.setFinishAt(p.getFinishAt());
		});
		j.setTasks(e.getReportDetails().stream().map(rd -> formReportDetailToJson(rd)).collect(Collectors.toList()));
		return j;
	}

	private PTaskJSON formReportDetailToJson(ReportPart e) {
		PTaskJSON j = new PTaskJSON();
		Optional<Task> task = taskSelector.getTaskDetailById(e.getTaskId());
		task.ifPresent(t -> {
			j.setTaskId(t.getId());
			j.setTaskName(t.getName());
		});
		j.setTimeWork(e.getTimeWorked());
		return j;
	}
}
