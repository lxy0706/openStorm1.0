package tgb.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import tgb.taotao.common.pojo.EUDataGridResult;
import tgb.taotao.common.utils.IDUtils;
import tgb.taotao.common.utils.TaotaoResult;
import tgb.taotao.mapper.TbItemDescMapper;
import tgb.taotao.mapper.TbItemMapper;
import tgb.taotao.pojo.TbItem;
import tgb.taotao.pojo.TbItemDesc;
import tgb.taotao.pojo.TbItemExample;
import tgb.taotao.pojo.TbItemExample.Criteria;
import tgb.taotao.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;

	@Autowired
	private TbItemDescMapper itemDescMapper;
	@Override
	public TbItem getItemById(Long ItemId) {
		// TbItem item = itemMapper.selectByPrimaryKey(ItemId);

		TbItemExample example = new TbItemExample();
		// 添加查询条件

		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(ItemId);
		List<TbItem> list = itemMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			TbItem item = list.get(0);
			return item;

		}
		return null;
	}

	/**
	 * 商品列表查询
	 */
	@Override
	public EUDataGridResult getItemList(int page, int rows) {

		// 查询商品列表

		TbItemExample example = new TbItemExample();
		// 分页处理
		PageHelper.startPage(page, rows);

		List<TbItem> list = itemMapper.selectByExample(example);
		// 创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		// 取记录的总条数

		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public TaotaoResult creatItem(TbItem item,String desc) throws Exception {
		// iterm补全
		// 生商品id
		Long itemId = IDUtils.genItemId();
		item.setId(itemId);
		//商品状态1 正常，2 下架，3删除
		item.setStatus((byte) 1);
		item.setCreated(new Date());
		item.setUpdated(new Date());

		// 插入到数据库
		itemMapper.insert(item);
//添加商品描述信息
		TaotaoResult result = insertItemDesc(itemId,desc);
		if(result.getStatus() !=200){
			throw new Exception();
		}
		return TaotaoResult.ok();
	}
    private TaotaoResult insertItemDesc(Long itemId,String desc){
    	TbItemDesc itemDesc= new TbItemDesc();
    	itemDesc.setItemId(itemId);
    	itemDesc.setItemDesc(desc);
    	itemDesc.setCreated(new Date());
    	itemDesc.setUpdated(new Date());
    	
    	itemDescMapper.insert(itemDesc);
    	return TaotaoResult.ok();
    }
	

}
