package model.repo.report;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.TypedQuery;

import common.util.DBConnector;
import model.entity.Report;
import model.repo.IRepository;

@RequestScoped
public class ReportRepoImpl implements IRepository<Report> {
	@Inject
	private DBConnector connector;

	@Override
	public List<Report> getAll() {
		TypedQuery<Report> query = connector.createQuery("FROM " + Report.class.getName(), Report.class);
		return query.getResultList();
	}

	public Optional<Report> find(int id) {
		TypedQuery<Report> query = connector.createQuery("FROM " + Report.class.getName() + " WHERE id=:id",
				Report.class);
		query.setParameter("id", id);
		return Optional.ofNullable(query.getSingleResult());

	}

	@Override
	public boolean insert(Report report) {
		report.setCreatedAt(new Date());
		connector.insert(report);
		return true;
	}

	@Override
	public boolean update(Report report) {
		connector.update(report);
		return true;
	}

	@Override
	public boolean delete(Report report) {
		connector.delete(report);
		return true;
	}

}
