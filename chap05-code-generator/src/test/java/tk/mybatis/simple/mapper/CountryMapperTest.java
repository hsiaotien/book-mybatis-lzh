package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import tk.mybatis.simple.model.Country;
import tk.mybatis.simple.model.CountryExample;

import java.util.List;

public class CountryMapperTest extends BaseMapper05Test{

	@Test
	public void selectByExample() {
		SqlSession sqlSession = getSqlSession();
		try {
			// 获取接口
			CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
			// 创建Example对象
			CountryExample example = new CountryExample();
			// 设置排序规则
			example.setOrderByClause("id desc,countryname asc");
			// 设置是否distinct去重
			example.setDistinct(true);
			// 创造条件
			CountryExample.Criteria criteria = example.createCriteria();
			// id >= 1
			criteria.andIdGreaterThanOrEqualTo(1);
			// id < 4
			criteria.andIdLessThan(4);
			// countrycode like %U%
			criteria.andCountrycodeLike("%U%");
			// or的情况
			CountryExample.Criteria or = example.or();
			// countryname = 中国
			or.andCountrynameEqualTo("中国");
			// 执行查询
			List<Country> countryList = countryMapper.selectByExample(example);
			printList(countryList);
		} finally {
			sqlSession.close();
		}
	}

	private <T> void printList(List<T> list) {
		for (T t : list) {
			System.out.println("t = " + t);
		}
	}


	@Test
	public void updateByExampleSelective() {
		SqlSession sqlSession = getSqlSession();
		try {
			CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
			// 创建Example对象
			CountryExample example = new CountryExample();
			// 创造条件,只能有一个createCriteria
			CountryExample.Criteria criteria = example.createCriteria();
			// 更新所有id > 2的国家
			criteria.andIdGreaterThan(2);
			// 创建一个需要设置的对象
			Country record = new Country();
			// 将国家名字设置为china
			record.setCountryname("China");
			// 执行
			countryMapper.updateByExampleSelective(record,example);
			// 将符合条件的结果输出查看
			printList(countryMapper.selectByExample(example));
		} finally {
			sqlSession.rollback();
			sqlSession.close();
		}
	}


	@Test
	public void deleteByExample() {
		SqlSession sqlSession = getSqlSession();
		try {
			CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
			//
			CountryExample example = new CountryExample();
			// 创造条件，并且只能有一个createCriteria
			CountryExample.Criteria criteria = example.createCriteria();
			//
			criteria.andIdGreaterThan(2);
			// 执行
			countryMapper.deleteByExample(example);
			// 查询，检查
			assert 0 == countryMapper.countByExample(example);
		} finally {
			sqlSession.rollback();
			sqlSession.close();
		}
	}
}