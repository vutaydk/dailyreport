package controller.service.report;

import java.util.HashSet;
import java.util.Set;
import model.entity.Report;
import model.entity.ReportPart;

public class ReportConverter {

	public Report fromDtoToEntity(ReportDTO dto) {
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
}
