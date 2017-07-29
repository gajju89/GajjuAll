package com.zaso.agent.service;

import java.util.List;

import com.zaso.agent.model.FavouriteModel;

public interface FavouriteService {
	
    public void saveFavourite(String email,String accessToken,boolean string);
	
	public List<FavouriteModel> getAllFav();
	
	public void  deleteFav(String email);
	
	

}
