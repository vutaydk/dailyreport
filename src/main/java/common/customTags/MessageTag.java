package common.customTags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import language.Translator;
import language.i18n.Language;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
public class MessageTag extends TagSupport {

	private static final long serialVersionUID = 1L;

	@Setter
	private String key;
	private Translator message = new Translator();

	@Override
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		Object object = pageContext.getAttribute("lang");
		if (object !=null && object instanceof Language) {
			
		}
		//Language language = 
		try {
			out.print(message.getWord(key));
		} catch (Exception e) {
			log.debug(e);
		}
		return SKIP_BODY;
	}
}
