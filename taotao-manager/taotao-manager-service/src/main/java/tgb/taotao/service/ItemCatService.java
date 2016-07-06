package tgb.taotao.service;

import java.util.List;

import tgb.taotao.common.pojo.EUTreeNode;
import tgb.taotao.pojo.TbItemCat;


public interface ItemCatService {

	
	public List<EUTreeNode> getCatList(Long parentId)throws Exception ;
	public List<TbItemCat> getItemCatList(Long parentId) throws Exception;
}
