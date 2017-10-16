package com.chunyang.service.serviceImpl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chunyang.mapper.HomeMapper;
import com.chunyang.service.HomeService;

@Service
public class HomeServiceImpl implements HomeService{
    @Autowired
    private HomeMapper homeMapper;

	/* (non-Javadoc)
	 * @see com.chunyang.service.HomeService#getHomeBanner()
	 */
	@Override
	public List<HashMap<String, Object>> getHomeBanner() {
		return homeMapper.getHomeBanner();
	}

	/* (non-Javadoc)
	 * @see com.chunyang.service.HomeService#getHomeProduct()
	 */
	@Override
	public List<HashMap<String, Object>> getHomeProduct() {
		return homeMapper.getHomeProduct();
	}

}
