package model.business.rights;

import java.security.Key;
import java.util.Optional;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.ws.rs.core.HttpHeaders;
import common.exception.BusinessException;
import common.exception.message.RawMessage;
import common.security.Sha256;
import io.jsonwebtoken.Jwts;
import model.entity.Rights;
import model.entity.User;
import model.repo.right.IRightRepo;
import model.repo.user.IUserRepo;

@RequestScoped
public class UpdateRightsHandler {

	@Inject
	private IRightRepo rightsRepo;
	@Inject
	private IUserRepo userRepo;
	@Inject
	HttpServletRequest httpHeaders;

	@Transactional
	public int execute(Rights input) {

		// check
		Rights rights = checkExistId(input.getId());
		checkLevel(input.getLevel());

		// converter
		rights.setName(input.getName());
		rights.setLevel(input.getLevel());

		// execute
		rightsRepo.update(rights);

		return rights.getId();
	}

	private Rights checkExistId(int id) {
		Optional<Rights> rights = rightsRepo.findById(id);
		if (!rights.isPresent())
			throw new BusinessException(new RawMessage("rights khong ton tai"));

		return rights.get();
	}

	private void checkLevel(int level) {
		int id = getUserCurrent();
		Optional<User> user = userRepo.findById(id);
		if (user.isPresent()) {
			Optional<Rights> rights = rightsRepo.findById(user.get().getRights());
			// check level
			if (rights.isPresent()) {
				if (rights.get().getLevel() <= level)
					return;
			}
		}

		throw new BusinessException(new RawMessage("khong du quyen"));
	}

	private int getUserCurrent() {
		String authorizationHeader = httpHeaders.getHeader(HttpHeaders.AUTHORIZATION);
		String token = authorizationHeader.substring("Bearer".length()).trim();
		Key key = Sha256.generateKey();
		String obj = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject();
		return Integer.valueOf(obj);
	}
}
