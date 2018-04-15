package controller.service.report;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import model.entity.Report;
import model.entity.ReportPart;

@Getter
@Setter
public class ReportEn {
	private Report report;
	private List<ReportPart> reportParts;
}
