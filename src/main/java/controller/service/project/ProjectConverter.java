package controller.service.project;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import model.business.task.TaskSelector;
import model.entity.Project;
import model.entity.ReportPart;
import model.entity.Task;

public class ProjectConverter {

	@Inject
	TaskSelector taskSelector;

	public Project fromDtoToEntity(ProjectDTO dto) {
		Project e = new Project();
		e.setProjectCode(dto.getProjectCode());
		e.setName(dto.getName());
		e.setStartAt(dto.getStartAt());
		e.setFinishAt(dto.getFinishAt());
		return e;
	}

	public ProjectJSON fromEntityToJSON(Project e) {
		ProjectJSON json = new ProjectJSON();
		json.setId(e.getId());
		json.setProjectCode(e.getProjectCode());
		json.setName(e.getName());
		json.setStartAt(e.getStartAt());
		json.setFinishAt(e.getFinishAt());
		return json;
	}

	public ChartJSON fromEntityToChartJSON(Project e) {
		ChartJSON json = new ChartJSON();
		json.setId(e.getId());
		json.setName(e.getName());
		List<PChartJSON> tasks = e.getReports().stream().filter(r -> !r.getReportDetails().isEmpty()).map(r -> {
			PChartJSON p = new PChartJSON();
			for (ReportPart d : r.getReportDetails()) {
				Optional<Task> task = taskSelector.getTaskDetailById(d.getTaskId());
				if (task.isPresent()) {
					p.setTaskId(task.get().getId());
					p.setTaskName(task.get().getName());
				}
				p.setTimeWork(d.getTimeWorked());
				p.setNote(d.getNote());
			}
			return p;
		}).collect(Collectors.toList());
		json.setTasks(tasks);
		return json;
	}
}
