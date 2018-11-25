package tk.mybatis.simple.generator;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 读取MBG配置生成代码
 */
public class Generator {

	public static void main(String[] args) throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
		//MBG执行过程中的警告信息
		ArrayList<String> warnings = new ArrayList<>();
		// 当生成代码重复时，覆盖原代码
		boolean overwrite = true;
		// 读取MBG配置文件
		InputStream is = Generator.class.getResourceAsStream(
				"/generator/generatorConfig.xml");
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(is);
		is.close();

		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		// 创建MBG
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		//执行生成代码
		myBatisGenerator.generate(null);
		for (String warning : warnings) {
			System.out.println("warning =====> " + warning);
		}
	}
}
