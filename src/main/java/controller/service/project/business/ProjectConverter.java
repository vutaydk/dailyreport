package controller.service.project.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import model.business.report.ReportSelector;
import model.business.task.TaskSelector;
import model.business.user.UserSelector;
import model.entity.Project;
import model.entity.Report;
import model.entity.ReportPart;
import model.entity.Task;
import model.entity.User;

public class ProjectConverter {

	@Inject
	TaskSelector taskSelector;
	@Inject
	ReportSelector reportSelector;
	@Inject
	UserSelector userSelector;

	public Project fromDtoToEntity(ProjectDTO dto) {
		Project e = new Project();
		e.setProjectCode(dto.getProjectCode());
		e.setName(dto.getName());
		e.setStartAt(dto.getStartAt());
		e.setFinishAt(dto.getFinishAt());
		return e;
	}

	public ProjectJSON fromEntityToJSON(Project e) {
		ProjectJSON pj = new ProjectJSON();
		pj.setId(e.getId());
		pj.setProjectCode(e.getProjectCode());
		pj.setName(e.getName());
		pj.setStartAt(e.getStartAt());
		pj.setFinishAt(e.getFinishAt());
		return pj;
	}

	public ChartJSON fromEntityToChartJSON(Project e) {
		ChartJSON cj = new ChartJSON();
		cj.setId(e.getId());
		cj.setName(e.getName());
		List<PChartJSON> tasks = new ArrayList<>();
		for (Report r : e.getReports()) {
			if (!r.getReportDetails().isEmpty()) {
				for (ReportPart rp : r.getReportDetails()) {
					tasks.add(fromReportDetailToPChart(rp));
				}
			}
		}
		cj.setTasks(tasks);
		return cj;
	}

	public ChartFullJSON fromEntityToChartFullJSON(Project e) {
		ChartFullJSON cfj = new ChartFullJSON();
		cfj.setId(e.getId());
		cfj.setProjectCode(e.getProjectCode());
		cfj.setReports(e.getReports().stream().filter(r -> !r.getReportDetails().isEmpty()).map(r -> {
			return fromReportToPChartFullJSON(r);
		}).collect(Collectors.toList()));
		return cfj;
	}

	private PChartFullJSON fromReportToPChartFullJSON(Report r) {
		PChartFullJSON pcfj = new PChartFullJSON();
		pcfj.setId(r.getId());
		Optional<User> user = userSelector.getUserDetailById(r.getUserId());
		user.ifPresent(x -> {
			pcfj.setEmployeeCode(x.getEmployeeCode());
			pcfj.setEmployeeName(x.getName());
		});
		List<PChartJSON> pChartJSONs = r.getReportDetails().stream().map(rp -> {
			return fromReportDetailToPChart(rp);
		}).collect(Collectors.toList());
		pcfj.setTasks(pChartJSONs);
		return pcfj;
	}

	private PChartJSON fromReportDetailToPChart(ReportPart r) {
		PChartJSON p = new PChartJSON();
		Optional<Task> task = taskSelector.getTaskDetailById(r.getTaskId());
		if (task.isPresent()) {
			p.setTaskId(task.get().getId());
			p.setTaskName(task.get().getName());
		}
		p.setTimeWork(r.getTimeWorked());
		p.setNote(r.getNote());
		return p;
	}
}
