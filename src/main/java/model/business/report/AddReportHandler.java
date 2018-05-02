package model.business.report;

import java.security.Key;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.ws.rs.core.HttpHeaders;
import common.security.Sha256;
import io.jsonwebtoken.Jwts;
import model.entity.Report;
import model.repo.report.IReportRepo;

@RequestScoped
public class AddReportHandler {

	@Inject
	private IReportRepo reportRepo;
	@Inject
	HttpServletRequest httpHeaders;

	@Transactional
	public int execute(Report input) {

		// set id user
		int id = getUserCurrent();
		input.setUserId(id);

		// execute
		reportRepo.insert(input);

		return input.getId();
	}

	private int getUserCurrent() {
		String authorizationHeader = httpHeaders.getHeader(HttpHeaders.AUTHORIZATION);
		String token = authorizationHeader.substring("Bearer".length()).trim();
		Key key = Sha256.generateKey();
		String obj = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject();
		return Integer.valueOf(obj);
	}
}
