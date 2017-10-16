package com.chunyang.web.login;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.chunyang.util.AesCbcUtil;

import net.sf.json.JSONObject;

/**
 * 用户登录认证Contorller
 * 
 * @author qinxuegang
 *
 */
@Controller
public class LoginController {

	@Value("${chunyang.wx-xcx.wxspAppid}")
	private String wxspAppid;
	@Value("${chunyang.wx-xcx.wxspSecret}")
	private String wxspSecret;
	@Value("${chunyang.wx-xcx.grant_type}")
	private String grant_type;
	@Value("${chunyang.wx-xcx.wx-xcx.wxspUrl}")
	private String url;
	
	@Value("${chunyang.wx-xcx.grant_type_access_token}")
	private String grant_type_access_token;
	@Value("${chunyang.wx-xcx.wx-xcx.wxspAccess_tokenUrl}")
	private String tokenUrl; 
	
	@Value("${chunyang.wx-xcx.wx-xcx.wxspUnionidUrl}")
	private String wxspUnionidUrl; 
	

	private RestTemplate restTemplate = new RestTemplate();


	/**
	 * 小程序登录认证 解密用户敏感数据
	 * 
	 * @param code
	 *            用户允许登录后，回调内容会带上 code（有效期五分钟），开发者需要将 code 发送到开发者服务器后台，使用code 换取
	 *            session_key api，将 code 换成 openid 和 session_key
	 * @param iv
	 *            加密算法的初始向量
	 * @param encryptedData
	 *            明文,加密数据
	 * @return
	 */
	@ResponseBody // 该标签控制方法返回的数据
	@RequestMapping(value = "/decodeUserInfo", method = RequestMethod.POST)
	public Map<String, Object> decodeUserInfo(@RequestParam("code") String code, @RequestParam("iv") String iv,
			@RequestParam("encryptedData") String encryptedData) {

		Map<String, Object> map = new HashMap<String, Object>();

		// 登录凭证不能为空
		if (code == null || code.length() == 0) {
			map.put("status", 0);
			map.put("msg", "code 不能为空");
			return map;
		}
		
		/* 1、 向微信服务器 使用登录code 获取 session_key 和 openid */
		// 下面是使用了spring中的RestTemplat的get请求方法请求微信服务器的，而且使用了rest的风格进行的访问
		// getForObject()方法 第一个参数：访问的地址 第二个参数： 请求返回的数据类型 之后的参数为请求路径中包含的参数
		
		// 发送请求
		String str = restTemplate.getForObject(url, String.class, wxspAppid, wxspSecret, code, grant_type);
		// 解析相关内容(转换成json)
		JSONObject json = JSONObject.fromObject(str);
		// 获取会话秘钥
		String session_key = json.getString("session_key");
		// 获取用户的唯一标识
		//String openid = json.getString("openid");
	      
		//下面是进行服务器验证，需要到开发者平台进行服务器配置
		/*//发送请求 进行token验证(服务器验证)
		String access_tokenStr = restTemplate.getForObject(tokenUrl, String.class, wxspAppid, wxspSecret, "client_credential");
		// 解析相关内容(转换成json)
		JSONObject access_token_json = JSONObject.fromObject(access_tokenStr);
		// 获取access_token
		String access_token = access_token_json.getString("access_token");
		
		//发送请求获取unionid
		String unionidStr = restTemplate.getForObject(wxspUnionidUrl, String.class, access_token, openid);
		// 解析相关内容(转换成json)
		JSONObject unionid_json = JSONObject.fromObject(unionidStr);
		// 获取access_token
		String unionid = unionid_json.getString("unionid");*/
		
		
		/* 2、 对encryptedData加密数据进行AES解密 */
		try {
			String result = AesCbcUtil.decrypt(encryptedData, session_key, iv, "UTF-8");
			if (null != result && result.length() > 0) {
				map.put("status", 1);
				map.put("msg", "解密成功");

				JSONObject userInfoJSON = JSONObject.fromObject(result);
				Map<String, Object> userInfo = new HashMap<String, Object>();
				userInfo.put("openId", userInfoJSON.get("openId"));
				userInfo.put("nickName", userInfoJSON.get("nickName"));
				userInfo.put("gender", userInfoJSON.get("gender"));
				userInfo.put("city", userInfoJSON.get("city"));
				userInfo.put("province", userInfoJSON.get("province"));
				userInfo.put("country", userInfoJSON.get("country"));
				userInfo.put("avatarUrl", userInfoJSON.get("avatarUrl"));
				userInfo.put("unionId", userInfoJSON.get("unionId"));
				map.put("userInfo", userInfo);
				return map;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("status", 0);
		map.put("msg", "解密失败");
		return map;
	}

}
