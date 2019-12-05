package intern.controller;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "intern")
public class InternController {

	@RequestMapping(value = "internMain", method = RequestMethod.GET)
	public String internMain(Model model) {
		model.addAttribute("display", "/intern/internList.jsp");
		return "/main/index";
	}

	/**
	 * 
	 * @Title : httpClient 통신
	 * @Author : yangjaewoo, @Date : 2019. 11. 12.
	 */
	@RequestMapping(value = "getInternList", method = RequestMethod.POST, produces = "application/xml;charset=utf-8")
	@ResponseBody
	public String getInternList() {

		String url = "http://openapi.work.go.kr/opi/opi/opia/dhsOpenEmpInfoAPI.do";

		String rtnXml = "";
		byte[] responseBody = null;
		HttpClient client = new HttpClient();

		PostMethod method = new PostMethod(url);
		method.addParameter("returnType", "xml");
		method.addParameter("startPage", "1");
		method.addParameter("display", "10");
		method.addParameter("callTp", "L");

		// method.addParameter("empWantedTypeCd", "20");

		method.addParameter("authKey", "WNK2NZUW0RUBMIQ2PUMYS2VR1HJ");
		method.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

		try {
			int responseCode = client.executeMethod(method);
			// System.out.println("responseCode -->"+responseCode);

			if (responseCode != HttpStatus.SC_OK) {

			} else {
				// 내용이 길어지면 method.getResponseBodyAsStream()을 이용하여 InputStream 객체를 직접 핸들링 하는게
				// 낫다.
				responseBody = method.getResponseBody();

				// iso 8859로 리턴 되기 때문에 한번 변환해 준다.
				rtnXml = new String(responseBody, "UTF-8");
				// System.out.println("rtnXml : " + rtnXml);
			}
		} catch (Exception e) {

		} finally {
			// 혹시 모르니 releaseConnection
			if (method != null)
				method.releaseConnection();
		}

		return rtnXml;
	}

	@RequestMapping(value = "getInternSearchList", method = RequestMethod.POST, produces = "application/xml;charset=utf-8")
	@ResponseBody
	public String getInternSearchList(
			@RequestParam(value = "coClcd", required = false, defaultValue = "") String coClcd,
			@RequestParam(value = "empWantedCareerCd", required = false, defaultValue = "") String empWantedCareerCd,
			@RequestParam(value = "empWantedEduCd", required = false, defaultValue = "") String empWantedEduCd,
			@RequestParam(value = "empWantedTypeCd", required = false, defaultValue = "") String empWantedTypeCd,
			@RequestParam(value = "pageIndex", required = false, defaultValue = "1") String pageIndex) {

		String url = "http://openapi.work.go.kr/opi/opi/opia/dhsOpenEmpInfoAPI.do";

		coClcd = coClcd.replace(",", "|");
		empWantedCareerCd = empWantedCareerCd.replace(",", "|");
		empWantedEduCd = empWantedEduCd.replace(",", "|");
		empWantedTypeCd = empWantedTypeCd.replace(",", "|");

		// System.out.println(coClcd + ", " + empWantedCareerCd + ", " + empWantedEduCd
		// + ", " + empWantedTypeCd+" ,pg = " + pageIndex);

		String rtnXml = "";
		byte[] responseBody = null;
		HttpClient client = new HttpClient();

		PostMethod method = new PostMethod(url);
		method.addParameter("returnType", "xml");
		method.addParameter("startPage", pageIndex);
		method.addParameter("display", "10");
		method.addParameter("callTp", "L");
		method.addParameter("authKey", "WNK2NZUW0RUBMIQ2PUMYS2VR1HJ");

		if (coClcd != "") {
			method.addParameter("coClcd", coClcd);
		}
		if (empWantedCareerCd != "") {
			method.addParameter("empWantedCareerCd", empWantedCareerCd);
		}
		if (empWantedEduCd != "") {
			method.addParameter("empWantedEduCd", empWantedEduCd);
		}
		if (empWantedTypeCd != "") {
			method.addParameter("empWantedTypeCd", empWantedTypeCd);
		}

		method.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

		try {
			int responseCode = client.executeMethod(method);
			if (responseCode != HttpStatus.SC_OK) {
			} else {
				responseBody = method.getResponseBody();
				rtnXml = new String(responseBody, "UTF-8");
			}
		} catch (Exception e) {
		} finally {
			if (method != null)
				method.releaseConnection();
		}

		return rtnXml;
	}

}
