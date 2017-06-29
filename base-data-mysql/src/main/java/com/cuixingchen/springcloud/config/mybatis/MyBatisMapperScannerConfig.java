/**
 * @Title: MyBatisMapperScannerConfig.java
 * @Package com.jyall.service.centerhouse.common
 * @Description: TODO(用一句话描述该文件做什么)
 * @author zhao.liang
 * @date 2016年3月12日 下午1:57:30
 * @version V1.0
 */
package com.cuixingchen.springcloud.config.mybatis;

import com.cuixingchen.springcloud.mapper.BaseReaderMapper;
import com.cuixingchen.springcloud.mapper.BaseWriteMapper;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;


/**
 * @ClassName: MyBatisMapperScannerConfig
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhao.liang
 * @date 2016年3月12日 下午2:06:09  
 *
 */
@Configuration
//TODO 注意，由于MapperScannerConfigurer执行的比较早，所以必须有下面的注解
@AutoConfigureAfter({MyBatisSessionFactoryConfig.class})
public class MyBatisMapperScannerConfig {
    private static Logger logger = LoggerFactory.getLogger(MyBatisMapperScannerConfig.class);

    @Bean(name = "readMapperScannerConfigurer")
    public MapperScannerConfigurer readMapperScannerConfigurer() {
        logger.info("Database Read Scanner File ~~~~~~~~~~~~~~~~~~~");
        MapperScannerConfigurer readMapperScannerConfigurer = new MapperScannerConfigurer();
        readMapperScannerConfigurer.setSqlSessionFactoryBeanName("readSqlSessionFactory");
        readMapperScannerConfigurer.setBasePackage(BaseReaderMapper.class.getPackage().getName()+ File.separator+"read");
        readMapperScannerConfigurer.setMarkerInterface(BaseReaderMapper.class);
        return readMapperScannerConfigurer;
    }

    @Bean(name = "writeMapperScannerConfigurer")
    public MapperScannerConfigurer writeMapperScannerConfigurer() {
        logger.info("Database Write Scanner File ~~~~~~~~~~~~~~~~~~~");
        MapperScannerConfigurer writeMapperScannerConfigurer = new MapperScannerConfigurer();
        writeMapperScannerConfigurer.setSqlSessionFactoryBeanName("writeSqlSessionFactory");
        writeMapperScannerConfigurer.setBasePackage(BaseWriteMapper.class.getPackage().getName()+ File.separator+"write");
        writeMapperScannerConfigurer.setMarkerInterface(BaseWriteMapper.class);
        return writeMapperScannerConfigurer;
    }


}
