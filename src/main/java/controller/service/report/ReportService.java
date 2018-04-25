package controller.service.report;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import model.business.report.AddReportHandler;
import model.entity.Report;

@Path("/report")
@Produces(MediaType.APPLICATION_JSON)
public class ReportService {

	@Inject
	AddReportHandler addCommand;
	@Inject
	ReportConverter converter;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public int insert(@Valid ReportDTO dto) {
		Report report = converter.fromDtoToEntity(dto);
		// handling data
		int reportId = addCommand.execute(report);
		return reportId;
	}
}
