package model.business.rights;

import java.util.Optional;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import common.exception.BusinessException;
import common.exception.message.RawMessage;
import model.entity.Rights;
import model.repo.right.IRightRepo;

@RequestScoped
public class UpdateRightsHandler {
	@Inject
	private IRightRepo rightRepo;

	public int execute(Rights input) {
		checkExistId(input.getId());

		rightRepo.update(input);

		return input.getId();
	}

	public void checkExistId(int id) {
		Optional<Rights> rights = rightRepo.findById(id);
		if (!rights.isPresent())
			throw new BusinessException(new RawMessage("rights khong ton tai"));
	}
}
