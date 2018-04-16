package common.customTags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
public class MessageTag extends TagSupport {

	private static final long serialVersionUID = 1L;

	@Setter
	private String key;

	@Override
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			out.print(key);
		} catch (Exception e) {
			log.debug(e);
		}
		return SKIP_BODY;
	}
}
