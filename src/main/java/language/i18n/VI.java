package language.i18n;

import java.util.HashMap;
import java.util.Map;

public class VI {

	private final static Map<String, String> language = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;
		{
			put("label.project", "Dự án");
			put("label.project.add", "Thêm dự án");
			put("label.task", "Công việc");
			put("label.rights", "Quyền");
			put("label.login", "Đăng nhập");
			put("label.username", "Tài khoản");
			put("label.password", "Mật khẩu");
			put("label.brandName", "DailyReport");
			put("label.project.manament", "Quản lý dự án");
			put("label.logout", "Đăng xuất");
			put("label.projectCode", "Mã dự án");
			put("label.taskCode", "Mã công việc");
			put("label.timeWork", "Thời gian làm");
			put("label.note", "Ghi chú");
			put("label.submit", "Xác nhận");
			put("label.reset", "Khôi phục");
			put("label.name", "Tên");
			put("label.startAt", "Ngày bắt đầu");
			put("label.finishAt", "Ngày kết thúc");
			put("label.manager", "Người quản lý");
			put("label.level", "Cấp bậc");
		}
	};

	public static Map<String, String> get() {
		return language;
	}
}
