package tool;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 全ActionはこのActionクラスを継承し、execute()をオーバーライドする。
// 戻り値は遷移先JSPファイルのパス名
public abstract class Action {
	public abstract String execute(HttpServletRequest request,HttpServletResponse response) throws Exception;
}
