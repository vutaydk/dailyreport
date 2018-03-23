package controller.restful;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import common.util.Format;
import model.entity.Report;
import model.entity.TaskDetail;
import model.repo.ReportRepo;

@Path("/report")
public class ReportCRUD {

	@GET
	@Path("get-all")
	@Produces("application/json")
	public String getAll() {
		List<Object> list = new ArrayList<>();
		for (Report r : ReportRepo.model.getAll()) {
			HashMap<String, Object> report = new HashMap<>();
			report.put("id", r.getId());
			report.put("employeeCode", r.getUser().getEmployeeCode());
			report.put("employeeName", r.getUser().getName());
			List<Object> tasks = new ArrayList<>();
			for (TaskDetail td : r.getTaskDetails()) {
				HashMap<String, Object> task = new HashMap<>();
				task.put("code", td.getTask().getTaskCode());
				task.put("name", td.getTask().getName());
				task.put("timeWorked", td.getTimeWorked());
				task.put("note", td.getNote());
				tasks.add(task);
			}
			report.put("tasks", tasks);
			report.put("date", r.getCreatedAt());
			list.add(report);
		}
		return Format.toJson(list);
	}
}
