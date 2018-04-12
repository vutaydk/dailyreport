package model.repo.report;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.TypedQuery;

import common.util.DBConnector;
import model.entity.ReportPart;

@RequestScoped
public class ReportDetailRepoImpl implements IReportPartRepo {

	@Inject
	private DBConnector connector;

	@Override
	public List<ReportPart> getAll() {
		TypedQuery<ReportPart> query = connector.createQuery("FROM " + ReportPart.class.getName(), ReportPart.class);
		return query.getResultList();
	}

	public Optional<ReportPart> find(int id) {
		TypedQuery<ReportPart> query = connector.createQuery("FROM " + ReportPart.class.getName() + " WHERE id=:id",
				ReportPart.class);
		query.setParameter("id", id);
		return Optional.ofNullable(query.getSingleResult());
	}

	@Override
	public boolean insert(ReportPart reportDetail) {
			reportDetail.setCreatedAt(new Date());
			connector.insert(reportDetail);
			return true;
	}

	@Override
	public boolean update(ReportPart report) {
			connector.update(report);
			return true;
	}

	@Override
	public boolean delete(ReportPart report) {
			connector.delete(report);
			return true;
	}

}
