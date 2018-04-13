package controller.service.report;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.entity.Report;
import model.entity.ReportPart;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReportEn {
	private Report report;
	private List<ReportPart> reportParts;
}
