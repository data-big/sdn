package zx.soft.sdn.api.common;

import java.util.List;
import java.util.Map;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import zx.soft.sdn.api.util.JsonUtil;

/**
 * 分页信息模型
 * 
 * @author xuran
 *
 */
public class Page {

	/**当前页码**/
	private int pageNo;
	/**每页行数**/
	private int pageSize;
	/**总记录数**/
	private int totalRecord;
	/**总页数**/
	private int totalPage;
	/**查询条件**/
	private Map<String, Object> param;
	/**Action URL地址**/
	private String searchUrl;
	/**可以显示的页号(分隔符"|"，总页数变更时更新)  **/
	private String pageNoDisp;

	/**
	 * 默认构造函数
	 * 
	 * 设置默认值
	 */
	private Page() {
		pageNo = 1;
		pageSize = 10;
		totalRecord = 0;
		totalPage = 0;
		param = Maps.newHashMap();
		searchUrl = "";
		pageNoDisp = "";
	}

	/**
	 * 创建Page对象
	 * @param pageNo 当前页码
	 * @param pageSize 每页行数
	 * @param url Action URL
	 * @param param 查询参数
	 * @return Page对象
	 */
	public static Page newBuilder(int pageNo, int pageSize, String url, Map<String, Object> param) {
		Page page = new Page();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setSearchUrl(url);
		page.setParam(param);
		return page;
	}

	/**
	 * 查询条件转Json
	 * @return Json字符串
	 */
	public String getParamJson() {
		Map<String, Object> map = Maps.newHashMap();
		for (String key : param.keySet()) {
			if (param.get(key) != null) {
				map.put(key, param.get(key));
			}
		}
		return JsonUtil.parseString(map);
	}

	/** 
	 * 总页数变化时，更新总页数并计算显示样式 
	 */
	private void refreshPage() {
		//总页数计算  
		totalPage = totalRecord % pageSize == 0 ? totalRecord / pageSize : (totalRecord / pageSize + 1);
		//防止超出最末页（浏览途中数据被删除的情况）  
		if (pageNo > totalPage && totalPage != 0) {
			pageNo = totalPage;
		}
		pageNoDisp = computeDisplayStyleAndPage();
	}

	/** 
	 * 计算页号显示样式 
	 *  这里实现以下的分页样式("[]"代表当前页号)，可根据项目需求调整 
	 *   [1],2,3,4,5,6,7,8..12,13 
	 *   1,2..5,6,[7],8,9..12,13 
	 *   1,2..6,7,8,9,10,11,12,[13] 
	 */
	private String computeDisplayStyleAndPage() {
		List<Integer> pageDisplays = Lists.newArrayList();
		if (totalPage <= 11) {
			for (int i = 1; i <= totalPage; i++) {
				pageDisplays.add(i);
			}
		} else if (pageNo < 7) {
			for (int i = 1; i <= 8; i++) {
				pageDisplays.add(i);
			}
			pageDisplays.add(0);// 0 表示 省略部分(下同)  
			pageDisplays.add(totalPage - 1);
			pageDisplays.add(totalPage);
		} else if (pageNo > totalPage - 6) {
			pageDisplays.add(1);
			pageDisplays.add(2);
			pageDisplays.add(0);
			for (int i = totalPage - 7; i <= totalPage; i++) {
				pageDisplays.add(i);
			}
		} else {
			pageDisplays.add(1);
			pageDisplays.add(2);
			pageDisplays.add(0);
			for (int i = pageNo - 2; i <= pageNo + 2; i++) {
				pageDisplays.add(i);
			}
			pageDisplays.add(0);
			pageDisplays.add(totalPage - 1);
			pageDisplays.add(totalPage);
		}
		return Joiner.on("|").join(pageDisplays.toArray());
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = (pageNo > 0 ? pageNo : this.pageNo);
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = (pageSize > 0 ? pageSize : this.pageSize);
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		refreshPage();
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public Map<String, Object> getParam() {
		return param;
	}

	public void setParam(Map<String, Object> param) {
		this.param = param;
	}

	public String getSearchUrl() {
		return searchUrl;
	}

	public void setSearchUrl(String searchUrl) {
		this.searchUrl = searchUrl;
	}

	public String getPageNoDisp() {
		return pageNoDisp;
	}

	public void setPageNoDisp(String pageNoDisp) {
		this.pageNoDisp = pageNoDisp;
	}
}