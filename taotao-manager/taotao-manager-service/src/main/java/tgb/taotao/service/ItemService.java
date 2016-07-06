package tgb.taotao.service;

import tgb.taotao.common.pojo.EUDataGridResult;
import tgb.taotao.common.utils.TaotaoResult;
import tgb.taotao.pojo.TbItem;

public interface ItemService {
	TbItem getItemById(Long ItemId);
	/**
	 * 分页查询
	 * @param page
	 * @param rows
	 * @return
	 */
   EUDataGridResult getItemList(int page,int rows);
   
   /**
    * 增加对象
    * @param item
    * @return
    */
   public TaotaoResult creatItem(TbItem item,String desc) throws Exception;
   
   
}
