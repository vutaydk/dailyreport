package controller.service.login;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import common.security.Sha256;
import common.util.JWTToken;
import controller.filter.JWTTokenNeeded;
import controller.service.login.logic.LoginDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import model.business.login.LoginHandler;
import model.business.rights.RightsSelector;
import model.business.user.UserSelector;
import model.entity.Rights;
import model.entity.User;

@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
public class LoginService {

	@Inject
	LoginHandler loginHandler;
	@Inject
	UserSelector userSelector;
	@Inject
	RightsSelector rightsSelector;
	@Context
	private UriInfo uriInfo;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(@Valid LoginDTO dto) {
		int id = loginHandler.execute(dto);
		Map<String, String> datas = new HashMap<String, String>();
		datas.put("userId", String.valueOf(id));
		datas.put("token", issueToken(String.valueOf(id)));
		return Response.ok(datas).build();
	}

	private String issueToken(String userId) {
		Key key = Sha256.generateKey();
		return Jwts.builder().setSubject(userId).setIssuer(uriInfo.getAbsolutePath().toString()).setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS512, key).compact();
	}

	@GET
	@JWTTokenNeeded
	public Response status(@Context HttpHeaders httpHeaders) {
		String authorizationHeader = httpHeaders.getHeaderString(HttpHeaders.AUTHORIZATION);
		String token = authorizationHeader.substring("Bearer".length()).trim();
		String subject = JWTToken.getSubject(token);
		return Response.ok(subject).build();
	}

	@GET
	@Path("/get-rights-level")
	@JWTTokenNeeded
	public int getRights(@Context HttpHeaders httpHeaders) {
		String authorizationHeader = httpHeaders.getHeaderString(HttpHeaders.AUTHORIZATION);
		String token = authorizationHeader.substring("Bearer".length()).trim();
		String subject = JWTToken.getSubject(token);
		Optional<User> user = userSelector.getUserDetailById(Integer.valueOf(subject));
		Optional<Rights> rights = rightsSelector.getRightsDetailById(user.get().getRights());
		return rights.get().getLevel();
	}
}
