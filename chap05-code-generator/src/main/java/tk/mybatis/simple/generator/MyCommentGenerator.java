package tk.mybatis.simple.generator;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.config.PropertyRegistry;
import org.mybatis.generator.internal.DefaultCommentGenerator;

import java.util.Properties;
import static org.mybatis.generator.internal.util.StringUtility.isTrue;
import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;

/**
 * 注释生成器。 默认的注释实现类是DefaultCommentGenerator. 该类实现的接口是CommentGenerator 接口
 *
 * 自己定义注释生成器，可以仿照默认的DefaultCommentGenerator, 实现接口CommentGenerator即可
 */
public class MyCommentGenerator extends DefaultCommentGenerator {
	/**
	 * 由于默认实现类中的可配参数都没有提供给予子类可以访问的方法，这里定义一遍
	 */
	private boolean suppressAllComments;
	/**
	 * 同上
	 */
	private boolean addRemarkComments;

	/**
	 * 设置用户配置的参数
	 */
	public void addConfigurationProperties(Properties properties) {
		// 先调用父类方法保证父类方法可以正常使用
		super.addConfigurationProperties(properties);
		// 获取suppressAllComments参数值
		suppressAllComments = isTrue(properties.getProperty(
						PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_ALL_COMMENTS));
		// 获取addRemarkComments参数值
		addRemarkComments = isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_ADD_REMARK_COMMENTS));
	}

	/**
	 * 给字段添加注释信息
	 */
	public void addFieldComment(Field field,
								IntrospectedTable introspectedTable,
								IntrospectedColumn introspectedColumn) {
		// 如果组织生成所有注释，直接返回
		if (suppressAllComments) {
			return;
		}
		// 文档注释开始
		field.addJavaDocLine("/**");
		// 获取数据库字段的备注信息
		String remarks = introspectedColumn.getRemarks();
		// 根据参数和备注信息判断是否添加备注信息
		if (addRemarkComments && stringHasValue(remarks)) {
			String[] remarkLines = remarks.split(System.getProperty("line.separator"));
			for (String remarkLine : remarkLines) {
				field.addJavaDocLine("*"+remarkLine);
			}
		}
		// 由于java对象名和数据库字段名可能不一样，注释中保留数据库字段名
		field.addJavaDocLine("*"+introspectedColumn.getActualColumnName());
		field.addJavaDocLine("*/");
	}
}
