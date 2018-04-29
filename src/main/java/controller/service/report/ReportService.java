package controller.service.report;

import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import model.business.report.AddReportHandler;
import model.business.report.ReportSelector;
import model.entity.Report;

@Path("/report")
@Produces(MediaType.APPLICATION_JSON)
public class ReportService {

	@Inject
	AddReportHandler addCommand;
	@Inject
	ReportSelector reportSelector;
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

	@GET
	@Path("get-all")
	public List<ReportJSON> getAll() {
		return reportSelector.getList().stream().map(r -> converter.fromEntityToJSON(r)).collect(Collectors.toList());
	}
}
