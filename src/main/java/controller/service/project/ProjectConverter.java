package controller.service.project;

import java.util.List;
import java.util.stream.Collectors;
import model.entity.Project;
import model.entity.ReportPart;

public class ProjectConverter {
	public static Project fromDtoToEntity(ProjectDTO dto) {
		Project en = new Project();
		en.setProjectCode(dto.getProjectCode());
		en.setName(dto.getName());
		en.setStartAt(dto.getStartAt());
		en.setFinishAt(dto.getFinishAt());
		return en;
	}

	public static ProjectJSON fromEntityToJSON(Project en) {
		ProjectJSON json = new ProjectJSON();
		json.setId(en.getId());
		json.setProjectCode(en.getProjectCode());
		json.setName(en.getName());
		json.setStartAt(en.getStartAt());
		json.setFinishAt(en.getFinishAt());
		return json;
	}

	public static ChartJSON fromEntityToChartJSON(Project en) {
		ChartJSON json = new ChartJSON();
		json.setId(en.getId());
		json.setName(en.getName());
		List<PChartJSON> tasks = en.getReports().stream().filter(r -> !r.getReportDetails().isEmpty()).map(r -> {
			PChartJSON p = new PChartJSON();
			for (ReportPart d : r.getReportDetails()) {
				p.setTaskId(d.getTask().getId());
				p.setTaskName(d.getTask().getName());
				p.setTimeWork(d.getTimeWorked());
				p.setNote(d.getNote());
			}
			return p;
		}).collect(Collectors.toList());
		json.setTasks(tasks);
		return json;
	}
}
