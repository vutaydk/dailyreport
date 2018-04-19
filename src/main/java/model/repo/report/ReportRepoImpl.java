package model.repo.report;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.TypedQuery;
import common.util.DBConnector;
import model.entity.Report;

@RequestScoped
public class ReportRepoImpl implements IReportRepo {

	@Inject
	private DBConnector connector;

	@Override
	public List<Report> getAll() {
		TypedQuery<Report> query = connector.createQuery("FROM " + Report.class.getName(), Report.class);
		return query.getResultList();
	}

	@Override
	public boolean insert(Report report) {
		report.setCreatedAt(new Date());
		connector.insert(report);
		return true;
	}

	@Override
	public boolean update(Report report) {
		report.setUpdatedAt(new Date());
		connector.update(report);
		return true;
	}

	@Override
	public boolean delete(Report report) {
		connector.delete(report);
		return true;
	}

	@Override
	public Optional<Report> findById(int id) {
		Report report = connector.getEntityManager().find(Report.class, id);
		return Optional.ofNullable(report);
	}
}
