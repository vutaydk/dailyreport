package controller.service.report;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import model.business.report.AddReportHandler;

@Path("/report")
@Produces(MediaType.APPLICATION_JSON)
public class ReportService {
	@Inject
	AddReportHandler addCommand;

	@POST
	@Path("add")
	public int insert(@Valid ReportDTO reportDTO) {
		ReportEn report = ReportConverter.fromDtoToEntity(reportDTO);
		// handling data
		int reportId = addCommand.execute(report);
		return reportId;
	}
}
