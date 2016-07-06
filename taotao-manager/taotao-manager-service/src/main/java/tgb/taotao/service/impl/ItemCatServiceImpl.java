package tgb.taotao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tgb.taotao.common.pojo.EUTreeNode;
import tgb.taotao.mapper.TbItemCatMapper;
import tgb.taotao.pojo.TbItemCat;
import tgb.taotao.pojo.TbItemCatExample;
import tgb.taotao.pojo.TbItemCatExample.Criteria;
import tgb.taotao.service.ItemCatService;

@Service
public class ItemCatServiceImpl implements ItemCatService {
	@Autowired
	private TbItemCatMapper itemCatMapper;

	@Override
	public List<EUTreeNode> getCatList(Long parentId) throws Exception {
		TbItemCatExample example = new TbItemCatExample();
		// 设置查询条件
		Criteria criteria = example.createCriteria();
		// 根据parentid查询子节点
		criteria.andParentIdEqualTo(parentId);
		// 返回节点列表
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		List<EUTreeNode> resultList = new ArrayList<>();
		// 把列表转换成TreeNodeList
		for (TbItemCat tbItemCat : list) {
			EUTreeNode node = new EUTreeNode();
			node.setId(tbItemCat.getId());
			node.setText(tbItemCat.getName());
			node.setState(tbItemCat.getParentId() != null ? "closed" : "open");
			resultList.add(node);

		}
		return resultList;
	}

	@Override
	public List<TbItemCat> getItemCatList(Long parentId) throws Exception {

		TbItemCatExample example = new TbItemCatExample();
		// 设置查询条件
		Criteria criteria = example.createCriteria();
		// 根据parentid查询子节点
		criteria.andParentIdEqualTo(parentId);
		// 返回子节点列表
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		return list;
	}

}
