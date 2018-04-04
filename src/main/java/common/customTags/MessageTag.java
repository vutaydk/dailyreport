package common.customTags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import initialize.Message;
import lombok.Setter;

public class MessageTag extends TagSupport {

	private static final long serialVersionUID = 1L;

	@Setter
	private String key;

	@Override
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			out.print(Message.getText(key));
		} catch (Exception e) {
			System.out.println(e);
		}
		return SKIP_BODY;
	}
}
