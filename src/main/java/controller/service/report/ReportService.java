package controller.service.report;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import controller.filter.JWTTokenNeeded;
import controller.service.report.logic.ReportConverter;
import controller.service.report.logic.ReportDTO;
import controller.service.report.logic.ReportJSON;
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
	@JWTTokenNeeded
	public int insert(@Valid ReportDTO dto) {
		Report report = converter.fromAddDtoToEntity(dto);
		System.out.println(report);
		return addCommand.execute(report);
	}

	@POST
	@Path("/approvel/{id: [0-9]+}")
	@Consumes(MediaType.APPLICATION_JSON)
	@JWTTokenNeeded
	public int approvel(@PathParam("id") int id) {
		Optional<Report> report = reportSelector.getReportDetailById(id);
		System.out.println(report.get());
		// handling data
		int reportId = 1;
		return reportId;
	}

	@GET
	@Path("get-all")
	@JWTTokenNeeded
	public List<ReportJSON> getAll() {
		return reportSelector.getList().stream().map(r -> {
			return converter.fromEntityToJSON(r);
		}).collect(Collectors.toList());
	}
}
